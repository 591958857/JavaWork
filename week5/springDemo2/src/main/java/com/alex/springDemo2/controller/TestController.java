package com.alex.springDemo2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alex.springDemo2.model.Student;
import com.alex.springDemo2.service.TestService;





@Controller
@RequestMapping(value =TestController.DIR)
public class TestController  extends BaseAdminController {
    public static final String DIR = "/test";
        
    @Autowired
    private TestService testService;
    
    @RequestMapping("/query")
    @ResponseBody
    public String save(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "studentId",required = true)String studentId
    ){
    			Student student = testService.queryStudentById(studentId);
                return this.AjaxResult(200, "保存成功", student);
    }
}




