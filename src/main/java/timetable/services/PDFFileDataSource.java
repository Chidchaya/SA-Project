package timetable.services;

import timetable.models.WorkList;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PDFFileDataSource {
    private String fileDirectoryName;
    private String fileName;

    public PDFFileDataSource(String fileDirectoryName, String fileName) {
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

    public void openPDFFile() throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        try {
            File file = new File(filePath);
            Desktop.getDesktop().open(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
