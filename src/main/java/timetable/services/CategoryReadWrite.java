package timetable.services;

import timetable.controllers.CategoryController;
import timetable.models.DetailCategory;
import timetable.models.DetailGeneralBasic;
import timetable.models.WorkList;

import java.io.*;

public class CategoryReadWrite implements DataSource {
    private String fileDirectoryName;
    private String fileName;
    private WorkList categoryList;

    public CategoryReadWrite(String fileDirectoryName, String fileName) {
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
//            String[] data = line.split(",");
            //create obj data[index].trim()
            DetailCategory detailCategory = new DetailCategory(line);
            //accountList.addToList(obj);
            categoryList.addCategory(detailCategory);
//            System.out.println(categoryList.toListCategory().toString());
        }
        reader.close();
    }

    @Override
    public WorkList getData() {
        try {
            categoryList = new WorkList();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return categoryList;
    }

    @Override
    public void setData(WorkList categoryData) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (DetailCategory category : categoryData.toListCategory()) {
                String line = category.getNameCategory() ;
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }
}
