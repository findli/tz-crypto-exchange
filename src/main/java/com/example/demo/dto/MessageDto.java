package com.example.demo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class MessageDto {
    String action;
    List<PriceLevelDto> data;
}
