import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class StockTradingApplication {
    private static final Scanner scanner = new Scanner(System.in);
    // Giving user a $10,000 starting balance
    private static final TradingEngineService engine = new TradingEngineService(10000.0);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("==================================================");
        System.out.println("       ENTERPRISE STOCK TRADING PLATFORM");
        System.out.println("==================================================");
        System.out.println("Welcome Trader! You have been granted $10,000.00");

        while (running) {
            printMenu();
            int choice = getIntInput("Enter your choice (1-5): ");

            switch (choice) {
                case 1:
                    displayMarket();
                    break;
                case 2:
                    handleBuy();
                    break;
                case 3:
                    handleSell();
                    break;
                case 4:
                    displayPortfolio();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting the trading platform. Have a profitable day!");
                    break;
                default:
                    System.out.println("Error: Invalid choice. Please select from 1 to 5.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- TRADING MENU ---");
        System.out.println("1. View Market Data (Prices will fluctuate)");
        System.out.println("2. Buy Stocks");
        System.out.println("3. Sell Stocks");
        System.out.println("4. View Portfolio & Performance");
        System.out.println("5. Exit");
    }

    private static void displayMarket() {
        engine.simulateMarketFluctuation(); // Make the market feel alive
        System.out.println("\n============= LIVE MARKET DATA =============");
        System.out.println(String.format("%-6s | %-20s | %s", "TICKER", "COMPANY", "CURRENT PRICE"));
        System.out.println("--------------------------------------------");
        for (Stock stock : engine.getMarketStocks()) {
            System.out.println(stock.toString());
        }
        System.out.println("============================================");
    }

    private static void handleBuy() {
        System.out.print("Enter Stock Ticker to BUY (e.g., AAPL): ");
        String ticker = scanner.nextLine().trim().toUpperCase();

        Stock stock = engine.getStockByTicker(ticker);
        if (stock == null) {
            System.out.println("Error: Invalid Ticker Symbol.");
            return;
        }

        System.out.printf("Current price of %s is $%.2f. Available Cash: $%.2f\n",
                ticker, stock.getCurrentPrice(), engine.getPortfolio().getCashBalance());

        int quantity = getIntInput("Enter quantity to buy: ");

        if (engine.buyStock(ticker, quantity)) {
            System.out.println("Success: Bought " + quantity + " shares of " + ticker + ".");
        } else {
            System.out.println("Failed: Insufficient funds or invalid quantity.");
        }
    }

    private static void handleSell() {
        System.out.print("Enter Stock Ticker to SELL (e.g., AAPL): ");
        String ticker = scanner.nextLine().trim().toUpperCase();

        int ownedQty = engine.getPortfolio().getStockQuantity(ticker);
        if (ownedQty == 0) {
            System.out.println("Error: You don't own any shares of " + ticker + ".");
            return;
        }

        Stock stock = engine.getStockByTicker(ticker);
        System.out.printf("You own %d shares of %s. Current market price: $%.2f\n",
                ownedQty, ticker, stock.getCurrentPrice());

        int quantity = getIntInput("Enter quantity to sell: ");

        if (engine.sellStock(ticker, quantity)) {
            System.out.println("Success: Sold " + quantity + " shares of " + ticker + ".");
        } else {
            System.out.println("Failed: You don't have enough shares or invalid quantity.");
        }
    }

    private static void displayPortfolio() {
        Portfolio p = engine.getPortfolio();
        System.out.println("\n============== YOUR PORTFOLIO ==============");
        System.out.printf("Available Cash : $%.2f\n", p.getCashBalance());
        System.out.println("--------------------------------------------");
        System.out.println(String.format("%-6s | %-10s | %s", "TICKER", "QUANTITY", "CURRENT VALUE"));
        System.out.println("--------------------------------------------");

        Map<String, Integer> holdings = p.getHoldings();
        if (holdings.isEmpty()) {
            System.out.println("No stocks currently held.");
        } else {
            for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
                String ticker = entry.getKey();
                int qty = entry.getValue();
                Stock stock = engine.getStockByTicker(ticker);
                double totalValue = stock.getCurrentPrice() * qty;
                System.out.println(String.format("%-6s | %-10d | $%.2f", ticker, qty, totalValue));
            }
        }
        System.out.println("--------------------------------------------");
        System.out.printf("TOTAL NET WORTH (Cash + Stocks) : $%.2f\n", engine.calculateTotalPortfolioValue());
        System.out.println("============================================");
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine(); // Clear bad input
            }
        }
    }
}
