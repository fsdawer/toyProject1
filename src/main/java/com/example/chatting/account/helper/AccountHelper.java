package com.example.chatting.account.helper;

import com.example.chatting.account.dto.AccountJoinRequest;
import com.example.chatting.account.dto.AccountLoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AccountHelper {

    // 회원가입
    void join (AccountJoinRequest joinReq);

    // 로그인
    String login(AccountLoginRequest loginReq, HttpServletRequest request, HttpServletResponse response);

    // 회원아이디 조회
    Integer getMemberId(HttpServletRequest request);

    // 로그인 여부 확인
    boolean isLogin(HttpServletRequest request);

    // 로그아웃
    void Logout(HttpServletRequest request, HttpServletResponse response);

}
