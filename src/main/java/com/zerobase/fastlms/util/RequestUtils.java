package com.zerobase.fastlms.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class RequestUtils {
    public static String getUserAgent(HttpServletRequest request) {
        log.info("==============================");
        log.info(request.getRequestedSessionId());
        log.info("==============================");
        return request.getHeader("User-Agent");
    }

    public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");

        //proxy 환경일 경우
        if (ip == null || ip.length() == 0) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        //웹로직 서버일 경우
        if (ip == null || ip.length() == 0) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0) {
            ip = request.getRemoteAddr() ;
        }

        return ip;
    }
}
