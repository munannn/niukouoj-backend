package com.luo.niukouoj.judge.codesandbox;

import com.luo.niukouoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.luo.niukouoj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 木南
 * @version 1.0
 * @Description 使用代理模式对代码沙箱功能进行增强，在执行代码前后打印日志
 */
@Slf4j
public class CodeSandBoxProxy implements CodeSandBox {
    private final CodeSandBox codeSandBox;

    public CodeSandBoxProxy(CodeSandBox codeSandBox) {
        this.codeSandBox = codeSandBox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("代码沙箱请求信息：" + executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        log.info("代码沙箱响应信息：" + executeCodeResponse.toString());
        return executeCodeResponse;

    }
}
