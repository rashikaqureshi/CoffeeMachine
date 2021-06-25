package com.example.CoffeeMachine.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Outlet {
    @JsonProperty("count_n")
    private int count;
}
