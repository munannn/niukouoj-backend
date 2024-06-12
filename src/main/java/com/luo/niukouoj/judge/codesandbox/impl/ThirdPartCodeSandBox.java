package com.luo.niukouoj.judge.codesandbox.impl;

import com.luo.niukouoj.judge.codesandbox.CodeSandBox;
import com.luo.niukouoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.luo.niukouoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @author 木南
 * @version 1.0
 * @Description 第三方代码沙箱，调用网上现成的代码沙箱
 */
public class ThirdPartCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
