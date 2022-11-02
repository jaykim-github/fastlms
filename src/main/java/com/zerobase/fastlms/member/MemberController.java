package com.zerobase.fastlms.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MemberController {

    @GetMapping(value = "/member/register")
    public String register(){
        return "member/register";
    }

    @PostMapping(value="/memeber/register")
    public String registerSubmit(HttpServletRequest request, HttpServletResponse response,
                                 MemberInput parameter){
         parameter.toString();

//        String userId = request.getParameter("userId");
//        String userName = request.getParameter("userName");
//        String password = request.getParameter("password");
//        String phone = request.getParameter("phone");


        return "member/register";
    }
}
