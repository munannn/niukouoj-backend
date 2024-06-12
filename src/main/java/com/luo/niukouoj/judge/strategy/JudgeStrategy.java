package com.luo.niukouoj.judge.strategy;

import com.luo.niukouoj.judge.codesandbox.model.JudgeInfo;

/**
 * @author 木南
 * @version 1.0
 * @Description 判题策略
 */
public interface JudgeStrategy {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);

}
