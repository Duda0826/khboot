package com.spring.mallapi.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.google.gson.Gson;
import com.spring.mallapi.dto.MemberDTO;
import com.spring.mallapi.util.JWTUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        log.info("-------------------------------------");
        log.info(authentication.toString());
        log.info("-------------------------------------");

        MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
        Map<String, Object> claims = memberDTO.getClaims();

        //추가
        String accessToken = JWTUtil.generateToken(claims, 10); 		// 10분
        String refreshToken = JWTUtil.generateToken(claims, 60 * 24); 	// 24시간

        claims.put("accessToken", accessToken);		// 수정
        claims.put("refreshToken", refreshToken);	// 수정

        Gson gson = new Gson();
        String jsonStr = gson.toJson(claims);

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println(jsonStr);
        printWriter.close();
    }
}