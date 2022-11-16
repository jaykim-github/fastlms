package com.zerobase.fastlms.course.service;

import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.model.CourseParam;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.model.TakeCourseInput;

import java.util.List;

public interface CourseService {

    boolean add(CourseInput parameter);

    List<CourseDto> list(CourseParam parameter);

    CourseDto getById(long id);

    boolean set(CourseInput parameter);

    boolean del(String idList);


    //프론트 강좌 목록
    List<CourseDto> frontList(CourseParam parameter);

    CourseDto frontDetail(long id);

    //수강신청
    ServiceResult req(TakeCourseInput parameter);

    List<CourseDto> listAll();
}
