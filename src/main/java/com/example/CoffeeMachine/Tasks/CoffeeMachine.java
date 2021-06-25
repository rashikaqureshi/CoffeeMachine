package com.example.CoffeeMachine.Tasks;

import com.example.CoffeeMachine.Entities.CoffeeMaker;
import com.example.CoffeeMachine.Inventory.InventoryContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CoffeeMachine {

    private InventoryContainer inventoryContainer;
    private CoffeeMaker coffeeMaker;

    /**
     * Initailizing object and adding values in pojo
     */
    public CoffeeMachine(String jsonInput, InventoryContainer inventoryContainer) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        coffeeMaker = objectMapper.readValue(jsonInput, CoffeeMaker.class);
        this.inventoryContainer = inventoryContainer;
    }

    public void processInputAndAddInventory() {
        Map<String, Integer> ingredients = coffeeMaker.getMachine().getQuantityMap();
        for (String key : ingredients.keySet()) {
            inventoryContainer.addInventory(key, ingredients.get(key));
        }
    }

    public HashMap<String, HashMap<String, Integer>> getDrinks() {
        HashMap<String, HashMap<String, Integer>> drinks = coffeeMaker.getMachine().getDrinks();
        return drinks;
    }
    /**
     * Needed this to figure out number of threads
     */

    public int getThreads() {
        return coffeeMaker.getMachine().getOutlets().getCount();
    }

}
