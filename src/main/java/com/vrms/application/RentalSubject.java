package com.vrms.application;

import com.vrms.domain.Rental;
import java.util.ArrayList;
import java.util.List;

// This class manages notification observers and notifies them about rental events.
public class RentalSubject {

    private final List<NotificationObserver> observers = new ArrayList<>();

    public void subscribe(NotificationObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(Rental rental) {
      for(NotificationObserver observer :observers){
        observer .notify(rental);
      }
    }
}