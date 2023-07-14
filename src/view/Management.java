package view;

import checked_input.Input;
import checked_input.Validator;
import control.CompanyManagement;
import model.Company;
import model.Customer;

import java.util.ArrayList;

public class Management extends Menu {
    //Customer cus = new Customer();
    CompanyManagement cm = new CompanyManagement();
    static Company c = new Company();

    public Management(String title, String[] s) {
        super(title, s);
    }

    @Override
    public boolean execute(int choice) {
        switch (choice) {
            case 1:
                cm.displayALL();
                break;
            case 2:
                Menu menuSearch = new Menu("Customer Searching\n--------------------", new String[]{"Find by customerID", "Find by Name", "Find by Phone", "Find by Birth", "Exit"}) {
                    @Override
                    public boolean execute(int choice) {
                        switch (choice) {
                            case 1:
                                cm.displayById();
                                break;
                            case 2:
                                cm.displayByName();
                                break;
                            case 3:
                                cm.displayByPhone();
                                break;
                            case 4:
                                cm.displayByBirth();
                                break;
                            case 5:
                                return true;
                            default:
                                break;
                        }
                        return false;
                    }

                };
                menuSearch.run();
                break;
            case 3:
                cm.addCustomer();
                break;
            case 4:
                Menu menuDelete = new Menu("Customer Delete\n--------------------", new String[]{"Delete by cusomerID", "Delete by Name", "Delete by Phone", "Delete by Birth", "Exit"}) {
                    @Override
                    public boolean execute(int choice) {
                        switch (choice) {
                            case 1:
                                cm.deleteId();
                                break;
                            case 2:
                                cm.deleteName();
                                break;
                            case 3:
                                cm.deletePhone();
                                break;
                            case 4:
                                cm.deleteBirth();
                                break;
                            case 5:
                                return true;
                            default:
                                break;
                        }
                        return false;
                    }

                };
                menuDelete.run();
                break;
            case 5:
                if (c.saveFile()) {
                    System.out.println("Lưu thành công");
                } else {
                    System.out.println("Lỗi rồi!");
                }
                break;
            case 6:
                String id = Input.enterString("Mời nhập id cần update", Validator.REGEX_USER_ID);
                ArrayList<Customer> update = c.search((c) -> c.getId().equals(id));
                if (update.isEmpty()) {
                    System.out.println("The Customer not exist");
                } else {
                    Menu menuUpdate = new Menu("Customer Update \n--------------------", new String[]{"Update Name", "Update Phone", "FUpdate Birth", "Exit"}) {
                        @Override
                        public boolean execute(int choice) {
                            switch (choice) {
                                case 1:
                                    String name = Input.enterString("Mời nhập tên update ", Validator.REGEX_FULL_NAME_VN);
                                    if (c.update(update.get(0), name, "NAME")) {
                                        System.out.println("Update success");
                                    } else {
                                        System.out.println("Update fail");
                                    }
                                    break;
                                case 2:
                                    String phone = Input.enterString("Mời nhập phone mới: ", Validator.REGEX_PHONE_NUMBER);
                                    if (c.update(update.get(0), phone, "PHONE")) {
                                        System.out.println("Update success");
                                    } else {
                                        System.out.println("Update fail");
                                    }
                                    break;
                                case 3:
                                    String date = Input.enterDate("Mời nhập ngày sinh mới(dd/MM/yy)");
                                    if (c.update(update.get(0), date, "BIRTHDAY")) {
                                        System.out.println("Update success");
                                    } else {
                                        System.out.println("Update fail");
                                    }

                                    break;
                                case 4:
                                    return true;

                                default:
                                    break;

                            }
                            return false;
                        }
                    };
                    menuUpdate.run();
                }
                System.out.println("Detele Customer Success");

                break;
            case 7:
                return true;

            default:
                break;
        }
        return false;
    }

    public static void main(String[] args) {
        Company.config();
        Management menu = new Management("Library Management\n--------------------", new String[]{"List All Customer", "Search Customer", "Add New Customer", "Delete Customer", "Save", "Update Customer", "Exit"});
        menu.run();

    }
}