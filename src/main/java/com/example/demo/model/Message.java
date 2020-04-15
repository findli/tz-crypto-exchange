package com.example.demo.model;

import org.springframework.lang.NonNull;

import java.util.Objects;

public class Message {
    private final Long priceLevel;
    private final Long amount;

    Message(@NonNull Long priceLevel, @NonNull Long amount) {
        this.priceLevel = priceLevel;
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
    }

    public Long getPriceLevel() {
        return priceLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message bookState = (Message) o;
        return priceLevel.equals(bookState.priceLevel) &&
                amount.equals(bookState.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priceLevel, amount);
    }
}
