package com.example.CoffeeMachine.Tasks;

import com.example.CoffeeMachine.Entities.Drink;
import com.example.CoffeeMachine.Inventory.InventoryContainer;

public class DrinksRequestUpdater implements Runnable {
    private Drink drink;
    private InventoryContainer inventoryContainer;

    public DrinksRequestUpdater(Drink drink, InventoryContainer inventoryContainer) {
        this.drink = drink;
        this.inventoryContainer = inventoryContainer;
    }
    /**
     * Accepting drink request checking ingredients needed for drink and the ingredients present in inventory
     *
     * @param Drink drink
     * @return
     */
    @Override
    public void run() {
        inventoryContainer.checkAndUpdateInventory(drink);
    }


}
