package com.vrms.application;
import com.vrms.domain.Rental;

// Any class that wants to be notified about rental events must implement this.
public interface NotificationObserver {

    void notify(Rental rental);
}