import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TradingEngineService {
    private final List<Stock> market;
    private final Portfolio userPortfolio;
    private final Random random;

    public TradingEngineService(double initialBalance) {
        this.market = new ArrayList<>();
        this.userPortfolio = new Portfolio(initialBalance);
        this.random = new Random();
        initializeMarket();
    }

    // Dummy market data initialize karna
    private void initializeMarket() {
        market.add(new Stock("AAPL", "Apple Inc.", 150.00));
        market.add(new Stock("GOOGL", "Alphabet Inc.", 2800.50));
        market.add(new Stock("TSLA", "Tesla Inc.", 700.25));
        market.add(new Stock("AMZN", "Amazon.com", 3300.00));
        market.add(new Stock("MSFT", "Microsoft Corp.", 299.99));
    }

    public List<Stock> getMarketStocks() {
        return market;
    }

    public Portfolio getPortfolio() {
        return userPortfolio;
    }

    public Stock getStockByTicker(String ticker) {
        for (Stock stock : market) {
            if (stock.getTicker().equalsIgnoreCase(ticker)) {
                return stock;
            }
        }
        return null; // Ticker not found
    }

    // Simulation: Har baar market dekhne par prices -3% se +3% tak change hongi
    public void simulateMarketFluctuation() {
        for (Stock stock : market) {
            double fluctuation = -3.0 + (6.0 * random.nextDouble());
            stock.updatePrice(fluctuation);
        }
    }

    public boolean buyStock(String ticker, int quantity) {
        Stock stock = getStockByTicker(ticker);
        if (stock == null || quantity <= 0) return false;

        double totalCost = stock.getCurrentPrice() * quantity;
        if (userPortfolio.getCashBalance() >= totalCost) {
            userPortfolio.deductCash(totalCost);
            userPortfolio.addStock(stock.getTicker(), quantity);
            return true;
        }
        return false; // Insufficient funds
    }

    public boolean sellStock(String ticker, int quantity) {
        Stock stock = getStockByTicker(ticker);
        if (stock == null || quantity <= 0) return false;

        int ownedQty = userPortfolio.getStockQuantity(stock.getTicker());
        if (ownedQty >= quantity) {
            double revenue = stock.getCurrentPrice() * quantity;
            userPortfolio.removeStock(stock.getTicker(), quantity);
            userPortfolio.addCash(revenue);
            return true;
        }
        return false; // Not enough stocks to sell
    }

    public double calculateTotalPortfolioValue() {
        double totalValue = userPortfolio.getCashBalance();
        for (String ticker : userPortfolio.getHoldings().keySet()) {
            Stock stock = getStockByTicker(ticker);
            int quantity = userPortfolio.getStockQuantity(ticker);
            if (stock != null) {
                totalValue += (stock.getCurrentPrice() * quantity);
            }
        }
        return totalValue;
    }
}
