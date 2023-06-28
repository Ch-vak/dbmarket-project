package com.fdmgroup.dbmarket.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <h1>This class represents the traded values of a company for a specific
 * date.</h1>
 * 
 * @param date          The date of the traded values.
 * @param companyTicker The ticker symbol of the company.
 * @param openValue     The opening value of the company's stock.
 * @param closeValue    The closing value of the company's stock.
 * @param highestValue  The highest value of the company's stock.
 * @param lowestValue   The lowest value of the company's stock.
 * @param tradedVolume  The volume of securities traded.
 * 
 * @author Chrysostomos Vakasiras
 */
public class TradedValues {
	private LocalDate date;
	private String companyTicker;
	private double openValue;
	private double closeValue;
	private double highestValue;
	private double lowestValue;
	private BigDecimal tradedVolume;

	public TradedValues(LocalDate date, String companyTicker, double openValue, double closeValue, double highestValue,
			double lowestValue, BigDecimal tradedVolume) {
		super();
		this.date = date;
		this.companyTicker = companyTicker;
		this.openValue = openValue;
		this.closeValue = closeValue;
		this.highestValue = highestValue;
		this.lowestValue = lowestValue;
		this.tradedVolume = tradedVolume;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getCompanyTicker() {
		return companyTicker;
	}

	public double getOpenValue() {
		return openValue;
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

	public BigDecimal getTradedVolume() {
		return tradedVolume;
	}

	@Override
	public String toString() {
		return "TradedValues [date=" + date + ", companyTicker=" + companyTicker + ", openValue=" + openValue
				+ ", closeValue=" + closeValue + ", highestValue=" + highestValue + ", lowestValue=" + lowestValue
				+ ", tradedVolume=" + tradedVolume + "]";
	}

}
