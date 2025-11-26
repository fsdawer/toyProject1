package com.example.chatting.cart.repository.service;

import com.example.chatting.cart.dto.CartRead;
import com.example.chatting.cart.entity.Cart;
import com.example.chatting.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    // 장바구니 상품 데이터 목록 조회(특정 회원)
    @Override
    public List<CartRead> findAll(Integer memberId) {
        return cartRepository.findAllByMemberId(memberId).stream().map(Cart::toRead).toList();
    }


    // 장바구니 상품 데이터 조회(특정 회원의 특정 상품)
    @Override
    public CartRead find(Integer memberId, Integer itemId) {
        Optional<Cart> cartOptional = cartRepository.findByMemberIdAndItemId(memberId, itemId);
        return cartOptional.map(Cart::toRead).orElse(null);
    }


    // 장바구니 상품 데이터 전체 삭제(특정 회원)
    @Override
    @Transactional
    public void removeAll(Integer memberId) {
        cartRepository.deleteByMemberId(memberId);

    }


    // 장바구니 상품 데이터 삭제(특정 회원의 특정 상품)
    @Override
    @Transactional
    public void remove(Integer memberId, Integer itemId) {
        cartRepository.deleteByMemberIdAndItemId(memberId, itemId);
    }


    // 장바구니 데이터 저장(특정 회원의 특정 상품)
    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }
}
