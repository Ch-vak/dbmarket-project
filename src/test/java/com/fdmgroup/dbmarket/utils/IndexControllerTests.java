package com.fdmgroup.dbmarket.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.dbmarket.model.IndexValues;
import com.fdmgroup.dbmarket.model.TradedValues;

class IndexControllerTests {

	List<TradedValues> trx = new ArrayList<TradedValues>();
	List<TradedValues> ngl = new ArrayList<TradedValues>();
	List<TradedValues> mega = new ArrayList<TradedValues>();
	List<TradedValues> abc = new ArrayList<TradedValues>();

	@BeforeEach
	void setUp() {
		trx.add(new TradedValues(LocalDate.now(), "TRX", 10.0, 100.0, 100.0, 10.0, null));
		ngl.add(new TradedValues(LocalDate.now(), "NGL", 10.0, 100.0, 100.0, 10.0, null));
		mega.add(new TradedValues(LocalDate.now(), "MEGA", 10.0, 100.0, 100.0, 10.0, null));
		abc.add(new TradedValues(LocalDate.now(), "ABC", 10.0, 100.0, 100.0, 10.0, null));

	}

	@Test
	void test_IndexControllerCalculatestheCorrectValues() {
		IndexController indexController = new IndexController();

		List<IndexValues> indexList = indexController.calculateIndex(trx, ngl, mega, abc);

		double closeValueResult = 100.0;
		double maxValueResult = 100.0;
		double minValueResult = 10.0;
		double openValueResult = 10.0;

		assertEquals(1, indexList.size());
		assertEquals(LocalDate.now(), indexList.get(0).getDate());
		assertEquals(closeValueResult, indexList.get(0).getCloseValue(), 0.001);
		assertEquals(maxValueResult, indexList.get(0).getHighestValue(), 0.001);
		assertEquals(minValueResult, indexList.get(0).getLowestValue(), 0.001);
		assertEquals(openValueResult, indexList.get(0).getOpenaValue(), 0.001);

	}

}
