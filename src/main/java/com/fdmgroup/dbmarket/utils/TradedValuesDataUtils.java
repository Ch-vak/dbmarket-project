package com.fdmgroup.dbmarket.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.fdmgroup.dbmarket.csvreader.CsvReader;
import com.fdmgroup.dbmarket.model.MarketData;

/**
 * <h1>This class provides utility functions for retrieving specific values from
 * a list of MarketData objects.</h1>
 * <h3>It implements the TradedValuesUtilsInterface and contains methods to
 * retrieve maximum, minimum, open, close, and traded volume values for a given
 * company's ticker.</h3>
 *
 * <p>
 * The class utilizes a backup CSV file for retrieving values if no value is
 * found in the provided list of market data. The backup file path and date are
 * configurable.
 * </p>
 *
 * <p>
 * Note: If there is an error reading the CSV file, an error message is printed
 * and a default value is returned.
 * </p>
 *
 * @see {@link com.fdmgroup.dbmarket.utils.TradedValuesUtilsInterface}
 *      {@link com.fdmgroup.dbmarket.model.MarketData}
 *      {@link com.fdmgroup.dbmarket.utils.DataController}
 */
public class TradedValuesDataUtils implements TradedValuesUtilsInterface {

	private String backUpPath = "src/main/resources/test-market.csv";
	private LocalDate backUpDate = LocalDate.parse("2023-06-01");
	private CsvReader reader = new CsvReader(backUpPath);


	public TradedValuesDataUtils() {
		super();
	}

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
	@Override
	public Function<List<MarketData>, Double> getMaxValue(String companyTicker) {
		return (data) -> {
			Optional<Double> maxOptional = data.parallelStream()
					.filter(marketData -> marketData.getCompanyTicker().equals(companyTicker))
					.max(Comparator.comparing(marketData -> marketData.getPrice()))
					.map(marketData -> marketData.getPrice());
			if (maxOptional.isPresent()) {
				return maxOptional.get();
			} else {
				return getMaxBackupValue(companyTicker).get();
			}
		};
	}

	/**
	 * This method is used to retrieve the max price in case of the company ticker
	 * not being traded for the specified day.
	 */
	private Optional<Double> getMaxBackupValue(String companyTicker) {
		Optional<Double> backupValue = Optional.empty();
		backupValue = reader.loadData().get(backUpDate).parallelStream()
				.filter(marketData -> marketData.getCompanyTicker().equals(companyTicker))
				.max(Comparator.comparing(marketData -> marketData.getPrice()))
				.map(marketData -> marketData.getPrice());

		return backupValue;
	}

	/**
	 * <p>
	 * Returns the minimum value of a company's stock price from a list of market
	 * data. If no value is found then data from the 01-06-2023 will be taken. If
	 * there is no backup value or such CSV file, it prints an error message.
	 * </p>
	 * 
	 * @param companyTicker the ticker symbol of the company
	 * @return the minimum value of a company's stock price
	 * @author Chrysostomos Vakasiras
	 */
	@Override
	public Function<List<MarketData>, Double> getMinValue(String companyTicker) {
		return (data) -> {
			Optional<Double> minOptional = data.parallelStream()
					.filter(marketData -> marketData.getCompanyTicker().equals(companyTicker))
					.min(Comparator.comparing(marketData -> marketData.getPrice()))
					.map(marketData -> marketData.getPrice());
			if (minOptional.isPresent()) {
				return minOptional.get();
			} else {
				return getMinBackupValue(companyTicker).get();
			}
		};
	}

	/**
	 * This method is used to retrieve the min price in case of the company ticker
	 * not being traded for the specified day.
	 */
	private Optional<Double> getMinBackupValue(String companyTicker) {
		Optional<Double> backupValue = Optional.empty();
		backupValue = new CsvReader(backUpPath).loadData().get(backUpDate).parallelStream()
				.filter(t -> t.getCompanyTicker().equals(companyTicker))
				.min(Comparator.comparing(marketData -> marketData.getPrice()))
				.map(marketData -> marketData.getPrice());

		return backupValue;
	}

	/**
	 * <p>
	 * Returns the open value of a company's stock price from a list of market data.
	 * If no value is found then data from the 01-06-2023 will be taken. If there is
	 * no backup value or such CSV file, it prints an error message.
	 * </p>
	 * 
	 * @param companyTicker the ticker symbol of the company
	 * @return the open value of a company's stock price
	 * @author Chrysostomos Vakasiras
	 */
	@Override
	public Function<List<MarketData>, Double> getOpenValue(String companyTicker) {
		return (data) -> {
			Optional<Double> openOptional = data.parallelStream()
					.filter(t -> t.getCompanyTicker().equals(companyTicker))
					.min(Comparator.comparing(marketData -> marketData.getTransactionTime()))
					.map(marketData -> marketData.getPrice());
			if (openOptional.isPresent()) {
				return openOptional.get();
			} else {
				Optional<Double> backupValue = getOpenBackupValue(companyTicker);
				return backupValue.get();
			}
		};
	}

	/**
	 * This method is used to retrieve the open price in case of the company ticker
	 * not being traded for the specified day.
	 */
	private Optional<Double> getOpenBackupValue(String companyTicker) {
		Optional<Double> backupValue = Optional.empty();
		backupValue = new CsvReader(backUpPath).loadData().get(backUpDate).parallelStream()
				.filter(t -> t.getCompanyTicker().equals(companyTicker))
				.min(Comparator.comparing(marketData -> marketData.getTransactionTime()))
				.map(marketData -> marketData.getPrice());

		return backupValue;
	}

	/**
	 * <p>
	 * Returns the close value of a company's stock price from a list of market
	 * data. If no value is found then data from the 01-06-2023 will be taken. If
	 * there is no backup value or such CSV file, it prints an error message.
	 * </p>
	 * 
	 * @param companyTicker the ticker symbol of the company
	 * @return the close value of a company's stock price
	 * @author Chrysostomos Vakasiras
	 */
	@Override
	public Function<List<MarketData>, Double> getCloseValue(String companyTicker) {
		return (data) -> {
			Optional<Double> closeOptional = data.parallelStream()
					.filter(t -> t.getCompanyTicker().equals(companyTicker))
					.max(Comparator.comparing(marketData -> marketData.getTransactionTime()))
					.map(marketData -> marketData.getPrice());
			if (closeOptional.isPresent()) {
				return closeOptional.get();
			} else {
				Optional<Double> backupValue = getCloseBackupValue(companyTicker);
				return backupValue.get();
			}
		};
	}

	/**
	 * This method is used to retrieve the close price in case of the company ticker
	 * not being traded for the specified day.
	 */
	private Optional<Double> getCloseBackupValue(String companyTicker) {
		Optional<Double> backupValue = Optional.empty();
		backupValue = new CsvReader(backUpPath).loadData().get(backUpDate).parallelStream()
				.filter(t -> t.getCompanyTicker().equals(companyTicker))
				.max(Comparator.comparing(marketData -> marketData.getTransactionTime()))
				.map(marketData -> marketData.getPrice());

		return backupValue;
	}

	/**
	 * <p>
	 * Returns the traded volume of a company's stock price from a list of market
	 * data. If no value is found then data from the 01-06-2023 will be taken. If
	 * there is no backup value or such CSV file, it prints an error message.
	 * </p>
	 * 
	 * @param companyTicker the ticker symbol of the company
	 * @return the traded volume of a company's stock price
	 * @author Chrysostomos Vakasiras
	 */
	@Override
	public Function<List<MarketData>, BigDecimal> getTradedVolume(String companyTicker) {
		return (data) -> {
			Optional<MarketData> anyTradedOptional = data.stream()
					.filter(t -> t.getCompanyTicker().equals(companyTicker)).findAny();

			if (anyTradedOptional.isPresent()) {
				BigDecimal result = data.parallelStream()
						.filter(marketData -> marketData.getCompanyTicker().equals(companyTicker))
						.map(marketData -> BigDecimal.valueOf(marketData.getPrice())
								.multiply(BigDecimal.valueOf(marketData.getSecuritiesTraded())))
						.reduce(BigDecimal.ZERO, BigDecimal::add);

				return result;
			} else {
				Optional<BigDecimal> backupValue = getTradedVolumeBackupValue(companyTicker);
				return backupValue.get();
			}
		};
	}

	/**
	 * This method is used to retrieve the trade volume in case of the company
	 * ticker not being traded for the specified day.
	 */
	private Optional<BigDecimal> getTradedVolumeBackupValue(String companyTicker) {
		Optional<BigDecimal> backupValue = Optional.empty();
		backupValue = Optional.of(new CsvReader(backUpPath).loadData().get(backUpDate).stream()
				.filter(marketData -> marketData.getCompanyTicker().equals(companyTicker))
				.map(marketData -> BigDecimal.valueOf(marketData.getPrice())
						.multiply(BigDecimal.valueOf(marketData.getSecuritiesTraded())))
				.reduce(BigDecimal.ZERO, (acc, x) -> acc.add(x)));

		return backupValue;
	}

}
