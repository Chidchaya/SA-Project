package timetable.services;

import timetable.models.CheckStock;
import timetable.models.StockConstructor;

import java.io.*;

public class CheckStockReadWrite{
    private String fileDirectoryName;
    private String fileName;
    private CheckStock stockList;

    public CheckStockReadWrite(String fileDirectoryName, String fileName) {
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    private void checkFileIsExisted() {
        File file = new File(fileDirectoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Cannot create " + filePath);
            }
        }
    }

    //    @Override
    public void readData() throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            StockConstructor stockConstructor = new StockConstructor(data[0], Double.parseDouble(data[1]),
                    data[2]);
            stockList.addStock(stockConstructor);

        }
        reader.close();
    }

    public CheckStock getData() {
        try {
            stockList = new CheckStock();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return stockList;
    }

    public void setData(CheckStock checkStock) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (StockConstructor stockConstructor : checkStock.getStocks()) {
                String line = stockConstructor.getStock() + ","
                        + stockConstructor.getBalance() + ","
                        + stockConstructor.getPin();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }
}
