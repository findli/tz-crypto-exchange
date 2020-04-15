package com.example.demo.decorator;

import com.example.demo.dto.PriceLevelDto;

import java.util.List;
import java.util.Map;

public interface OrderBookDecoratorI {
    Map<String, List<PriceLevelDto>> getQuote();
}
