package com.example.demo.controller;

import com.example.demo.decorator.OrderBookDecoratorI;
import com.example.demo.dto.PriceLevelDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OrderBookController {
    final OrderBookDecoratorI orderBook;

    public OrderBookController(OrderBookDecoratorI orderBook) {
        this.orderBook = orderBook;
    }

    @GetMapping("/quote")
    public Map<String, List<PriceLevelDto>> getQuotes() {
        return orderBook.getQuote();
    }

}
