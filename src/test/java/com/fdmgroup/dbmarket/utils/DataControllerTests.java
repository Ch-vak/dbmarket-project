package com.fdmgroup.dbmarket.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.dbmarket.model.MarketData;
import com.fdmgroup.dbmarket.model.TradedValues;

class DataControllerTests {

	private List<MarketData> marketDataList1 = new ArrayList<>();
	private List<MarketData> marketDataList2 = new ArrayList<>();
	private Map<LocalDate, List<MarketData>> data = new HashMap<>();

	@BeforeEach
	void setUp() {
		LocalDate date1 = LocalDate.of(2023, 6, 1);
		LocalDate date2 = LocalDate.of(2023, 6, 2);

		marketDataList1.add(new MarketData(date1, LocalTime.of(10, 10), "ABC", 12.0, 100L));
		marketDataList1.add(new MarketData(date1, LocalTime.of(10, 11), "ABC", 12.1, 200L));

		marketDataList2.add(new MarketData(date2, LocalTime.of(10, 10), "ABC", 12.0, 100L));
		marketDataList2.add(new MarketData(date2, LocalTime.of(10, 11), "ABC", 12.1, 300L));

		data.put(date1, marketDataList1);
		data.put(date2, marketDataList2);
	}

	@Test
	void testLoadTradedValues() {
		DataController dataController = new DataController();

		List<TradedValues> tradedValues = dataController.loadTradedValues(data, "ABC");
		assertEquals(2, tradedValues.size());
	}
	
	@Test
	void testLoadTradedValuesReturnsNA_WhenTickerIsNotPresent() {
		DataController dataController = new DataController();

		List<TradedValues> tradedValues = dataController.loadTradedValues(data, "TRX");
		assertEquals(2, tradedValues.size());
		
	}
}