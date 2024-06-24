package com.luo.niukouoj.judge.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.luo.niukouoj.common.ErrorCode;
import com.luo.niukouoj.exception.BusinessException;
import com.luo.niukouoj.judge.codesandbox.CodeSandBox;
import com.luo.niukouoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.luo.niukouoj.judge.codesandbox.model.ExecuteCodeResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 木南
 * @version 1.0
 * @Description 远程代码沙箱(实际调用接口的沙箱)
 */
public class RemoteCodeSandBox implements CodeSandBox {

    // 鉴权请求头和密钥
    private static final String AUTH_REQUEST_HEADER = "auth";
    private static final String AUTH_REQUEST_ACCESS = "access";

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("调用远程代码沙箱");
        String url = "http://localhost:8102/executeCode";
        String requestJsonStr = JSONUtil.toJsonStr(executeCodeRequest);
        String responseJsonStr = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER, AUTH_REQUEST_ACCESS)
                .body(requestJsonStr).execute().body();
        if (StringUtils.isBlank(responseJsonStr)) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "调用远程代码沙箱错误，错误信息：" + responseJsonStr);
        }
        ExecuteCodeResponse executeCodeResponse = JSONUtil.toBean(responseJsonStr, ExecuteCodeResponse.class);
        return executeCodeResponse;
    }
}
