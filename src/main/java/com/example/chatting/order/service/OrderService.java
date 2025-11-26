package com.example.chatting.order.service;

import com.example.chatting.order.dto.OrderReadDto;
import com.example.chatting.order.dto.OrderRequestDto;

import java.util.List;

public interface OrderService {

    // 특정 회원의 주문 전체 목록 조회
    List<OrderReadDto> findAll(Integer memberId);

    // 특정 회원의 상세 주문 조회
    OrderReadDto findByUserId(Integer id, Integer memberId);

    // 주문 내역 저장
    void order(OrderRequestDto orderRequestDto, Integer memberId);
}
