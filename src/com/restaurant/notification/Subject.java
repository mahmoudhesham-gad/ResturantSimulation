package com.restaurant.notification;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer Pattern: Subject that maintains observers and notifies them
 */
public class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(int orderId, List<String> items, double total) {
        for (Observer observer : observers) {
            observer.update(orderId, items, total);
        }
    }
}
