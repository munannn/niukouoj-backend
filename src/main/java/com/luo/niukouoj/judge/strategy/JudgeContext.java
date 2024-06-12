package com.luo.niukouoj.judge.strategy;

import com.luo.niukouoj.judge.codesandbox.model.JudgeInfo;
import com.luo.niukouoj.model.dto.question.JudgeCase;
import com.luo.niukouoj.model.entity.Question;
import com.luo.niukouoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * @author 木南
 * @version 1.0
 * @Description 上下文（用于定义在策略中传递的参数）
 */
@Data
public class JudgeContext {
    private JudgeInfo judgeInfo;

    private List<String> inputCaseList;

    private List<String> outputCaseList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;

}
