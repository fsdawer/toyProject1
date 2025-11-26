package com.example.chatting.member.repository;


import com.example.chatting.member.entitiy.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    // 아이디와 패스워드로 회원 정보를 조회
    Optional<Member> findByLoginIdAndLoginPw(String login_id, String login_pw);
}
