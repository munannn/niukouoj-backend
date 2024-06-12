package com.luo.niukouoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luo.niukouoj.model.dto.question.QuestionQueryRequest;
import com.luo.niukouoj.model.entity.Question;
import com.luo.niukouoj.model.entity.User;
import com.luo.niukouoj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 木南
 * @description 针对表【question(题目)】的数据库操作Service
 */
public interface QuestionService extends IService<Question> {
    /**
     * 校验题目是否合法
     *
     * @param question
     * @param add
     */
    void validQuestion(Question question, boolean add);

    /**
     * 获取查询包装类，根据前端传递的请求对象，得到mybatis框架支持的查询QueryWrapper类
     *
     * @param questionQueryRequest
     * @return
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);

    /**
     * 获取题目对象返回给前端
     *
     * @param question
     * @param loginUser
     * @return
     */
    Question getQuestion(Question question, User loginUser);

    /**
     * 获取题目封装对象返回给前端
     *
     * @param question
     * @param request
     * @return
     */
    QuestionVO getQuestionVO(Question question, HttpServletRequest request);

    /**
     * 分页获取帖子封装
     *
     * @param questionPage
     * @param request
     * @return
     */
    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request);


}
