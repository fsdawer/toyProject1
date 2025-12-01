package com.example.chatting.item.repository.service;

import com.example.chatting.item.dto.ItemDto;
import com.example.chatting.item.entity.Item;
import com.example.chatting.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;
    @Value("${app.asset-base-url:http://localhost:8080}")
    private String assetBaseUrl;

    // 전체 상품 목록 조회
    @Override
    public List<ItemDto> selectAll() {
        return itemRepository.findAll()
                .stream()
                .map(Item::toRead)
                .map(this::applyAbsoluteImgPath)
                .toList();
    }


    // 특정 아이디로 상품 목록을 조회 (아이디로 조회하니까 파라미터로 아이디 넘겨야함)
    @Override
    public List<ItemDto> selectAll(List<Integer> ids) {
        return itemRepository.findAllByIdIn(ids)
                .stream()
                .map(Item::toRead)
                .map(this::applyAbsoluteImgPath)
                .toList();
    }

    private ItemDto applyAbsoluteImgPath(ItemDto dto) {
        return ItemDto.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .discountPer(dto.getDiscountPer())
                .imgPath(buildAbsoluteUrl(dto.getImgPath()))
                .build();
    }

    private String buildAbsoluteUrl(String imgPath) {
        if (imgPath == null || imgPath.isBlank()) {
            return imgPath;
        }

        // 이미 절대 경로면 그대로 반환
        if (imgPath.startsWith("http://") || imgPath.startsWith("https://")) {
            return imgPath;
        }

        String normalized = imgPath.startsWith("/") ? imgPath : "/" + imgPath;

        return normalizeBaseUrl(assetBaseUrl) + normalized;
    }

    private String normalizeBaseUrl(String base) {
        if (base == null || base.isBlank()) {
            return "";
        }

        return base.endsWith("/") ? base.substring(0, base.length() - 1) : base;
    }
}
