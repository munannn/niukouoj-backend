package com.luo.niukouoj.judge;

import com.luo.niukouoj.judge.codesandbox.model.JudgeInfo;
import com.luo.niukouoj.judge.strategy.DefaultJudgeStrategy;
import com.luo.niukouoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.luo.niukouoj.judge.strategy.JudgeContext;
import com.luo.niukouoj.judge.strategy.JudgeStrategy;
import com.luo.niukouoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * @author 木南
 * @version 1.0
 * @Description 判题管理(简化调用)
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("Java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
