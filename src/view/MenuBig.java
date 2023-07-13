package view;

import model.Customer;

import java.io.IOException;

public class MenuBig extends Menu {
    Customer cus = new Customer();
    public MenuBig(String title, String[] s) {
        super(title, s);
    }
    @Override
    public boolean execute(int choice) {
        switch (choice) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:
                return true;

            default:
                break;
        }
        return false;
    }
    public static void main(String[] args) {
        MenuBig menu = new MenuBig("Library Management\n--------------------", new String[]{"List All Customer", "Search Customer", "Add New Customer", "Delete Customer", "Save", "Update Customer", "Exit"});
        menu.run();

    }
}