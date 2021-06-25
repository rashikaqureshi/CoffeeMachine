package com.example.CoffeeMachine.Inventory;

import com.example.CoffeeMachine.Entities.Drink;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryContainer {
    public final Map<String, Integer> inventoryMap = new HashMap<>();

    /**
     * adding values in inventory map
     *
     * @param key ,value
     * @return
     */
    public void addInventory(String key, Integer value) {
        int currentValue = inventoryMap.getOrDefault(key, 0);
        inventoryMap.put(key, currentValue + value);
    }
    /**
     * checking if drinks can be made from items available in inventory , if available then updating the inventory map.
     *
     * @param Drink drink
     * @return
     */
    public synchronized void checkAndUpdateInventory(Drink drink) {
        Map<String, Integer> quantityMap = drink.getIngredientQuantityMap();
        boolean canBeMade = true;
        for (Map.Entry<String, Integer> entry : quantityMap.entrySet()) {
            if (inventoryMap.containsKey(entry.getKey())) {
                int currentValue = inventoryMap.get(entry.getKey());
                if (currentValue == 0) {
                    System.out.println(drink.getName() + " cannot be prepared because " + entry.getKey() + " is not available");
                    canBeMade = false;
                    break;
                } else if (quantityMap.get(entry.getKey()) > inventoryMap.get(entry.getKey())) {
                    System.out.println(drink.getName() + " cannot be prepared because " + entry.getKey() + " is not sufficient");
                    canBeMade = false;
                    break;
                }
            }
        }
        if (canBeMade) {
            updateInventory(quantityMap);
            System.out.println(drink.getName() + " is prepared");
        }
    }

    private void updateInventory(Map<String, Integer> ingredientMap) {
        for (String ingredient : ingredientMap.keySet()) {
            int existingInventory = inventoryMap.getOrDefault(ingredient, 0);
            inventoryMap.put(ingredient, existingInventory - ingredientMap.get(ingredient));
        }
    }


}
