package com.fdmgroup.dbmarket;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fdmgroup.dbmarket.csvreader.CsvReader;
import com.fdmgroup.dbmarket.model.IndexValues;
import com.fdmgroup.dbmarket.model.MarketData;
import com.fdmgroup.dbmarket.model.TradedValues;
import com.fdmgroup.dbmarket.utils.DataController;
import com.fdmgroup.dbmarket.utils.IndexController;

/**
 * <h1>It is the main entry point of the application.</h1>
 * <p>
 * It has a main method that does the following:
 * <ol>
 * <li>Loads Data from the CSV file using the CsvReader class</li>
 * <li>Initializes the IndexController and DataController classes</li>
 * <li>Calls the loadTradedValues() method from the DataController and stores
 * the results to Lists for each Company</li>
 * <li>Uses the Lists created above then calling the calculateIndex() method
 * from IndexController to populate index values for each day</li>
 * <li>Prints the results on the console</li>
 * </ol>
 * 
 * By default the CSV file is expected to be located at
 * <code>src/main/resources/test-market.csv</code>
 * </p>
 * .
 * 
 * @see {@link com.fdmgroup.dbmarket.utils.IndexController}
 *      {@link com.fdmgroup.dbmarket.utils.DataController}
 *      {@link com.fdmgroup.dbmarket.csvreader.CsvReader}
 * @author Chrysostomos Vakasiras
 */
public class MarketMain {

	public static void main(String[] args) {
		String path = "src/main/resources/test-market.csv";
		CsvReader reader = new CsvReader(path);
		Map<LocalDate, List<MarketData>> data = new HashMap<LocalDate, List<MarketData>>();
		data = reader.loadData();

		IndexController indexController = new IndexController();
		DataController tradedTickerData = new DataController();

		List<TradedValues> trx, abc, mega, ngl = new ArrayList<TradedValues>();
		List<IndexValues> index = new ArrayList<IndexValues>();

		trx = tradedTickerData.loadTradedValues(data, "TRX");
		abc = tradedTickerData.loadTradedValues(data, "ABC");
		ngl = tradedTickerData.loadTradedValues(data, "NGL");
		mega = tradedTickerData.loadTradedValues(data, "MEGA");

		index = indexController.calculateIndex(trx, ngl, mega, abc);

		for (int i = 0; i < trx.size(); i++) {
			System.out.println("---------------" + trx.get(i).getDate() + "------------");
			System.out.println("-------------------------------------");
			System.out.println(trx.get(i).toString());
			System.out.println(abc.get(i).toString());
			System.out.println(ngl.get(i).toString());
			System.out.println(mega.get(i).toString());

			System.out.println("-----------Index --------------");
			System.out.println(index.get(i).toString());
			System.out.println("-------------------------------------");
		}

	}
}
