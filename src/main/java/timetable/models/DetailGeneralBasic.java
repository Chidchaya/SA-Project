package timetable.models;

public class DetailGeneralBasic {
    private String title;
    private String date;
    private String startTime;
    private String endTime;
    private String priority;
    private String status;
    private String category;
    private String size;
    private String smlxl;
    private String sendStatus;
    private String sendDate;


    public DetailGeneralBasic(String title, String date, String startTime, String endTime,
                              String priority, String status, String category,String size,
                              String smlxl, String sendStatus, String sendDate) {
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.status = status;
        this.category = category;
        this.size = size;
        this.smlxl = smlxl;
        this.sendStatus = sendStatus;
        this.sendDate = sendDate;
    }

    public String getTitle() {
        return title;
    }
    public String getDate() {
        return date;
    }
    public String getStartTime() {
        return startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public String getPriority() {
        return priority;
    }
    public String getStatus() { return status; }
    public String getCategory() { return category; }
    public String getSize() {return  size; }
    public String getSmlxl() {
        return smlxl;
    }
    public String getSendStatus() {return sendStatus; }
    public String getSendDate() {return sendDate; }

    public void setTitle(String title) { this.title = title; }
    public void setDate(String date) { this.date = date; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
    public void setPriority(String priority) { this.priority = priority; }
    public void setStatus(String status) { this.status = status; }
    public void setCategory(String category) { this.category = category; }
    public void setSize(String size) {
        this.size = size;
    }
    public void setSmlxl(String smlxl) {
        this.smlxl = smlxl;
    }
    public void setSendStatus(String sendStatus) {this.sendStatus = sendStatus; }
    public void setSendDate(String sendDate) {this.sendDate = sendDate; }

    public void update(String title, String date, String startTime, String endTime,
                       String priority, String status, String category, String size,
                       String smlxl, String sendStatus, String sendDate) {
        setTitle(title);
        setDate(date);
        setStartTime(startTime);
        setEndTime(endTime);
        setPriority(priority);
        setStatus(status);
        setCategory(category);
        setSize(size);
        setSmlxl(smlxl);
        setSendStatus(sendStatus);
        setSendDate(sendDate);
    }

    public void remove(String title, String date, String startTime, String endTime,
                       String priority, String status, String category, String size,
                       String smlxl, String sendStatus, String sendDate) {
        setTitle(title);
        setDate(date);
        setStartTime(startTime);
        setEndTime(endTime);
        setPriority(priority);
        setStatus(status);
        setCategory(category);
        setSize(size);
        setSmlxl(smlxl);
        setSendStatus(sendStatus);
        setSendDate(sendDate);
    }

    @Override
    public String toString() {
        return title + " (" + date + ")" + " Start time: " + startTime
                + " End time: " + endTime + " Priority: " + priority + " Status: "
                + status + " Category: " + category + " Size: " + size + " SMLXL: " +
                smlxl + " Send Status: " + sendStatus + " Send Date: " + sendDate;
    }
}
