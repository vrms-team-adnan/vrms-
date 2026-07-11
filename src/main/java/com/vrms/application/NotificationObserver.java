package com.vrms.application;

import com.vrms.domain.Rental;

/**
 * Any class that wants to be notified about rental events must
 * implement this interface.
 */
public interface NotificationObserver {

    /**
     * Called when a rental event occurs (e.g. the rental is ending soon).
     *
     * @param   rental   the rental related to the event
     */
    void notify(Rental rental);
}