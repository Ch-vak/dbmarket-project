package com.fdmgroup.dbmarket.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

import com.fdmgroup.dbmarket.model.MarketData;

/**
 * The TradedValuesUtilsInterface provides methods to retrieve various values
 * related to a company's stock price from a list of market data. Implementing
 * classes should define the behavior for each method based on the specific
 * requirements.
 * 
 * @author Chrysostomos Vakasiras
 */
public interface TradedValuesUtilsInterface {

	/**
	 * <p>
	 * Returns the maximum value of a company's price from a list of market data. If
	 * no value is found then data from the 01-06-2023 will be taken. If there is no
	 * backup value or such CSV file, it prints an error message.
	 * </p>
	 * 
	 * @param companyTicker the ticker symbol of the company.
	 * @return the maximum value of a company's price.
	 * @author Chrysostomos Vakasiras
	 * 
	 */
	Function<List<MarketData>, Double> getMaxValue(String companyTicker);

	/**
	 * <p>
	 * Returns the minimum value of a company's stock price from a list of market
	 * data. If no value is found then data from the 01-06-2023 will be taken
	 * </p>
	 * 
	 * @param companyTicker the ticker symbol of the company
	 * @return the minimum value of a company's stock price
	 * @author Chrysostomos Vakasiras
	 */
	Function<List<MarketData>, Double> getMinValue(String companyTicker);

	/**
	 * <p>
	 * Returns the open value of a company's stock price from a list of market data.
	 * If no value is found then data from the 01-06-2023 will be taken
	 * </p>
	 * 
	 * @param companyTicker the ticker symbol of the company
	 * @return the open value of a company's stock price
	 * @author Chrysostomos Vakasiras
	 */
	Function<List<MarketData>, Double> getOpenValue(String companyTicker);

	/**
	 * <p>
	 * Returns the close value of a company's stock price from a list of market
	 * data. If no value is found then data from the 01-06-2023 will be taken
	 * </p>
	 * 
	 * @param companyTicker the ticker symbol of the company
	 * @return the close value of a company's stock price
	 * @author Chrysostomos Vakasiras
	 */
	Function<List<MarketData>, Double> getCloseValue(String companyTicker);

	/**
	 * <p>
	 * Returns the traded volume of a company's stock price from a list of market
	 * data. If no value is found then data from the 01-06-2023 will be taken
	 * </p>
	 * 
	 * @param companyTicker the ticker symbol of the company
	 * @return the traded volume of a company's stock price
	 * @author Chrysostomos Vakasiras
	 */
	Function<List<MarketData>, BigDecimal> getTradedVolume(String companyTicker);

}