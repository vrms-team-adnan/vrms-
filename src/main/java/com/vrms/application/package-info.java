/**
 * Application layer: contains the services that orchestrate logic between
 * the Domain and Persistence layers, such as RentalService, AuthService,
 * and BillingService.
 * <p>
 * This layer depends only on the Domain layer. It has no knowledge of how
 * data is displayed (Presentation) or how it is actually stored
 * (Persistence) — communication with Persistence happens only through the
 * Repository interfaces defined there.
 * </p>
 */
package com.vrms.application;