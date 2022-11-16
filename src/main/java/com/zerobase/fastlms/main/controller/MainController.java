package com.zerobase.fastlms.main.controller;

import com.zerobase.fastlms.components.MailComponents;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.member.service.MemberService;
import com.zerobase.fastlms.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final MailComponents mailComponents;
    private final MemberService memberService;

    @RequestMapping("/")
    public String index(HttpServletRequest request, Principal principal) {
        if (principal != null) {
            String userAgent = RequestUtils.getUserAgent(request);
            String clientIp = RequestUtils.getClientIP(request);

            String username = principal.getName();
            ServiceResult result = memberService.updateLoginHistory(username, userAgent, clientIp);
        }

        return "index";
    }


    @RequestMapping("/error/denied")
    public String errorDenied() {
        return "error/denied";
    }
}
