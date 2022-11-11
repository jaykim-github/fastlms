package com.zerobase.fastlms.course.controller;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.model.CourseParam;
import com.zerobase.fastlms.course.service.CourseService;
import com.zerobase.fastlms.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminCourseController extends BaseController{

    private final CourseService courseService;

    @GetMapping("/admin/course/list")
    public String list(Model model, CourseParam parameter){
        parameter.init();

        List<CourseDto> courseList = courseService.list(parameter);

        long totalCount = 0;
        if(!CollectionUtils.isEmpty(courseList))
        {
            totalCount = courseList.get(0).getTotalCount();
        }

        String queryString = parameter.getQueryString();

        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);
        model.addAttribute("list", courseList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "admin/course/list";
    }

    @GetMapping("/admin/course/add")
    public String add(Model model){
        return "admin/course/add";
    }

    @PostMapping("/admin/course/add")
    public String addSubmit(Model model, CourseInput parameter){

        boolean resullt = courseService.add(parameter);

        return "redirect:/admin/course/list";
    }


}
