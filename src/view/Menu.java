package view;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu {

    protected String title;
    protected ArrayList<String> list = new ArrayList();

    public Menu() {
    }

    public Menu(String title, String[] s) {
        this.title = title;
        for (String item : s) {
            list.add(item);
        }
    }

    public void display() {
        System.out.println(title);
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    public int getChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter selection: ");
        int choice = Integer.parseInt(scanner.nextLine());
        return choice;
    }

//    public abstract void execute(int choice);

//    public void run() {
//        int choice;
//        label:
////        do {
////            display();//hiện menu
////            choice = getChoice();
////            execute(choice);//xử lý
////        } while (choice > 0 && choice < list.size());
//        while (true) {
//            display();
//            choice = getChoice();
//            execute(choice);
//            if (choice == list.size()-1) {
//                break label;
//            }
//        }
//    }
public void run(){
    while(true){
        int choice = getChoice();
        if(choice<=0 || choice> list.size()){
            System.out.println("You must choice from 1 to "+list.size());
            continue;
        }
        boolean isExit = execute(choice);
        if(isExit){
            break;
        }
    }
}
    public abstract boolean execute(int choice);

}
