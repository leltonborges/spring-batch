package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Client implements Domain {
    private String name;
    private String lastName;
    private String age;
    private String email;
    private List<Transaction> transactions = new ArrayList<>();
}
