import java.util.HashMap; 
import java.util.Map;

public class Portfolio {
    private double cashBalance;
    private final Map<String, Integer> holdings; // Ticker -> Quantity

    public Portfolio(double initialBalance) {
        this.cashBalance = initialBalance;
        this.holdings = new HashMap<>();
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public Map<String, Integer> getHoldings() {
        return holdings;
    }

    public void addCash(double amount) {
        this.cashBalance += amount;
    }

    public void deductCash(double amount) {
        this.cashBalance -= amount;
    }

    public void addStock(String ticker, int quantity) {
        holdings.put(ticker, holdings.getOrDefault(ticker, 0) + quantity);
    }

    public void removeStock(String ticker, int quantity) {
        int currentQty = holdings.getOrDefault(ticker, 0);
        if (currentQty == quantity) {
            holdings.remove(ticker); // Sab bech diye toh list se nikal do
        } else {
            holdings.put(ticker, currentQty - quantity);
        }
    }

    public int getStockQuantity(String ticker) {
        return holdings.getOrDefault(ticker, 0);
    }
}
