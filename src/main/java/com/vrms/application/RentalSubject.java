package com.vrms.application;

import com.vrms.domain.Rental;
import java.util.ArrayList;
import java.util.List;

/**
 * This class manages notification observers and notifies them about
 * rental events. This is the Subject in the Observer design pattern.
 */
public class RentalSubject {

    private final List<NotificationObserver> observers = new ArrayList<>();

    /**
     * Creates a new RentalSubject.
     */
    public RentalSubject() {
    }

    /**
     * Registers a new observer to be notified about rental events.
     *
     * @param   observer   the observer to register
     */
    public void subscribe(NotificationObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies all registered observers about a rental event.
     *
     * @param   rental   the rental related to the event
     */
    public void notifyObservers(Rental rental) {
        for (NotificationObserver observer : observers) {
            observer.notify(rental);
        }
    }
}