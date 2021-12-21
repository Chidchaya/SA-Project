//package timetable.models;
//
//public class DetailForward extends DetailGeneralBasic {
//    private String workerName;
//    private String forwardingFormat;
//    private String fileExtension;
//
//    //constructor
//    public DetailForward(String title, String date, String startTime, String endTime, String priority,
//                         String status, String category,  String workerName, String forwardingFormat, String fileExtension) {
//        super(title, date, startTime, endTime, priority, status, category);
//        this.workerName = workerName;
//        this.forwardingFormat = forwardingFormat;
//        this.fileExtension = fileExtension;
//    }
//
//    //getter
//    public String getWorkerName() { return workerName; }
//    public String getForwardingFormat() {return forwardingFormat; }
//    public String getFileExtension() {return  fileExtension;}
//
//    //setter
//    public void setWorkerName(String workerName) { this.workerName = workerName; }
//    public void setForwardingFormat(String forwardingFormat) { this.forwardingFormat = forwardingFormat; }
//    public void setFileExtension(String fileExtension) { this.fileExtension = fileExtension; }
//
//    //update
//    public void update(String title, String date, String startTime, String endTime,
//                       String priority, String status, String category,
//                       String forwardingFormat, String fileExtension) {
//        setTitle(title);
//        setDate(date);
//        setStartTime(startTime);
//        setEndTime(endTime);
//        setPriority(priority);
//        setStatus(status);
//        setCategory(category);
//        setForwardingFormat(forwardingFormat);
//        setFileExtension(fileExtension);
//    }
//
//    public void workerNameForward(String workerName) {
//        setWorkerName(getWorkerName() + " =>> " + workerName);
//    }
//
//    @Override
//    public String toString() {
//        return getTitle() + " (" + getDate() + ")" + " Start time: " + getStartTime()
//                + " End time: " + getEndTime() + "Priority: " + getPriority() + "Status: "
//                + getStatus() + " Category: " + getCategory() + " Worker Name: "
//                + getWorkerName() + " Forwarding Format: " + getForwardingFormat()
//                + " File Extension: " + getFileExtension();
//    }
//}
