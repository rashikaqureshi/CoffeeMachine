package com.example.CoffeeMachine.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
@Data
public class Machine {
   private Outlet outlets;

    @JsonProperty("total_items_quantity")
    private HashMap<String, Integer> quantityMap;

    @JsonProperty("beverages")
    private  HashMap<String, HashMap<String, Integer>> drinks;
}
