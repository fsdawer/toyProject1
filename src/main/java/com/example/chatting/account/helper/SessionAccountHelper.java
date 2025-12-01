package com.example.chatting.account.helper;

import com.example.chatting.account.dto.AccountJoinRequest;
import com.example.chatting.account.dto.AccountLoginRequest;
import com.example.chatting.account.etc.AccountConstants;
import com.example.chatting.common.util.HttpUtils;
import com.example.chatting.member.entitiy.Member;
import com.example.chatting.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionAccountHelper implements AccountHelper {

    private final MemberService memberService;

    // 회원가입
    @Override
    public void join(AccountJoinRequest joinReq) {
        memberService.save(joinReq.getName(), joinReq.getLoginId(), joinReq.getLoginPw());
    }


    // 로그인
    @Override
    public String login(AccountLoginRequest loginReq, HttpServletRequest request, HttpServletResponse response) {
        // memberService.find 아이디 비밀번호 파라미터로 회원 조회 -> 존재하면 Member객체 반환
        Member member = memberService.find(loginReq.getLoginId(), loginReq.getLoginPw());
        if(member == null) {
            return null;
        }
        // 로그인 정보를 세션에 등록
        HttpUtils.setSession(request, AccountConstants.Member_ID_NAME, member.getId());
        //로그인 성공 시 로그인 아이디를 컨트롤러로 반환한다.
        return member.getLoginId();
    }


    // 회원 아이디 조회
    @Override
    public Integer getMemberId(HttpServletRequest request) {
        // 멤버고유번호(pk)와 HTTP 요청 정보 객체를 getSession에 담아서 보냄
        Object memberId = HttpUtils.getSession(request, AccountConstants.Member_ID_NAME);
        if(memberId != null) {
            return (int) memberId;
        }
        return null;
    }


    // 로그인 여부 확인
    @Override
    public boolean isLogin(HttpServletRequest request) {
        return getMemberId(request) != null;
    }

    @Override
    public void Logout(HttpServletRequest request, HttpServletResponse response) {
        HttpUtils.removeSession(request, AccountConstants.Member_ID_NAME);
    }
}
