package com.vrms.application.strategy;
import java.time.LocalDate;
import com.vrms.domain.Rental;
/**
 * Calculates rental prices and late fees.
 */
public interface RentalPricingStrategy {
	/**
	 * calculate the rental cost 
	 * @param rental
	 * @return the rental cost
	 */
	double calcRental(Rental rental);
	/**
	 * calculate late penalty 
	 * @param rental
	 * @param returnDate real return date
	 * @return late penalty
	 */
	double calcLateP(Rental rental ,LocalDate returnDate);

}
