# dbmarket-project

My solution to the Project for DB

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [Output](#output)

## Installation

Requires JavaSE 17  and JUnit 5.

## Usage

Starting on **MarketMain** class we load Data from the Csv file using the CsvReader class.  <br /> Then using the **DataController**  and **IndexController** classes 
we create Lists the hold the values to be displayed.  <br />
The csvreader directory holds the **CsvReader** class that handles the whole reading process for a csv file.  <br />
The data are first splited to a map that uses the day as a key and stores the transactions details as values.  <br />
My approach was that dates are unique and therefore can be used as keys.  <br />
While instantiating this class you need to pass a String representing the file path. <br />
This class uses the ; as delimiter to separate the values.  <br />
The model folder contains the Objects that are created and later stored in the Lists. <br />
Finally the utils folder holds 2 classes **DataController** and **IndexController** that handle the Daily traded values and daily Index respectively. <br />
The **TradedValuesDataUtils** class contains the Functions that are used to filter out the expected data. <br />   <br />
**Summing Up**
1. Start with MarketMain 
2. Move to CsvReader
3. DataController
4. TradedDataUtils
5. IndexController
<br />

Models explained bellow. <br />

**IndexValues** <br />
This class represents the values of an index for a specific date. <br />
  **date:**     The date of the index values. <br />
  **openaValue:** The opening value of the index.<br />
  **closeValue:** The closing value of the index.<br />
  **highestValue:** The highest value of the index.<br />
  **lowestValue:**  The lowest value of the index.<br />
  <br />

**MarketData**   <br />
 This class represents market data for a specific transaction.  <br />

**transactionDate:**  The date of the transaction. <br />
**transactionTime:**  The time of the transaction. <br />
**companyTicker:**    The ticker symbol of the company. <br />
**price:**            The price of the security. <br />
**securitiesTraded:** The number of securities traded.' <br />
 <br />

**TradedValues** <br />
This class represents the traded values of a company for a specific date.<br />

**date:**          The date of the traded values.<br />
**companyTicker:** The ticker symbol of the company.<br />
**openValue:**     The opening value of the company's stock.<br />
**closeValue:**    The closing value of the company's stock.<br />
**highestValue:**  The highest value of the company's stock.<br />
**lowestValue:**   The lowest value of the company's stock.<br />
**tradedVolume:**  The volume of securities traded.<br />
  

## Contributing

Anyone can contribute. Some additional improvement could be caching the backup values. Also for the visualization.

## Output
```console
All available tickers in the data set: [NGL, MEGA, TRX, ABC]
--------TRX Processing--------
Processing Day: 2023-06-09
Available tickers on this day: [NGL, MEGA, ABC]
--------TRX Processed-------
--------TRX Processing--------
Processing Day: 2023-06-08
Available tickers on this day: [NGL, MEGA, TRX, ABC]
--------TRX Processed-------
.
.
.
.
---------------2023-06-01------------
-------------------------------------
TradedValues [date=2023-06-01, companyTicker=TRX, openValue=3997.9, closeValue=3884.68, highestValue=4038.11, lowestValue=3730.47, tradedVolume=11348798271.36]
TradedValues [date=2023-06-01, companyTicker=ABC, openValue=999.99, closeValue=999.87, highestValue=1000.09, lowestValue=999.75, tradedVolume=145050474.40]
TradedValues [date=2023-06-01, companyTicker=NGL, openValue=5000.83, closeValue=5117.97, highestValue=5197.55, lowestValue=4926.73, tradedVolume=22053540293.64]
TradedValues [date=2023-06-01, companyTicker=MEGA, openValue=2997.95, closeValue=3011.26, highestValue=3015.11, lowestValue=2927.89, tradedVolume=4169982578.54]
-----------Index --------------
IndexValues [date=2023-06-01, openaValue=3799,30, closeValue=3827,49, highestValue=3891,18, lowestValue=3695,13]
-------------------------------------
```
