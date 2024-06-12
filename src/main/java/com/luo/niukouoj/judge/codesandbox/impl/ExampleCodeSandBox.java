package com.luo.niukouoj.judge.codesandbox.impl;

import com.luo.niukouoj.judge.codesandbox.CodeSandBox;
import com.luo.niukouoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.luo.niukouoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.luo.niukouoj.judge.codesandbox.model.JudgeInfo;
import com.luo.niukouoj.model.enums.JudgeInfoMessageEnum;
import com.luo.niukouoj.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * @author 木南
 * @version 1.0
 * @Description 示例代码沙箱，只为了跑通业务流程
 */
public class ExampleCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputCaseList = executeCodeRequest.getInputCaseList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setMessage("执行测试成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        executeCodeResponse.setOutputResultList(inputCaseList);
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        judgeInfo.setMemory(110L);
        judgeInfo.setTime(150L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
