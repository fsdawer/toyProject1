package com.example.chatting.item.controller;

import com.example.chatting.item.dto.ItemDto;
import com.example.chatting.item.repository.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/api/items")
    public ResponseEntity<?> readAllItems() {
        List<ItemDto> items = itemService.selectAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

}
