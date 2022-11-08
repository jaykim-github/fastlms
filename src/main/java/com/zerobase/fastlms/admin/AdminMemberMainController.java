package com.zerobase.fastlms.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMemberMainController {

    @GetMapping("/admin/member/list.do")
    public String main(){
        return "admin/member/list";
    }
}
