//package timetable.services;
//
//import timetable.models.DetailForward;
//import timetable.models.DetailGeneralBasic;
//import timetable.models.DetailWeekly;
//import timetable.models.WorkList;
//
//import java.io.*;
//
//public class ForwardReadWrite implements DataSource{
//    private String fileDirectoryName;
//    private String fileName;
//    private WorkList forwardList;
//
//    public ForwardReadWrite(String fileDirectoryName, String fileName) {
//        this.fileDirectoryName = fileDirectoryName;
//        this.fileName = fileName;
//        checkFileIsExisted();
//    }
//
//    private void checkFileIsExisted() {
//        File file = new File(fileDirectoryName);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        String filePath = fileDirectoryName + File.separator + fileName;
//        file = new File(filePath);
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                System.err.println("Cannot create " + filePath);
//            }
//        }
//    }
//
//    //@Override
//    public void readData() throws IOException {
//        String filePath = fileDirectoryName + File.separator + fileName;
//        File file = new File(filePath);
//        FileReader fileReader = new FileReader(file);
//        BufferedReader reader = new BufferedReader(fileReader);
//        String line = "";
//        while ((line = reader.readLine()) != null) {
//            String[] data = line.split(",");
//            //create obj data[index].trim()
//            DetailForward detailforward = new DetailForward(data[0].trim()
//                    , data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim()
//                    , data[5].trim(), data[6].trim(), data[7].trim(), data[8].trim(),
//                    data[9].trim());
//            //accountList.addToList(obj);
//            forwardList.addForward(detailforward);
//
//        }
//        reader.close();
//    }
//
//    @Override
//    public WorkList getData() {
//        try {
//            forwardList = new WorkList();
//            readData();
//        } catch (FileNotFoundException e) {
//            System.err.println(this.fileName + " not found");
//        } catch (IOException e) {
//            System.err.println("IOException from reading " + this.fileName);
//        }
//        return forwardList;
//    }
//
//    @Override
//    public void setData(WorkList forwardData) {
//        String filePath = fileDirectoryName + File.separator + fileName;
//        File file = new File(filePath);
//        FileWriter fileWriter = null;
//        try {
//            fileWriter = new FileWriter(file);
//            BufferedWriter writer = new BufferedWriter(fileWriter);
//            for (DetailForward forward : forwardData.toListForward()) {
//                String line = forward.getTitle() + ","
//                        + forward.getDate() + ","
//                        + forward.getStartTime() + ","
//                        + forward.getEndTime() + ","
//                        + forward.getPriority() + ","
//                        + forward.getStatus() + ","
//                        + forward.getCategory() + ","
//                        + forward.getWorkerName() + ","
//                        + forward.getForwardingFormat() + ","
//                        + forward.getFileExtension();
//                writer.append(line);
//                writer.newLine();
//            }
//            writer.close();
//        } catch (IOException e) {
//            System.err.println("Cannot write " + filePath);
//        }
//    }
//}
