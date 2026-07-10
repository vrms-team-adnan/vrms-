package com.vrms.application;

import com.vrms.domain.Rental;

// A concrete observer that "sends" an email reminder when notified.
public class EmailReminderService implements NotificationObserver {

    @Override
    public void notify(Rental rental) {
        System.out.println("Reminder: your rental (ID: " + rental.getId() + ") is ending soon.");
    }
}