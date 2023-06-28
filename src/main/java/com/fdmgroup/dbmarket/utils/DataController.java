package com.fdmgroup.dbmarket.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.fdmgroup.dbmarket.model.MarketData;
import com.fdmgroup.dbmarket.model.TradedValues;

/**
 * The DataController class is responsible for loading traded values for a given
 * company ticker from a map of market data. It provides a method to load the
 * traded values and returns them as a list of TradedValues objects.
 *
 * @author Chrysostomos Vakasiras
 * 
 */
public class DataController {

	private TradedValuesDataUtils utils = new TradedValuesDataUtils();

	/**
	 * Loads traded values for a given company ticker from a map of market data.
	 * Checks upon all available tickers in the data set to decide which where
	 * traded on specific day.
	 * <ol>
	 * <li>Prints the list of all available tickers in the data set</li>
	 * <li>Retrieves the list of tickers traded on this day</li>
	 * <li>Retrieves the maximum, minimum, open, close, and traded volume values for
	 * the specified company ticker using Functions from TradedValuesDataUtils</li>
	 * <li>Adds the TradedValues to the List for the specified company ticker if it
	 * is traded on the day</li>
	 * <li>Adds a TradedValues object with "N/A" as the company ticker for days when
	 * it was not traded, data there will be taken from 2023-06-01</li>
	 * </ol>
	 *
	 * @param data          map of a list of market data as values and date as keys
	 * @param companyTicker the company ticker to load traded values for
	 * @return A List of traded values for the given company ticker
	 * @author Chrysostomos Vakasiras
	 * 
	 * @see {@link com.fdmgroup.dbmarket.utils.TradedValuesDataUtils}
	 *      {@link com.fdmgroup.dbmarket.utils.TradedValuesUtilsInterface}
	 *      {@link com.fdmgroup.dbmarket.csvreader.CsvReader}
	 */
	public List<TradedValues> loadTradedValues(Map<LocalDate, List<MarketData>> data, String companyTicker) {
		System.out.println("All available tickers in the data set: "
				+ data.values().parallelStream().flatMap(list -> list.stream())
						.map(marketData -> marketData.getCompanyTicker()).distinct().collect(Collectors.toList()));

		List<TradedValues> tradedValuesList = new ArrayList<TradedValues>();

		for (Entry<LocalDate, List<MarketData>> entry : data.entrySet()) {
			LocalDate key = entry.getKey();
			List<MarketData> values = entry.getValue();
			List<String> tradedOnThisDay = new ArrayList<String>(values.parallelStream()
					.map(marketData -> marketData.getCompanyTicker()).distinct().collect(Collectors.toList()));

			System.out.println("--------" + companyTicker + " Processing--------");
			System.out.println("Processing Day: " + key);
			System.out.println("Available tickers on this day: "
					+ values.stream().map(MarketData::getCompanyTicker).distinct().collect(Collectors.toList()));

			Double maxValue = utils.getMaxValue(companyTicker).apply(values);
			Double minValue = utils.getMinValue(companyTicker).apply(values);
			Double openValue = utils.getOpenValue(companyTicker).apply(values);
			Double closeValue = utils.getCloseValue(companyTicker).apply(values);
			BigDecimal tradedVolume = utils.getTradedVolume(companyTicker).apply(values);

			if (tradedOnThisDay.contains(companyTicker)) {
				tradedValuesList.add(
						new TradedValues(key, companyTicker, openValue, closeValue, maxValue, minValue, tradedVolume));
			} else {
				tradedValuesList
						.add(new TradedValues(key, "N/A", openValue, closeValue, maxValue, minValue, tradedVolume));
			}
			System.out.println("--------" + companyTicker + " Processed-------");
		}
		return tradedValuesList;

	}

}
