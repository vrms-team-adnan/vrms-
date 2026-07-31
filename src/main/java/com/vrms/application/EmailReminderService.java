package com.vrms.application;

import com.vrms.domain.Rental;

/**
 * A concrete observer that "sends" an email reminder when notified.
 * This is a simple simulation (it just prints a message) rather than
 * an actual email service.
 */
public class EmailReminderService implements NotificationObserver {

    /**
     * {@inheritDoc}
     */
    @Override
    public void notify(Rental rental) {
        System.out.println("Reminder: your rental (ID: " + rental.getId() + ") is ending soon.");
    }

    
}