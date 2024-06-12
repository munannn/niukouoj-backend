package com.luo.niukouoj.judge.codesandbox.impl;

import com.luo.niukouoj.judge.codesandbox.CodeSandBox;
import com.luo.niukouoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.luo.niukouoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @author 木南
 * @version 1.0
 * @Description 远程代码沙箱(实际调用接口的沙箱)
 */
public class RemoteCodeSandBox implements CodeSandBox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        return null;
    }
}
