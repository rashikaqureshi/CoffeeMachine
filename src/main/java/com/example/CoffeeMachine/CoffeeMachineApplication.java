package com.example.CoffeeMachine;

import com.example.CoffeeMachine.Entities.Drink;
import com.example.CoffeeMachine.Inventory.InventoryContainer;
import com.example.CoffeeMachine.Tasks.CoffeeMachine;
import com.example.CoffeeMachine.Tasks.DrinksRequestUpdater;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;


@SpringBootApplication
public class CoffeeMachineApplication {

    public static void main(String[] args) throws Exception {
        File file = new File(CoffeeMachine.class.getClassLoader().getResource("input.json").getFile());
        String jsonInput = FileUtils.readFileToString(file, "UTF-8");
        InventoryContainer inventoryContainer = new InventoryContainer();
        CoffeeMachine coffeeMachine = new CoffeeMachine(jsonInput, inventoryContainer);
        int noOfthreads = coffeeMachine.getThreads();
        ExecutorService executor = new ThreadPoolExecutor(noOfthreads, noOfthreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(Runtime.getRuntime().availableProcessors()));
        final List<Future> futureArrayList = new ArrayList<>();
        runTasks(futureArrayList, coffeeMachine, executor, inventoryContainer);
        coffeeMachine.processInputAndAddInventory();
        getTaskInfo(futureArrayList, executor);
    }

    private static void runTasks(List<Future> futureArrayList, CoffeeMachine coffeeMachine, ExecutorService executor, InventoryContainer inventoryContainer) {
        HashMap<String, HashMap<String, Integer>> drinks = coffeeMachine.getDrinks();
        for (String key : drinks.keySet()) {
            Drink drink = new Drink(key, drinks.get(key));
            final Runnable transformSchemaRunnable = new DrinksRequestUpdater(drink, inventoryContainer);
            futureArrayList.add(executor.submit(transformSchemaRunnable));
        }
    }

    private static void getTaskInfo(List<Future> futureArrayList, ExecutorService executor) {
        for (final Future schemaTransformationFuture : futureArrayList) {
            try {
                schemaTransformationFuture.get();
            } catch (final Exception e) {
                executor.shutdownNow();
            }
        }
    }

}
