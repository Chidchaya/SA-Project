package timetable.models;

public class StockConstructor {
    private String pin;
    private String stock;
    private double balance;

    public StockConstructor(String stock, double balance, String pin){
        this.stock = stock;
        this.balance = balance;
        this.pin = pin;
    }

    public void deposit(double amount){
        if(amount > 0){
            balance += amount;
        }
    }

    public void withdraw(double amount){
        if(amount > 0 && balance >= amount){
            balance -= amount;
        }
    }

    public void setPin(String pin){
        this.pin = pin;
    }

    public String getPin() {
        return pin;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "CheckStock{" +
                "pin='" + pin + '\'' +
                ", account='" + stock + '\'' +
                ", balance=" + balance +
                '}';
    }
}
