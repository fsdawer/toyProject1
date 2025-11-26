package com.example.chatting.order.repository;

import com.example.chatting.order.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    // 특정 회원의 주문 전체 목록 조회
    List<Order> findByMemberIdOrderByIdDesc(Integer memberId);

    // 특정 회원의 상세 주문 조회
    Optional<Order> findByIdAndMemberId(Integer id, Integer memberId);
}
