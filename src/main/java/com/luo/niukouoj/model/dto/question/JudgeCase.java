package com.luo.niukouoj.model.dto.question;

import lombok.Data;

/**
 * 题目用例
 *
 * @author 木南
 * @version 1.0
 * @Description TODO
 */
@Data
public class JudgeCase {
    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;

}
