package com.zerobase.fastlms;

import com.zerobase.fastlms.components.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final MailComponents mailComponents;
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
