package com.zerobase.fastlms.course.service;

import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.model.*;

import java.util.List;

public interface TakeCourseService {
    List<TakeCourseDto> list(TakeCourseParam parameter);

    TakeCourseDto detail(long id);

    ServiceResult updateStatus(long id, String status);

    List<TakeCourseDto> myCourse(String userId);

    ServiceResult cancel(long id);
}
