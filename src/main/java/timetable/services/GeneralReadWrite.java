package timetable.services;

import timetable.models.DetailGeneralBasic;
import timetable.models.WorkList;

import java.io.*;

public class GeneralReadWrite implements DataSource {
    private String fileDirectoryName;
    private String fileName;
    private WorkList generalBasicList;

    public GeneralReadWrite(String fileDirectoryName, String fileName) {
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
            DetailGeneralBasic detailGeneralBasic = new DetailGeneralBasic(data[0].trim()
                    , data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(),
                    data[5].trim(), data[6].trim(), data[7].trim(), data[8].trim(),
                    data[9].trim(), data[10].trim());
            //accountList.addToList(obj);
            generalBasicList.addGeneral(detailGeneralBasic);

        }
        reader.close();
    }

    @Override
    public WorkList getData() {
        try {
            generalBasicList = new WorkList();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return generalBasicList;
    }

    @Override
    public void setData(WorkList generalData) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (DetailGeneralBasic general : generalData.toListGeneral()) {
                String line = general.getTitle() + ","
                        + general.getDate() + ","
                        + general.getStartTime() + ","
                        + general.getEndTime() + ","
                        + general.getPriority() + ","
                        + general.getStatus() + ","
                        + general.getCategory() + ","
                        + general.getSize() + ","
                        + general.getSmlxl() + ","
                        + general.getSendStatus() + ","
                        + general.getSendDate();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }
}
