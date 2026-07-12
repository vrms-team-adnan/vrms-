package com.vrms.application.strategy;

import java.time.LocalDate;

import com.vrms.domain.Rental;
import java.time.temporal.ChronoUnit;
/**
 * Calculates the normal rental cost and late fee
 */
public class carRentalStrategy implements RentalPricingStrategy{
	private final double dailycost;
	private final double dailylatecost;
	/**
	 * Creates a rental pricing strategy
	 * @param dailycost the cost for one rental day
	 * @param dailylatecost the fee for one late day
	 */
	public carRentalStrategy(double dailycost,double dailylatecost) {
		this.dailycost=dailycost;
		this.dailylatecost=dailylatecost;
	}
	/**
	 * Calculates the cost using the number of rental days
	 * @param rental the rental
	 * @return the rental cost
	 */
	@Override
	public double calcRental(Rental rental) {
		long rentD=ChronoUnit.DAYS.between(rental.getStartD(),rental.getEndD());
		return rentD*dailycost;
	}
	/**
	 * Calculates the fee for late days
	 * @param rental the rental
	 * @param returnDate the real return date
	 * @return the late fee
	 */
	@Override
	public double calcLateP(Rental rental, LocalDate returnDate) {
		if(returnDate.isBefore(rental.getEndD()))
		{return 0;}
		long rentD=ChronoUnit.DAYS.between(rental.getEndD(),returnDate);
		return rentD*dailylatecost;

	}

}
