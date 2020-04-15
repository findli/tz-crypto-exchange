package com.example.demo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PriceLevelDto {
    Long id;
    String symbol;
    String side;
    Integer size;
    Integer price;

    public PriceLevelDto() {
    }

    public PriceLevelDto(PriceLevelDto priceLevelDto) {
        this.id = priceLevelDto.getId();
        this.symbol = priceLevelDto.getSymbol();
        this.side = priceLevelDto.getSide();
        this.size = priceLevelDto.getSize();
        this.price = priceLevelDto.getPrice();
    }
}
