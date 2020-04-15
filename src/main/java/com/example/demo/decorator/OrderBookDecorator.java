package com.example.demo.decorator;

import com.example.demo.dto.PriceLevelDto;
import com.example.demo.service.Exchange;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.demo.helper.Collections.distinctByKey;

@Service
public class OrderBookDecorator implements OrderBookDecoratorI {
    public Map<String, List<PriceLevelDto>> getQuote() {
        final List<PriceLevelDto> buyList = Exchange.CONCURRENT_MAP_BUY.values().stream()
                .filter(distinctByKey(PriceLevelDto::getPrice)).limit(10).collect(Collectors.toList());
        final List<PriceLevelDto> sellList = Exchange.CONCURRENT_MAP_SELL.descendingMap().values().stream()
                .filter(distinctByKey(PriceLevelDto::getPrice)).limit(10)
                .collect(Collectors.toList());
        Collections.reverse(sellList);
        return new HashMap<String, List<PriceLevelDto>>() {{
            put("Buy", buyList);
            put("Sell", sellList);
        }};

    }
}
