package com.fdmgroup.dbmarket.csvreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fdmgroup.dbmarket.model.MarketData;

/**
 * <h1>Handles the whole reading process for a csv file.</h1>
 * <p>
 * While instantiating this class you need to pass a String representing the
 * file path. This class uses the ; as delimiter to separate the values.
 * <h3>For questions about the List<Marktetdata> you can refer to the
 * model.</h3>
 * </p>
 * 
 * @param pathReader The file path of the CSV file to be read.
 * @return A map of market data as values and date as keys.
 * @throws IOException If there is an error reading the CSV file.
 * @author Chrysostomos Vakasiras
 * @see {@link com.fdmgroup.dbmarket.model.MarketData}
 *
 */
public class CsvReader {
	private String line = "";
	private String splitBy = ";";
	private LocalDateTime dateTime;
	private LocalDate date;
	private LocalTime time;
	private String companyTicker;
	private double price;
	private long securitiesTraded;
	private String pathReader;

	private Map<LocalDate, List<MarketData>> marketDataArray = new HashMap<>();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public CsvReader(String pathReader) {
		this.pathReader = pathReader;
	}

	/**
	 * Loads market data from a CSV file. Uses the ; as delimiter to separate the
	 * values. If a date is present while reading it will just add to the List the
	 * new MarketData Object, otherwise it will create a new List<MarketData> , add
	 * the Object and the create a new entry in the map.
	 * 
	 *
	 * @return a map of a list of marketData as values and date as keys
	 * @author Chrysostomos Vakasiras
	 * @throws IOException
	 *
	 */
	public Map<LocalDate, List<MarketData>> loadData() {
		try (BufferedReader br = new BufferedReader(new FileReader(pathReader))) {
			while ((line = br.readLine()) != null) {
				String[] market = line.split(splitBy);

				dateTime = LocalDateTime.parse(market[0], formatter);
				date = dateTime.toLocalDate();
				time = dateTime.toLocalTime();
				companyTicker = market[1];
				price = Double.parseDouble(market[2].replace(",", "."));
				securitiesTraded = Long.parseLong(market[3]);

				MarketData marketData = new MarketData(date, time, companyTicker, price, securitiesTraded);

				if (marketDataArray.containsKey(date)) {
					marketDataArray.get(date).add(marketData);
				} else {
					List<MarketData> newList = new ArrayList<>();
					newList.add(marketData);
					marketDataArray.put(date, newList);
				}
			}
			br.close();
			return marketDataArray;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
}
