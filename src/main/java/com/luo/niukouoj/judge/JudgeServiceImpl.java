package com.luo.niukouoj.judge;

import com.luo.niukouoj.judge.codesandbox.model.JudgeInfo;

import cn.hutool.json.JSONUtil;
import com.luo.niukouoj.common.ErrorCode;
import com.luo.niukouoj.exception.BusinessException;
import com.luo.niukouoj.judge.JudgeService;
import com.luo.niukouoj.judge.codesandbox.CodeSandBox;
import com.luo.niukouoj.judge.codesandbox.CodeSandBoxFactory;
import com.luo.niukouoj.judge.codesandbox.CodeSandBoxProxy;
import com.luo.niukouoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.luo.niukouoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.luo.niukouoj.judge.strategy.JudgeContext;
import com.luo.niukouoj.model.dto.question.JudgeCase;
import com.luo.niukouoj.model.dto.question.JudgeConfig;
import com.luo.niukouoj.model.entity.Question;
import com.luo.niukouoj.model.entity.QuestionSubmit;
import com.luo.niukouoj.model.enums.JudgeInfoMessageEnum;
import com.luo.niukouoj.model.enums.QuestionSubmitStatusEnum;
import com.luo.niukouoj.model.vo.QuestionSubmitVO;
import com.luo.niukouoj.service.QuestionService;
import com.luo.niukouoj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 木南
 * @version 1.0
 */
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private JudgeManager judgeManager;

    @Value("${codesandbox.type}")
    String type;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        // 判题逻辑
        //1.传入题目的提交id,获取到对应的题目、提交信息(包含代码、编程语言等)
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }
        //2.如果题目提交状态不为等待中，就不用重复执行了
        if (!QuestionSubmitStatusEnum.WAITING.getValue().equals(questionSubmit.getStatus())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题中");
        }
        //3.更改判题(题目提交)的状态为“判题中” ,防止重复执行，也能让用户即时看到状态
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
        //4.调用沙箱，获取到执行结果
        CodeSandBox codeSandBox = CodeSandBoxFactory.getInstance().newInstance(type);
        codeSandBox = new CodeSandBoxProxy(codeSandBox);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        // 获取题目输入用例
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputCaseList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest =
                ExecuteCodeRequest.builder()
                        .language(language)
                        .code(code)
                        .inputCaseList(inputCaseList).build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        List<String> outputCaseList = executeCodeResponse.getOutputCaseList();
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputCaseList(inputCaseList);
        judgeContext.setOutputCaseList(outputCaseList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        // 6）修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
        QuestionSubmit questionSubmitResult = questionSubmitService.getById(questionId);
        return questionSubmitResult;

//        //5.根据沙箱的执行结果，设置题目的判题状态和信息
//        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.WAITING;
//        // 首先判断题目的输出数与预期输出数是否相等，不等则直接判错
//        List<String> outputCaseList = executeCodeResponse.getOutputCaseList();
//        List<String> expectOutputCaseList =
//                judgeCaseList.stream().map(JudgeCase::getOutput).collect(Collectors.toList());
//        if (outputCaseList.size() != expectOutputCaseList.size()) {
//            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
//            return null;
//        }
//        // 判断每一次输出与期望输出是否一致
//        for (int i = 0; i < outputCaseList.size(); i++) {
//            if (!outputCaseList.get(i).equals(expectOutputCaseList.get(i))) {
//                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
//                return null;
//            }
//        }
//        // 判断题目执行限制
//        JudgeInfo judgeInfo = executeCodeResponse.getJudgeInfo();
//        // 题目执行消耗时间与内存
//        Long time = judgeInfo.getTime();
//        Long memory = judgeInfo.getMemory();
//        // 题目规定消耗时间与内存
//        String judgeConfigStr = question.getJudgeConfig();
//        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
//        Long timeLimit = judgeConfig.getTimeLimit();
//        Long memoryLimit = judgeConfig.getMemoryLimit();
//        if (time > timeLimit) {
//            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
//            return null;
//        }
//        if (memory > memoryLimit) {
//            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
//            return null;
//        }
//        return null;
    }
}
