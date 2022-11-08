package com.zerobase.fastlms.member.service;

import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    boolean register(MemberInput parameter);

    boolean emailAuth(String uuid);

    //입력한 이메일로 비밀번호 초기화 메일 전송
    boolean sendResetPassword(ResetPasswordInput parameter);

    //입력받은 uuid로 비밀번호 초기화
    boolean resetPassword(String id, String password);

    //입력받은 uuid값이 유효한지 확인
    boolean checkResetPassword(String uuid);
}
