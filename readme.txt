Executing:

1. cd to target folder
2. execute the folowing command: java -jar super-simple-stock-1.0-SNAPSHOT.jar
**Note: if you want to specify how many trades should be generated add the number of trades as an extra parameter: java -jar super-simple-stock-1.0-SNAPSHOT.jar 50

Classes:

SimpleStock: The entry point of our applcation. Randomly generate trades and calculate the desired metrics.
StockCalculator: Service which makes the calculations.

model/* : This folder contains the domain classes such as Stock, Trade...etc.
repository/*: Contains the interface and the (in-memory) implementation of the repository which stores and loads trades and stocks.