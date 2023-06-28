package com.fdmgroup.dbmarket.model;

import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 * <h1>This class represents the values of an index for a specific date.</h1>
 * 
 * @param date         The date of the index values.
 * @param openaValue   The opening value of the index.
 * @param closeValue   The closing value of the index.
 * @param highestValue The highest value of the index.
 * @param lowestValue  The lowest value of the index.
 * 
 * @author Chrysostomos Vakasiras
 */
public class IndexValues {

	private LocalDate date;
	private double openaValue;
	private double closeValue;
	private double highestValue;
	private double lowestValue;

	public IndexValues(LocalDate date, double openaValue, double closeValue, double highestValue, double lowestValue) {
		super();
		this.date = date;
		this.openaValue = openaValue;
		this.closeValue = closeValue;
		this.highestValue = highestValue;
		this.lowestValue = lowestValue;
	}

	public LocalDate getDate() {
		return date;
	}

	public double getOpenaValue() {
		return openaValue;
	}

	public double getCloseValue() {
		return closeValue;
	}

	public double getHighestValue() {
		return highestValue;
	}

	public double getLowestValue() {
		return lowestValue;
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.00");
		return "IndexValues [date=" + date + ", openaValue=" + df.format(openaValue) + ", closeValue="
				+ df.format(closeValue) + ", highestValue=" + df.format(highestValue) + ", lowestValue="
				+ df.format(lowestValue) + "]";
	}
}
