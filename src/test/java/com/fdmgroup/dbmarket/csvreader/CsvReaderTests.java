package com.fdmgroup.dbmarket.csvreader;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.dbmarket.model.MarketData;

@ExtendWith(MockitoExtension.class)
public class CsvReaderTests {

	@Test
	public void testLoadData_WhenCorrectPathIsPassed() throws NumberFormatException, IOException {
		CsvReader csvReader = new CsvReader("src/main/resources/test-market.csv");
		Map<LocalDate, List<MarketData>> marketDataArray = csvReader.loadData();

		Assert.assertNotNull(marketDataArray);
	}

	@Test
	public void testLoadData_WhenIncorrectPathIsPassed() throws NumberFormatException, IOException {
		CsvReader csvReader = new CsvReader("path/to/csv/file.csv");
		Map<LocalDate, List<MarketData>> marketDataArray = csvReader.loadData();

		Assert.assertNull(marketDataArray);
	}

}
