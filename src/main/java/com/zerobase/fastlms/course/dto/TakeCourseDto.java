package com.zerobase.fastlms.course.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class TakeCourseDto {
    Long id;

    long courseId;
    String userId;

    long payPrice; //결제금액
    String status; //상태(수강 신청중,결제완료,수강취소)

    LocalDateTime regDt;

    //join
    String userName;
    String phone;
    String subject;

    long totalCount;
    long seq;

    public String getRegDtText(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return regDt != null ? regDt.format(formatter) : "";
    }

}
