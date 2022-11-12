package com.zerobase.fastlms.course.controller;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.admin.service.CategoryService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminCourseController extends BaseController{

    private final CourseService courseService;
    private final CategoryService categoryService;

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

    @GetMapping(value = {"/admin/course/add", "/admin/course/edit"})
    public String add(Model model, HttpServletRequest request
        ,CourseInput parameter
    ){
        //카테고리 정보
        model.addAttribute("category", categoryService.list());

        boolean editMode = request.getRequestURI().contains("/edit");
        CourseDto detail = new CourseDto();

        if(editMode){
            long id = parameter.getId();

            CourseDto existCourse = courseService.getById(id);

            if(existCourse == null){
                //error
                model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
                return "common/error";
            }
           detail = existCourse;
        }

        model.addAttribute("editMode", editMode);
        model.addAttribute("detail",detail);

        return "admin/course/add";
    }

    @PostMapping(value = {"/admin/course/add", "/admin/course/edit"})
    public String addSubmit(Model model, CourseInput parameter, HttpServletRequest request){
        boolean editMode = request.getRequestURI().contains("/edit");
        if(editMode){
            long id = parameter.getId();

            CourseDto existCourse = courseService.getById(id);

            if(existCourse == null){
                //error
                model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
                return "common/error";
            }
            boolean result = courseService.set(parameter);
        }else{
            boolean result = courseService.add(parameter);
        }

        return "redirect:/admin/course/list";
    }

    @PostMapping("/admin/course/delete")
    public String del(Model model, CourseInput parameter, HttpServletRequest request){
        boolean result = courseService.del(parameter.getIdList());

        return "redirect:/admin/course/list";
    }


}
