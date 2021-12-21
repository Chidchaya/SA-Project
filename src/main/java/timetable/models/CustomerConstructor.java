package timetable.models;

public class CustomerConstructor {
    private String name;
    private String idCustomer;
    private String telNumber;

    public CustomerConstructor(String name, String idCustomer, String telNumber) {
        this.name = name;
        this.idCustomer = idCustomer;
        this.telNumber = telNumber;
    }

    public String getNameCustomer() {
        return name;
    }

    public String getName() { return name; }
    public String getIdCustomer() {return idCustomer; }
    public String getTelNumber() {return telNumber; }

    public void setName(String name) { this.name = name; }
    public void setIdCustomer(String id) { this.idCustomer = idCustomer; }
    public void setTelNumber(String telNumber) { this.telNumber = telNumber; }

    @Override
    public String toString() {
        return "Id: " + idCustomer + "  Name: " + name;
    }
}
