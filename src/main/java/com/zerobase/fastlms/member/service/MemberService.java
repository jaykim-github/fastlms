package com.zerobase.fastlms.member.service;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {

    boolean register(MemberInput parameter);

    boolean emailAuth(String uuid);

    //입력한 이메일로 비밀번호 초기화 메일 전송
    boolean sendResetPassword(ResetPasswordInput parameter);

    //입력받은 uuid로 비밀번호 초기화
    boolean resetPassword(String id, String password);

    //입력받은 uuid값이 유효한지 확인
    boolean checkResetPassword(String uuid);

    //관리자용 회원 목록 리턴
    List<MemberDto> list(MemberParam parameter);

    MemberDto detail(String userId);

    boolean updateStatus(String userId, String userStatus);

    boolean updatePassword(String userId, String password);

    ServiceResult updateMember(MemberInput parameter);
    ServiceResult updateMemberPassword(MemberInput parameter);

    ServiceResult withdraw(String userId, String password);

    ServiceResult updateLoginHistory(String username, String userAgent, String clientIp);
}
