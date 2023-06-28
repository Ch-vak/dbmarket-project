package com.fdmgroup.dbmarket.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.dbmarket.model.IndexValues;
import com.fdmgroup.dbmarket.model.TradedValues;

/**
 * The IndexController class calculates the index values based on a list of
 * traded values. Tickers are weighted. It provides a method to calculate the
 * index values and returns them as a list of IndexValues objects.
 * 
 * The weights for each ticker are as follows:
 * <ol>
 * <li>TRX: 0.2</li>
 * <li>NGL: 0.4</li>
 * <li>MEGA: 0.3</li>
 * <li>ABC: 0.1</li>
 * </ol>
 * 
 * @author Chrysostomos Vakasiras
 * @see {@link com.fdmgroup.dbmarket.model.IndexValues}
 */
public class IndexController {

	/**
	 * Calculates the index values for a list of traded values. If on a day a ticker
	 * was not traded then it will append on the companyTicker the String N/A
	 * Tickers are being weighted
	 *
	 * @param trx  a list of traded values for TRX
	 * @param ngl  a list of traded values for NGL
	 * @param mega a list of traded values for MEGA
	 * @param abc  a list of traded values for ABC
	 * @return the index values for the given traded values
	 * @author Chrysostomos Vakasiras
	 */
	public List<IndexValues> calculateIndex(List<TradedValues> trx, List<TradedValues> ngl, List<TradedValues> mega,
			List<TradedValues> abc) {

		List<IndexValues> indexList = new ArrayList<IndexValues>();
		int listSize = trx.size();
		double trxWeight = 0.2;
		double nglWeight = 0.4;
		double megaWeight = 0.3;
		double abcWeight = 0.1;

		for (int i = 0; i < listSize; i++) {

			LocalDate date = trx.get(i).getDate();
			double closeValue = trxWeight * trx.get(i).getCloseValue() + nglWeight * ngl.get(i).getCloseValue()
					+ megaWeight * mega.get(i).getCloseValue() + abcWeight * abc.get(i).getCloseValue();

			double openValue = trxWeight * trx.get(i).getOpenValue() + nglWeight * ngl.get(i).getOpenValue()
					+ megaWeight * mega.get(i).getOpenValue() + abcWeight * abc.get(i).getOpenValue();

			double highestValue = trxWeight * trx.get(i).getHighestValue() + nglWeight * ngl.get(i).getHighestValue()
					+ megaWeight * mega.get(i).getHighestValue() + abcWeight * abc.get(i).getHighestValue();

			double lowestValue = trxWeight * trx.get(i).getLowestValue() + nglWeight * ngl.get(i).getLowestValue()
					+ megaWeight * mega.get(i).getLowestValue() + abcWeight * abc.get(i).getLowestValue();

			indexList.add(new IndexValues(date, openValue, closeValue, highestValue, lowestValue));
		}

		return indexList;
	}

}
