package com.zerobase.fastlms.main.controller;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.components.MailComponents;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.member.service.MemberService;
import com.zerobase.fastlms.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final MailComponents mailComponents;
    private final MemberService memberService;
    private final BannerService bannerService;

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request, Principal principal) {
        if (principal != null) {
            String userAgent = RequestUtils.getUserAgent(request);
            String clientIp = RequestUtils.getClientIP(request);

            String username = principal.getName();
            ServiceResult result = memberService.updateLoginHistory(username, userAgent, clientIp);
        }

        List<BannerDto> list =  bannerService.mainBannerList();

        model.addAttribute("list", list);

        return "index";
    }


    @RequestMapping("/error/denied")
    public String errorDenied() {
        return "error/denied";
    }
}
