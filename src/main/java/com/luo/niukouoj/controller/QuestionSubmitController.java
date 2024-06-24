package com.luo.niukouoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luo.niukouoj.common.BaseResponse;
import com.luo.niukouoj.common.ErrorCode;
import com.luo.niukouoj.common.ResultUtils;
import com.luo.niukouoj.exception.BusinessException;
import com.luo.niukouoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.luo.niukouoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.luo.niukouoj.model.entity.QuestionSubmit;
import com.luo.niukouoj.model.entity.User;
import com.luo.niukouoj.model.vo.QuestionSubmitVO;
import com.luo.niukouoj.service.QuestionSubmitService;
import com.luo.niukouoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 * @author luo
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
@Deprecated
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

//    /**
//     * 提交题目
//     *
//     * @param questionSubmitAddRequest
//     * @param request
//     * @return 提交记录的 id
//     */
//    @PostMapping("/submit")
//    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
//                                               HttpServletRequest request) {
//        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        // 登录才能提交代码
//        final User loginUser = userService.getLoginUser(request);
//        if (loginUser == null) {
//            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
//        }
//        long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
//        return ResultUtils.success(questionSubmitId);
//    }
//
//    /**
//     * 分页获取题目提交列表（除了管理员外，普通用户只能看到非答案、提交代码等公开信息）
//     *
//     * @param questionSubmitQueryRequest
//     * @param request
//     * @return
//     */
//    @PostMapping("/list/page")
//    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest
//                                                                                 questionSubmitQueryRequest,
//                                                                         HttpServletRequest request) {
//        long current = questionSubmitQueryRequest.getCurrent();
//        long size = questionSubmitQueryRequest.getPageSize();
//        // 从数据库中查询原始的题目提交分页信息
//        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
//                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
//        final User loginUser = userService.getLoginUser(request);
//        // 返回脱敏信息
//        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage, loginUser));
//    }

}
