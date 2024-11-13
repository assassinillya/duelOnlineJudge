package com.asily.controller;

import com.asily.service.ProblemService;
import com.asily.utils.HTTPResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/problem/")
public class problemController {

    @Resource
    ProblemService problemService;


    @GetMapping("/get/{cfId}")
    public HTTPResponse<String> getProblem(@PathVariable("cfId") String cfId) {

        // 先查询库里有没有, 然后再启动爬虫
        problemService.getProblem(cfId);

        return HTTPResponse.success();
    }

}
