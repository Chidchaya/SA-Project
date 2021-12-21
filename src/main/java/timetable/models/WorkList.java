package timetable.models;

import timetable.controllers.GeneralList;
import timetable.services.GeneralReadWrite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class WorkList {
    private ArrayList<DetailGeneralBasic> generalBasicArrayList;
    private ArrayList<DetailCategory> categoryArrayList;
    private ArrayList<CustomerConstructor> customerConstructorArrayList;
    private ArrayList<StockConstructor> stockConstructorArrayList;

    public WorkList() throws IOException {
        generalBasicArrayList = new ArrayList<>();
        categoryArrayList = new ArrayList<>();
        customerConstructorArrayList = new ArrayList<>();
        stockConstructorArrayList = new ArrayList<>();
    }

    public DetailGeneralBasic searchName(String name){
        for (DetailGeneralBasic id : generalBasicArrayList){
            if(id.getTitle().equalsIgnoreCase(name)) {
                return id;
            }
        }
        return null;
    }

    public void addGeneral(DetailGeneralBasic generalBasic) {
        generalBasicArrayList.add(generalBasic);
    }
    public void addCategory(DetailCategory category) {categoryArrayList.add(category);}
    public void addCustomer(CustomerConstructor customerConstructor) {customerConstructorArrayList.add(customerConstructor);}
    public void addStock(StockConstructor stockConstructor) {stockConstructorArrayList.add(stockConstructor);}

    public ArrayList<DetailGeneralBasic> toGeneralList() {
        return generalBasicArrayList;
    }
    public ArrayList<DetailCategory> toCategoryList() {return categoryArrayList; }
    public ArrayList<CustomerConstructor> toCustomerList() {return customerConstructorArrayList; }
    public ArrayList<StockConstructor> toStockList() {return stockConstructorArrayList; }

    public ArrayList<DetailGeneralBasic> toListGeneral() {
        ArrayList<DetailGeneralBasic> list = new ArrayList<>();
        for (DetailGeneralBasic general:generalBasicArrayList) {
                list.add(general);
        }
        return list;
    }

    public ArrayList<DetailCategory> toListCategory() {
//        ArrayList<DetailCategory> list = new ArrayList<>();
//        for (DetailCategory category:categoryArrayList) {
//            if (category.getClass() == DetailCategory.class) {
//                list.add((DetailCategory) category);
//            }
//        }
//        return list;
        return categoryArrayList;
    }

    public ArrayList<CustomerConstructor> toListCustomer() {
        ArrayList<CustomerConstructor> list = new ArrayList<>();
        for (CustomerConstructor customerConstructor:customerConstructorArrayList) {
            list.add(customerConstructor);
        }
        return list;
    }

    public ArrayList<StockConstructor> toListStock() {
        ArrayList<StockConstructor> list = new ArrayList<>();
        for (StockConstructor stockConstructor:stockConstructorArrayList) {
            list.add(stockConstructor);
        }
        return list;
    }

//    public boolean removeGeneral(DetailGeneralBasic select, WorkList) {
//        for(DetailGeneralBasic detailGeneralBasic : generalBasicArrayList) {
//            if (detailGeneralBasic.getTitle().equals(name)) {
//                return false;
//            }
//        }
//        return true;
//    }

    public void removeGeneral(DetailGeneralBasic select) {
        generalBasicArrayList.remove(select);
    }
//    public void removeWeekly(DetailWeekly select) {
//        weeklyArrayList.remove(select);
//    }

    public boolean checkNameGeneral(String name) {
        for(DetailGeneralBasic detailGeneralBasic : generalBasicArrayList) {
            if (detailGeneralBasic.getTitle().equalsIgnoreCase(name)) {
                return false;
            }
        }
        return true;
    }

    public int runIdOrder(){
        int i;
        ArrayList<Integer> ids = getIdList();
        for (i = 1; i <= ids.size() ; i++) {
            if (ids.get(i-1) > i )
                return i;
        }
        return i;

    }

    public int runCustomerOrder(){
        int i;
        ArrayList<Integer> Customers = getCustomersList();
        for (i = 0; i < Customers.size() ; i++) {
            if (Customers.get(i) > i )
                return i;
        }
        return i;

    }
    public ArrayList<Integer> getIdList(){
        ArrayList<Integer> ids = new ArrayList<>();
        for (DetailGeneralBasic list : generalBasicArrayList) {
            ids.add(Integer.parseInt(list.getTitle()));
        }
        Collections.sort(ids);
        return ids;
    }

    public ArrayList<Integer> getCustomersList(){
        ArrayList<Integer> Customers = new ArrayList<>();
        for (DetailGeneralBasic list : generalBasicArrayList) {
            Customers.add(Integer.parseInt(list.getSize()));
        }
        Collections.sort(Customers);
        return Customers;
    }

    public int runCustomerId(){
        int i;
        ArrayList<Integer> ids = getCustomerIdList();
        for (i = 1; i <= ids.size() ; i++) {
            if (ids.get(i-1) > i )
                return i;
        }
        return i;
    }

    public ArrayList<Integer> getCustomerIdList(){
        ArrayList<Integer> ids = new ArrayList<>();
        for (CustomerConstructor list : customerConstructorArrayList) {
            ids.add(Integer.parseInt(list.getIdCustomer()));
        }
        Collections.sort(ids);
        return ids;
    }

    public ArrayList<String> getCustomerIdString(){
        ArrayList<String> ids = new ArrayList<>();
        for (CustomerConstructor list : customerConstructorArrayList) {
            ids.add(list.toString());
        }
        return ids;
    }

//    public String getCustomerIdString(){
//        int a = Integer.parseInt(id);
//        for (CustomerConstructor list : customerConstructorArrayList) {
//            Integer.toString(list.getIdCustomer());
//        }
//        return
//    }

    public boolean checkNameCategory(String name) {
        for(DetailCategory detailCategory: categoryArrayList) {
            if (detailCategory.getNameCategory().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkCustomerTel(String tel) {
        for(CustomerConstructor customerConstructor: customerConstructorArrayList) {
            if (customerConstructor.getTelNumber().equals(tel)) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<DetailGeneralBasic> generalCount (String category) {
        ArrayList<DetailGeneralBasic> general = new ArrayList<>();
        for (DetailGeneralBasic g : generalBasicArrayList) {
            if (g.getCategory().equals(category)) {
                general.add(g);
            }
        }
        return general;
    }
}
