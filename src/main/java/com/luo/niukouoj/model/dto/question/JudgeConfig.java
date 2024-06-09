package com.luo.niukouoj.model.dto.question;

import lombok.Data;

/**
 * 题目配置
 *
 * @author 木南
 * @version 1.0
 * @Description TODO
 */
@Data
public class JudgeConfig {
    /**
     * 时间限制（ms）
     */
    private Long timeLimit;

    /**
     * 内存限制（KB）
     */
    private Long memoryLimit;

    /**
     * 堆栈限制（KB）
     */
    private Long stackLimit;

}
