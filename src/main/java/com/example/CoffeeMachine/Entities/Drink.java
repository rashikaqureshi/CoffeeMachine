package com.example.CoffeeMachine.Entities;

import lombok.Data;

import java.util.Map;
@Data
public class Drink {
    private String name;
    private Map<String, Integer> ingredientQuantityMap;

    public Drink(String name, Map<String, Integer> ingredientQuantityMap) {
        this.name = name;
        this.ingredientQuantityMap = ingredientQuantityMap;
    }
}
