package com.example.demo.service;

import com.example.demo.dto.MessageDto;
import com.example.demo.dto.PriceLevelDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ConcurrentSkipListMap;

@Service
public class Exchange implements ExchangeI {
    public static final ConcurrentSkipListMap<Long, PriceLevelDto> CONCURRENT_MAP_BUY = new ConcurrentSkipListMap<>();
    public static final ConcurrentSkipListMap<Long, PriceLevelDto> CONCURRENT_MAP_SELL = new ConcurrentSkipListMap<>();
    private static final Logger logger = LogManager.getLogger(Exchange.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @PostConstruct
    @Override
    public void run() {
        try {
            Exchange.OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            WebSocketClientEndpoint clientEndPoint = new WebSocketClientEndpoint(
                    new URI("wss://testnet.bitmex.com/realtime?subscribe=instrument,orderBookL2_25:XBTUSD"));
            clientEndPoint.addMessageHandler(message -> {
                try {
                    final MessageDto messageDto = Exchange.OBJECT_MAPPER.readValue(message, MessageDto.class);
                    if (messageDto == null || messageDto.getData() == null) return;
                    for (PriceLevelDto datum : messageDto.getData()) {
                        if (datum.getSymbol().equals("XBTUSD")) {
                            if (datum.getSide() == null) continue;
                            if (messageDto.getAction().equals("partial")) {
                                if (datum.getSide().equals("Buy")) Exchange.CONCURRENT_MAP_BUY.put(datum.getId(), datum);
                                if (datum.getSide().equals("Sell")) Exchange.CONCURRENT_MAP_SELL.put(datum.getId(), datum);
                            }
                            if (messageDto.getAction().equals("delete")) {
                                if (datum.getSide().equals("Buy")) Exchange.CONCURRENT_MAP_BUY.remove(datum.getId());
                                if (datum.getSide().equals("Sell")) Exchange.CONCURRENT_MAP_SELL.remove(datum.getId());
                            }
                            if (messageDto.getAction().equals("update")) {
                                if (datum.getSide().equals("Buy")) Exchange.CONCURRENT_MAP_BUY.put(datum.getId(), datum);
                                if (datum.getSide().equals("Sell")) Exchange.CONCURRENT_MAP_SELL.put(datum.getId(), datum);
                            }
                        }
                    }
                } catch (JsonProcessingException e) {
                    logger.error(e.getMessage(), e);
                }
            });
        } catch (URISyntaxException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
