package com.example.chatting.member.service;


import com.example.chatting.member.entitiy.Member;

public interface MemberService {

    // 회원데이터를 저장하는 메서드, 회원의 이름과 , 로그인 아이디, 패스워드를 매개변수로 받는다
    void save(String name, String login_id, String login_pw);

    // 회원데이터를 조회하는 메서드, 매개변수로 로그인 아이디, 패스워드를 받고 리턴타입으로 Member엔티티를 지정
    Member find(String login_id, String login_pw);
}
