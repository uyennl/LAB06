package control;

import checked_input.Input;
import checked_input.Validator;
import model.Company;
import model.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;

public class CompanyManagement {
    public void displayALL() {
        Company.customerList.forEach(c -> System.out.println(c));
    }

    public void addCustomer() {
        String id = Input.enterString("Id", Validator.REGEX_USER_ID, (i) -> {
            for (Customer c : Company.customerList) {
                if (c.getId().equals(i)) {
                    return true;
                }

            }
            return false;
        });
        String name = Input.enterString("Name", Validator.REGEX_FULL_NAME_VN);
        String phone = Input.enterString("Phone", Validator.REGEX_PHONE_NUMBER);
        LocalDate birth = Input.enterDate("Birth(dd/MM/yyyy)", "dd/MM/yyyy");
        Customer cs = new Customer(id,name,phone,birth);
        Company.customerList.add(cs);
    }

    public ArrayList<Customer> search(Predicate<Customer> p) {
        ArrayList<Customer> cs = new ArrayList<>();
        for (Customer c : Company.customerList) {
            if (p.test(c)) {
                cs.add(c);
            }
        }
        return cs;
    }

    public void displayById() {
        String id = Input.enterString("Id", Validator.REGEX_USER_ID);
        ArrayList<Customer> search = search(i -> i.getId().equals(id));
        if (search.isEmpty()) {
            System.out.println("Empty");
        } else {
            search.forEach(i -> System.out.println(i));
        }


    }
    public void displayByName(){
        String name = Input.enterString("Name",Validator.REGEX_FULL_NAME_VN);
        ArrayList<Customer> search = search(i->i.getName().equals(name));
        if(search.isEmpty()){
            System.out.println("Empty");
        }
        else{
            search.forEach(i-> System.out.println(i));
        }
    }
    public void displayByPhone(){
        String num = Input.enterString("Phone",Validator.REGEX_PHONE_NUMBER_SEARCH);
        ArrayList<Customer> search = search(i->i.getPhone().startsWith(num));
        if(search.isEmpty()){
            System.out.println("Empty");
        }
        else{
            search.forEach(i-> System.out.println(i));
        }
    }
    public void displayByBirth(){
        LocalDate birth = Input.enterDate("Birth(dd/MM/yyyy)","(dd/MM/yyyy)");
        ArrayList<Customer> search = search(i->i.getBirth().equals(birth));
        if(search.isEmpty()){
            System.out.println("Empty");
        }
        else{
            search.forEach(i-> System.out.println(i));
        }
    }

//    public void deleteId(){
//        String id = Input.enterString("Id",Validator.REGEX_USER_ID);
//        ArrayList<Customer> search = search(i->i.getBirth().equals(id));
//        if(search.isEmpty()){
//            System.out.println("Empty");
//        }
//        else{
//            Company.customerList.remove(search.get(0));
//        }
//    }
public void deleteId() {
    String id = Input.enterString("ID delete", Validator.REGEX_USER_ID);
    ArrayList<Customer> delete = search((c) -> c.getId().equals(id));
    if (delete.isEmpty()) {
        System.out.println("The Customer does not exist");
    } else {
        System.out.println("Selected Customer(s):");
        for (Customer customer : delete) {
            System.out.println(customer.getId());
        }

        System.out.println("Do you want to delete?");

        String a = Input.enterString("(y/n)", Validator.REGEX_QUESTION);
        if (a.equals("y")) {
            delete.forEach(p -> Company.customerList.remove(p));
            System.out.println("Delete Customer Success");

            // Hiển thị danh sách khách hàng còn lại
            System.out.println("Remaining Customers:");
            for (Customer customer : Company.customerList) {
                System.out.println(customer.getId());
            }
        }
    }
}
    public void deleteName(){
        String name = Input.enterString("Name",Validator.REGEX_FULL_NAME_VN);
        ArrayList<Customer> search = search(i->i.getBirth().equals(name));
        if(search.isEmpty()){
            System.out.println("Empty");
        }
        else{
            Company.customerList.remove(search.get(0));
        }
    }

    public void deletePhone(){
        String phone = Input.enterString("Name",Validator.REGEX_PHONE_NUMBER_SEARCH);
        ArrayList<Customer> search = search(i->i.getBirth().equals(phone));
        if(search.isEmpty()){
            System.out.println("Empty");
        }
        else{
            Company.customerList.remove(search.get(0));
        }
    }


}
