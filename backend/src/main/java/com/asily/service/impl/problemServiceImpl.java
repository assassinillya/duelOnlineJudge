package com.asily.service.impl;

import com.asily.components.CrawlRunner;
import com.asily.entity.Problem;
import com.asily.mapper.ProblemMapper;
import com.asily.service.ProblemService;
import com.asily.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service("problemService")
@Transactional
public class problemServiceImpl implements ProblemService {

    @Resource
    ProblemMapper problemMapper;

    @Resource
    CrawlRunner crawlRunner;

    @Override
    public Problem getProblem(String cfId) {
        QueryWrapper<Problem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cf_id", cfId);
        Problem problem = problemMapper.selectOne(queryWrapper);
        if (problem != null) return problem;
        StringUtil stringUtil = new StringUtil();
        String[] strings = stringUtil.splitString(cfId);
        String description = crawlRunner.run(strings[0], strings[1]);


        return null;
    }
}
