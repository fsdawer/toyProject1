package com.example.chatting.item.repository.service;

import com.example.chatting.item.dto.ItemDto;

import java.util.List;

public interface ItemService {

    // 전체 상품 목록 조회
    List<ItemDto> selectAll();


    // 특정 아이디로 상품 목록을 조회 (아이디로 조회하니까 파라미터로 아이디 넘겨야함)
    List<ItemDto> selectAll(List<Integer> ids);
}
