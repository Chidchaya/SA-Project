package timetable.models;

import timetable.models.StockConstructor;

import java.util.ArrayList;

public class CheckStock {
    private ArrayList<StockConstructor> stocks;
    private StockConstructor currentStocks;

    public CheckStock(){
        stocks = new ArrayList<>();
    }

    public void addStock(StockConstructor sct){
        stocks.add(sct);
    }

    public boolean checkPin(String pin){
        for(StockConstructor sct : stocks){
            if(sct.getPin().equals(pin)){
                currentStocks = sct;
                return true;
            }
        }
        currentStocks = null;
        return false;
    }

    public boolean deposit(double amount){
        if(amount > 0){
            currentStocks.deposit(amount);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean withdraw(double amount){
        if(amount > 0 && amount <= currentStocks.getBalance()){
            currentStocks.withdraw(amount);
            return true;
        }
        else{
            return false;
        }
    }

    public double showStock(String type){
        for (StockConstructor stockConstructor : stocks) {
            if (stockConstructor.getPin().equals(type))
                return stockConstructor.getBalance();
        }
        //Not have type in data system
        return -1.00;
    }

    public StockConstructor getCurrentStocks(){
        return currentStocks;
    }

    public ArrayList<StockConstructor> getStocks() {
        return stocks;
    }

    @Override
    public String toString() {
        return stocks.toString();
    }
}
