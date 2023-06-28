package com.fdmgroup.dbmarket.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.dbmarket.model.MarketData;

class TradedValuesDataUtilsTests {

	List<MarketData> data = new ArrayList<>();

	@BeforeEach
	void setUp() {
		data.add(new MarketData(null, LocalTime.of(10, 10), "AAPL", 300.0, 2l));
		data.add(new MarketData(null, LocalTime.of(10, 15), "AAPL", 100.0, 1l));
		data.add(new MarketData(null, LocalTime.of(10, 20), "AAPL", 200.0, 1l));
	}

	@Test
	public void testGetMaxValue_ReturnsHighestPrice() {

		Function<List<MarketData>, Double> function = new TradedValuesDataUtils().getMaxValue("AAPL");

		assertEquals(data.size(), 3);
		assertEquals(300.0, function.apply(data), 0.001);
	}

	@Test
	public void testGetMinValue_ReturnsLowestPrice() {

		Function<List<MarketData>, Double> function = new TradedValuesDataUtils().getMinValue("AAPL");

		assertEquals(data.size(), 3);
		assertEquals(100.0, function.apply(data), 0.001);
	}

	@Test
	public void testGetOpenValue_ReturnsEarliestTransactionPrice() {

		Function<List<MarketData>, Double> function = new TradedValuesDataUtils().getOpenValue("AAPL");

		assertEquals(data.size(), 3);
		assertEquals(300.0, function.apply(data), 0.001);
	}

	@Test
	public void testGetCloseValue_returnsTheValueWithTheBiggestTime() {

		Function<List<MarketData>, Double> function = new TradedValuesDataUtils().getCloseValue("AAPL");

		assertEquals(data.size(), 3);
		assertEquals(200.0, function.apply(data), 0.001);
	}

	@Test
	public void testTradeVolumeValue_calculatesTheCorrectVolume() {

		Function<List<MarketData>, BigDecimal> function = new TradedValuesDataUtils().getTradedVolume("AAPL");

		assertEquals(data.size(), 3);
		assertEquals(900, function.apply(data).doubleValue(), 0.01);
	}

	@Test
	public void testTradeVolumeValueBackup_UsesBackupData() {

		Function<List<MarketData>, BigDecimal> function = new TradedValuesDataUtils().getTradedVolume("NGL");

		assertEquals(data.size(), 3);
		assertFalse(function.apply(data).equals(null));
	}

	@Test
	public void testMaxPriceValueBackup_UsesBackupData() {

		Function<List<MarketData>, Double> function = new TradedValuesDataUtils().getMaxValue("NGL");

		assertEquals(data.size(), 3);
		assertFalse(function.apply(data).equals(null));
	}

	@Test
	public void testMinValueValueBackup_UsesBackupData() {

		Function<List<MarketData>, Double> function = new TradedValuesDataUtils().getMinValue("NGL");

		assertEquals(data.size(), 3);
		assertFalse(function.apply(data).equals(null));
	}

	@Test
	public void testOpenValueValueBackup_UsesBackupData() {

		Function<List<MarketData>, Double> function = new TradedValuesDataUtils().getOpenValue("NGL");

		assertEquals(data.size(), 3);
		assertFalse(function.apply(data).equals(null));
	}

	@Test
	public void testCloseValueValueBackup_UsesBackupData() {

		Function<List<MarketData>, Double> function = new TradedValuesDataUtils().getCloseValue("NGL");

		assertEquals(data.size(), 3);
		assertFalse(function.apply(data).equals(null));
	}
}
