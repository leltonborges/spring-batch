package com.example.demo;

import java.time.LocalDate;

public class Abc {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now(); // ou informe a data desejada

        LocalDate dayOfMonth = date.withDayOfMonth(1);
        LocalDate plusMonths = dayOfMonth
                                  .plusMonths(1);
        LocalDate lastDayOfMonth = plusMonths
                                        .minusDays(1);

        System.out.println("dayOfMonth: " + dayOfMonth);
        System.out.println("plusMonths: " + plusMonths);
        System.out.println("O último dia do mês é: " + lastDayOfMonth);
    }
}
