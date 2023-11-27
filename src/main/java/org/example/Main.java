package org.example;


import java.util.*;

//Find the second most used ingredient in these recipes:
//
//        pie: ['flour', 'pepperoni', 'tomato', 'cheese', 'salt', 'pepper']
//        cake: ['flour', 'milk', 'sugar', 'cheese']
//        icecream: ['milk', 'sugar', 'nuts']
//        soup: ['water', 'meat', 'salt', 'pepper', 'potatoes']
//        fries: ['potatoes', 'salt']
//
//        If more that one ingredient satisfies the condition, return all of them.
public class Main {
    //Accept Set so we don't repeat values
    public static List<String> getSecondMostUsed(Map<String, Set<String>> recipeMap) {
        List<String> result = new ArrayList<>();

        Map<String, Integer> frequencyMap = new HashMap<>();

        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());


        //populate the frequency map
        for (Map.Entry<String, Set<String>> entry : recipeMap.entrySet()) {
            Set<String> values = entry.getValue();

            for (String ingredient : values) {
                // add only is the ingredient is not null or empty
                if (ingredient != null && !ingredient.isEmpty()) {
                    frequencyMap.put(ingredient, frequencyMap.getOrDefault(ingredient, 0) + 1);
                }
            }

        }

        //populate the maxHeap as we need the max occurring items
        for (Map.Entry<String, Integer> frequencyEntry : frequencyMap.entrySet()) {
            maxHeap.offer(frequencyEntry);
        }

        // Get the maximum occurring ingredient
        int maxCount = maxHeap.peek().getValue();


        // Remove all items for the maxHeap with the max value as we need the next most occurring item.
        while (!maxHeap.isEmpty() && maxHeap.peek().getValue() == maxCount) {
            maxHeap.poll();
        }

        //target frequency which is the second most occurring item.
        int lastValue = maxHeap.peek().getValue();

        while (!maxHeap.isEmpty()) {
            Map.Entry<String, Integer> currentEntry = maxHeap.poll();
            //Add all items to the target list which is the second most occurring item.
            if (currentEntry.getValue() == lastValue) {
                result.add(currentEntry.getKey());
            }

        }
        return result;

    }

    public static void main(String[] args) {
        Map<String, Set<String>> recipeMap = new HashMap<>();

        recipeMap.put("pie", Set.of("flour", "pepperoni", "tomato", "cheese", "salt", "pepper"));
        recipeMap.put("cake", Set.of("flour", "milk", "sugar", "cheese"));
        recipeMap.put("icecream", Set.of("milk", "sugar", "nuts"));
        recipeMap.put("soup", Set.of("water", "meat", "salt", "pepper", "potatoes"));
        recipeMap.put("fries", Set.of("potatoes", "salt"));


        for (String item : getSecondMostUsed(recipeMap)) {
            System.out.println("The second most used ingredient(s)");
            System.out.println(item);
        }

    }
}