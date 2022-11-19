package com.zerobase.fastlms.banner.controller;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.course.controller.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminBannerController extends BaseController {

    private final BannerService bannerService;

    @GetMapping("/admin/banner/list")
    public String list(Model model, BannerParam parameter){
        parameter.init();

        List<BannerDto> bannerList = bannerService.list(parameter);

        long totalCount = 0;
        if(!CollectionUtils.isEmpty(bannerList))
        {
            totalCount = bannerList.get(0).getTotalCount();
        }

        String queryString = parameter.getQueryString();

        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);
        model.addAttribute("list", bannerList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "admin/banner/list";
    }

    @GetMapping(value = {"/admin/banner/add", "/admin/banner/edit"})
    public String add(Model model, HttpServletRequest request
            , BannerInput parameter
    ){
        boolean editMode = request.getRequestURI().contains("/edit");
        BannerDto detail = new BannerDto();

        if(editMode){
            long id = parameter.getId();

            BannerDto existBanner = bannerService.getById(id);

            if(existBanner == null){
                //error
                model.addAttribute("message", "배너정보가 존재하지 않습니다.");
                return "common/error";
            }
            detail = existBanner;
        }

        model.addAttribute("editMode", editMode);
        model.addAttribute("detail",detail);

        return "admin/banner/add";
    }

    @PostMapping(value = {"/admin/banner/add", "/admin/banner/edit"})
    public String addSubmit(Model model,
                            MultipartFile file, BannerInput parameter, HttpServletRequest request){

        String saveFilename = "";
        String urlFilename = "";
        if(file != null){
            String originalFilename = file.getOriginalFilename();

            String baseLocalPath = "C:\\dev\\fastlms\\files";
            String baseUrlPath = "\\files";
            String[] arrFilename= getNewSaveFile(baseLocalPath,baseUrlPath,originalFilename);

            saveFilename = arrFilename[0];
            urlFilename = arrFilename[1];

            try {
                File newFile = new File(saveFilename);
                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
            }catch (IOException e){
                log.info(e.getMessage());
            }
        }

        //parameter.setFilename(saveFilename);
        parameter.setImagePath(urlFilename);

        System.out.println("컨트롤럴=====================");
        System.out.println(urlFilename);
        System.out.println("=====================");

        boolean editMode = request.getRequestURI().contains("/edit");
        if(editMode){
            long id = parameter.getId();

            BannerDto existBanner = bannerService.getById(id);

            if(existBanner == null){
                //error
                model.addAttribute("message", "배너정보가 존재하지 않습니다.");
                return "common/error";
            }
            boolean result = bannerService.set(parameter);
        }else{
            boolean result = bannerService.add(parameter);
        }

        return "redirect:/admin/banner/list";
    }

    @PostMapping("/admin/banner/delete")
    public String del(Model model, BannerInput parameter, HttpServletRequest request){
        boolean result = bannerService.del(parameter.getIdList());

        return "redirect:/admin/banner/list";
    }


}
