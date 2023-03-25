package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class Transaction implements Domain {
    private String id;
    private String description;
    private BigDecimal amount;
}
