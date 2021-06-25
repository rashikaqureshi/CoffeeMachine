package com.example.CoffeeMachine;

import com.example.CoffeeMachine.Inventory.InventoryContainer;
import com.example.CoffeeMachine.Tasks.CoffeeMachine;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class CoffeeMachineFunctionalTest {

    CoffeeMachine coffeeMachine;

    @Before
    public void setUp() {
    }


    @Test
    public void test1() throws Exception {
        final String filePath = "input.json";
        File file = new File(CoffeeMachine.class.getClassLoader().getResource(filePath).getFile());
        String jsonInput = FileUtils.readFileToString(file, "UTF-8");
        InventoryContainer inventoryContainer = new InventoryContainer();
        CoffeeMachine coffeeMachine = new CoffeeMachine(jsonInput, inventoryContainer);
        coffeeMachine.processInputAndAddInventory();
        Assert.assertEquals(4, coffeeMachine.getDrinks().size());
    }

    //Test when inventory is empty
    @Test
    public void test2() throws Exception {
        final String filePath = "input2.json";
        File file = new File(CoffeeMachine.class.getClassLoader().getResource(filePath).getFile());
        String jsonInput = FileUtils.readFileToString(file, "UTF-8");
        InventoryContainer inventoryContainer = new InventoryContainer();
        CoffeeMachine coffeeMachine = new CoffeeMachine(jsonInput, inventoryContainer);
        coffeeMachine.processInputAndAddInventory();
        Assert.assertEquals(4, coffeeMachine.getDrinks().size());
    }

}
