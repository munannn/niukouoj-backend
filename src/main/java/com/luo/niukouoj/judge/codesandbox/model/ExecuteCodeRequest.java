package com.luo.niukouoj.judge.codesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 木南
 * @version 1.0
 * @Description TODO
 * @since 2024/6/11 9:19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExecuteCodeRequest {

    /**
     * 代码语言
     */
    private String language;

    /**
     * 执行代码
     */
    private String code;

    /**
     * 输入用例
     */
    private List<String> inputCaseList;

}
