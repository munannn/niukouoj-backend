package com.luo.niukouoj.judge;

import com.luo.niukouoj.model.entity.QuestionSubmit;
import com.luo.niukouoj.model.vo.QuestionSubmitVO;

/**
 * @author 木南
 * @version 1.0
 * @Description 判题服务接口
 */
public interface JudgeService {

    /**
     * 判题
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit doJudge(long questionSubmitId);
}
