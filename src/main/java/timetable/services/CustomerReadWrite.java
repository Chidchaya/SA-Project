package timetable.services;

import timetable.models.CustomerConstructor;
import timetable.models.WorkList;

import java.io.*;

public class CustomerReadWrite implements DataSource {
    private String fileDirectoryName;
    private String fileName;
    private WorkList customerList;

    public CustomerReadWrite(String fileDirectoryName, String fileName) {
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
            //create obj data[index].trim()

            // เพิ่ม 2 พารา
            CustomerConstructor customerConstructor = new CustomerConstructor(data[0].trim(),
                    data[1].trim(), data[2].trim());
            //accountList.addToList(obj);
            customerList.addCustomer(customerConstructor);

        }
        reader.close();
    }

    public WorkList getData() {
        try {
            customerList = new WorkList();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return customerList;
    }

    public void setData(WorkList customerData) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (CustomerConstructor customer : customerData.toListCustomer()) {
                String line = customer.getName() + ","
                        + customer.getIdCustomer() + ","
                        + customer.getTelNumber();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }
}
