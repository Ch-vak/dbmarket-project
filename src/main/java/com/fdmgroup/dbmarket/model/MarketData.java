package com.fdmgroup.dbmarket.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * <h1>This class represents market data for a specific transaction.</h1>
 * 
 * @param transactionDate  The date of the transaction.
 * @param transactionTime  The time of the transaction.
 * @param companyTicker    The ticker symbol of the company.
 * @param price            The price of the security.
 * @param securitiesTraded The number of securities traded.'
 * 
 * @author Chrysostomos Vakasiras
 */
public class MarketData {

	private LocalDate transactionDate;
	private LocalTime transactionTime;
	private String companyTicker;
	private double price;
	private long securitiesTraded;

	public MarketData(LocalDate transactionDate, LocalTime transactionTime, String companyTicker, double price,
			long securitiesTraded) {
		super();
		this.transactionDate = transactionDate;
		this.transactionTime = transactionTime;
		this.companyTicker = companyTicker;
		this.price = price;
		this.securitiesTraded = securitiesTraded;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getSecuritiesTraded() {
		return securitiesTraded;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public LocalTime getTransactionTime() {
		return transactionTime;
	}

	public String getCompanyTicker() {
		return companyTicker;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public void setTransactionTime(LocalTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	public void setCompanyTicker(String companyTicker) {
		this.companyTicker = companyTicker;
	}

	public void setSecuritiesTraded(long securitiesTraded) {
		this.securitiesTraded = securitiesTraded;
	}

	@Override
	public String toString() {
		return "MarketData [transactionDate=" + transactionDate + ", transactionTime=" + transactionTime
				+ ", companyTicker=" + companyTicker + ", price=" + price + ", securitiesTraded=" + securitiesTraded
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(transactionTime);
	}

	/**
	 * Indicates whether some other object is "equal to" this one. Implemented so
	 * that objects can be compared based on transactionTime
	 * 
	 * @param obj The reference object with which to compare.
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarketData other = (MarketData) obj;
		return Objects.equals(transactionTime, other.transactionTime);
	}

}
