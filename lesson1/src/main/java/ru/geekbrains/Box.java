package ru.geekbrains;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box<T extends Fruit> {
    private final List<T> container;
    private int capacity;

    public Box(int capacity, T... fruits) {
        this.container = new ArrayList<>(Arrays.asList(fruits));
        this.capacity = capacity;
    }

    public float getWeight() {
        float commonWeight = 0.0f;

        for (T fruit : container) {
            commonWeight += fruit.getWeight();
        }
        return commonWeight;
    }

    public boolean weightCompare(Box<?> anotherBox) {
        return Math.abs(this.getWeight() - anotherBox.getWeight()) < 0.000001;
    }

    public void transferFruitsToAnotherBox(Box<T> anotherBox) {
        if (anotherBox == this) return;

        int countSize = Math.min(container.size(), anotherBox.capacity);

        List<T> fruits = container.subList(0, countSize);
        anotherBox.container.addAll(fruits);
        container.removeAll(fruits);

        anotherBox.capacity -= countSize;
        capacity += countSize;
    }

    public void addFruit(T fruit) {
        if (capacity - 1 > 0) {
            container.add(fruit);
            capacity--;
        }
    }
}

