package com.zerobase.fastlms.member.dto;

import com.zerobase.fastlms.member.entity.LoginHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LoginHistoryDto {
    private Long id;
    private String userId;
    private LocalDateTime loginDt;
    private String userAgent;
    private String clientIp;

    public static LoginHistoryDto of(LoginHistory loginHistory) {
        return LoginHistoryDto.builder()
                .id(loginHistory.getId())
                .userId(loginHistory.getUserId())
                .loginDt(loginHistory.getLoginDt())
                .userAgent(loginHistory.getUserAgent())
                .clientIp(loginHistory.getClientIp())
                .build();
    }

    public static List<LoginHistoryDto> of(List<LoginHistory> loginHistory){
        if(loginHistory == null){
            return null;
        }
        List<LoginHistoryDto> loginHistoryList = new ArrayList<>();
        for(LoginHistory x : loginHistory){
            loginHistoryList.add(LoginHistoryDto.of(x));
        }
        return loginHistoryList;
    }
}
