# 📈 Enterprise-Level Stock Trading Simulation

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![OOP](https://img.shields.io/badge/Architecture-OOP-blue?style=for-the-badge)
![CodeAlpha](https://img.shields.io/badge/Internship-CodeAlpha-success?style=for-the-badge)

## 📌 Overview
Welcome to the **Stock Trading Platform**, a robust, console-based application engineered in pure Java. Developed as part of my Java Programming Internship at CodeAlpha, this project goes beyond basic procedural scripts to simulate a real-world trading ecosystem. 

The core focus of this architecture is **System Robustness, Clean Code, and Object-Oriented Programming (OOP)**. It is specifically designed to handle complex business logic, real-time data fluctuations, and strict edge-case validation flawlessly.

## 🔥 Core Technical Highlights

* 📉 **Dynamic Market Engine:** Engineered a custom fluctuation algorithm that simulates real-time market volatility, dynamically updating stock prices just like a live financial exchange.
* 💼 **Advanced Portfolio Management:** Seamless integration for buying and selling shares. The system calculates total net worth, tracks available cash balance, and manages active holdings using optimized Java Collections (`HashMap`).
* 🛡️ **Bulletproof Data Validation:** Built to never crash. It handles invalid tickers, insufficient funds, incorrect quantity inputs, and UI navigation errors gracefully with targeted, user-friendly exception handling.
* 🏛️ **Clean Architecture (Separation of Concerns):** Structured with a strict separation between Models (Data), Services (Business Logic), and the Application entry point, ensuring the codebase is scalable, highly modular, and industry-standard.

## 🛠️ Tech Stack & Architecture

* **Language:** Core Java
* **Design Principles:** DRY (Don't Repeat Yourself), Modular Design, High Cohesion
* **Data Structures Used:** `HashMap` (for portfolio tracking), `ArrayList` (for market listings)
* **Key Components:**
  * `Stock`: Model class representing individual market assets.
  * `Portfolio`: Manages user cash and owned shares.
  * `TradingEngineService`: The brain of the operation, handling transactions and market volatility.
  * `StockTradingApplication`: The interactive console UI.

## 🚀 How to Run the Project

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/AkramSE/CodeAlpha_StockTradingPlatform.git](https://github.com/AkramSE/CodeAlpha_StockTradingPlatform.git)
