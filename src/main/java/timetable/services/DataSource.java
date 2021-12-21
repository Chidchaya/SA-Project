package timetable.services;

import timetable.models.DetailGeneralBasic;
import timetable.models.WorkList;

public interface DataSource {
    WorkList getData();
    void setData(WorkList generalListData);
//    void readData() throws IOException;
}
