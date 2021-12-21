package timetable.models;

public class DetailCategory {
    private String nameCategory;

    //constructor
    public DetailCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    //getter
    public String getNameCategory() {
        return nameCategory;
    }

    //setter
    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    //update button
//    public void update(String nameCategory) {
//        setNameCategory(nameCategory);
//    }

    @Override
    public String toString() {
        return "DetailCategory{" +
                "nameCategory='" + nameCategory + '\'' +
                '}';
    }
}
