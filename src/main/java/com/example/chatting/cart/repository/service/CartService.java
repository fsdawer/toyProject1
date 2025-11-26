package com.example.chatting.cart.repository.service;

import com.example.chatting.cart.dto.CartRead;
import com.example.chatting.cart.entity.Cart;

import java.util.List;

public interface CartService {

    // 장바구니 상품 데이터 목록 조회(특정회원)
    List<CartRead> findAll(Integer memberId);

    // 장바구니 상품 데이터 조회(특정 회원의 특정 상품)
    CartRead find(Integer memberId, Integer itemId);

    // 장바구니 상품 데이터 전체 삭제(특정 회원)
    void removeAll(Integer memberId);

    // 장바구니에서 이 상품만 뺄래요
    void remove(Integer memberId, Integer itemId);

    // 장바구니 데이터 저장(특정 회원의 특정 상품)
    void save(Cart cart);
}
