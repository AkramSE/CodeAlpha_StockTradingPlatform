public class Stock {
    private final String ticker;
    private final String companyName;
    private double currentPrice;

    public Stock(String ticker, String companyName, double currentPrice) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.currentPrice = currentPrice;
    }

    public String getTicker() {
        return ticker;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    // Market simulation ke liye price update logic
    public void updatePrice(double fluctuationPercentage) {
        double change = this.currentPrice * (fluctuationPercentage / 100);
        this.currentPrice += change;
        if (this.currentPrice < 1.0) {
            this.currentPrice = 1.0; // Minimum price limit
        }
    }

    @Override
    public String toString() {
        return String.format("%-6s | %-20s | $%.2f", ticker, companyName, currentPrice);
    }
}