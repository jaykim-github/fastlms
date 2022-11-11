package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.admin.dto.CategoryDto;
import com.zerobase.fastlms.admin.model.CategoryInput;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminCategoryController {

    private final CategoryService categoryService;

    @GetMapping("/admin/category/list")
    public String list(Model model, MemberParam parameter){

        List<CategoryDto> list = categoryService.list();
        model.addAttribute("list", list);

        return "admin/category/list";
    }

    @PostMapping("/admin/category/add")
    public String add(Model model, CategoryInput parameter){
        boolean result  = categoryService.add(parameter.getCategoryName());

        return "redirect:/admin/category/list";
    }

    @PostMapping("/admin/category/delete")
    public String del(Model model, CategoryInput parameter){
        boolean result  = categoryService.del(parameter.getId());

        return "redirect:/admin/category/list";
    }

    @PostMapping("/admin/category/update")
    public String update(Model model, CategoryInput parameter){
        boolean result  = categoryService.update(parameter);

        return "redirect:/admin/category/list";
    }
}
