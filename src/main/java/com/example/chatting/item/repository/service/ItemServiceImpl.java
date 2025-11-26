package com.example.chatting.item.repository.service;

import com.example.chatting.item.dto.ItemDto;
import com.example.chatting.item.entity.Item;
import com.example.chatting.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    // 전체 상품 목록 조회
    @Override
    public List<ItemDto> selectAll() {
        return itemRepository.findAll().stream().map(Item::toRead).toList();
    }


    // 특정 아이디로 상품 목록을 조회 (아이디로 조회하니까 파라미터로 아이디 넘겨야함)
    @Override
    public List<ItemDto> selectAll(List<Integer> ids) {
        return itemRepository.findAllByIdIn(ids).stream().map(Item::toRead).toList();
    }
}
