package assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library;


import assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.exception.ItemNotAvailableException;
import assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.exception.LibraryException;
import assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.model.*;
import assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.service.LibraryService;
import assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.util.CollectionUtils;

import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final LibraryService service = new LibraryService();

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("----------LIBRARY MENU---------");
                System.out.println("1. Add item");
                System.out.println("2. Register member");
                System.out.println("3. Borrow");
                System.out.println("4. Return");
                System.out.println("5. Waitlist");
                System.out.println("6. List items");
                System.out.println("7. Search");
                System.out.println("8. Export report");
                System.out.println("0. Exit");
                System.out.print("Choice: ");

                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        addItem();
                        break;
                    case 2:
                        registerMember();
                        break;
                    case 3:
                        borrowItem();
                        break;
                    case 4:
                        returnItem();
                        break;
                    case 5:
                        waitlist();
                        break;
                    case 6:
                        listItems();
                        break;
                    case 7:
                        searchItems();
                        break;
                    case 8:
                        service.exportReport("library-report.txt");
                        System.out.println("Report exported");
                        break;
                    case 0:
                        System.out.println("End");
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (LibraryException | IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    private static void addItem() {
        System.out.println("1. Book  2. Magazine  3. DVD");
        System.out.print("Type: ");
        int type =Integer.parseInt(sc.nextLine());

        System.out.print("Title: ");
        String title =sc.nextLine();

        System.out.print("Year: ");
        int year =Integer.parseInt(sc.nextLine());
        LibraryItem item;

        switch (type){
            case 1:
                System.out.print("Author: ");
                String author = sc.nextLine();
                System.out.print("ISBN: ");
                String isbn = sc.nextLine();
                item=new Book(title,year,author,isbn);
                break;
            case 2:
                System.out.print("Issue number: ");
                int issue =Integer.parseInt(sc.nextLine());
                item=new Magazine(title, year, issue);
                break;
            case 3:
                System.out.print("Duration in minutes: ");
                int duration = Integer.parseInt(sc.nextLine());
                item = new DVD(title, year, duration);
                break;
            default:
                throw new IllegalArgumentException("Invalid item type");
        }

        service.addItem(item);
        System.out.println("Item added: " + item);
    }

    private static void registerMember() {
        System.out.print("Member id: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        Member member = new Member(id, name, email);
        service.registerMember(member);

        System.out.println("Member registered");
    }

    private static void borrowItem(){
        System.out.print("Member id: ");
        int memberId = Integer.parseInt(sc.nextLine());

        System.out.print("Item id: ");
        int itemId = Integer.parseInt(sc.nextLine());

        try {
            service.borrowItem(memberId, itemId);
            System.out.println("Item issued: "+service.getItem(itemId));
        } catch (LibraryException e) {
            System.out.println("ERROR: "+e.getMessage());

            if (e instanceof ItemNotAvailableException) {
                System.out.print("Add to waitlist? (y/n): ");

                if (sc.nextLine().equalsIgnoreCase("y")) {
                    service.addToWaitlist(memberId, itemId);
                    System.out.println("Added to waitlist");
                }
            } else {
                throw e;
            }
        }
    }

    private static void returnItem() {
        System.out.print("Member id: ");
        int memberId = Integer.parseInt(sc.nextLine());

        System.out.print("Item id: ");
        int itemId = Integer.parseInt(sc.nextLine());

        System.out.print("Late by (days): ");
        int days = Integer.parseInt(sc.nextLine());

        if (days<0) {
            throw new IllegalArgumentException("Days cannot be negative");
        }

        double fee =service.returnItem(memberId,itemId,days);
        System.out.println("Late fee: "+fee);
    }

    private static void waitlist() {
        System.out.print("Item id: ");
        int itemId = Integer.parseInt(sc.nextLine());

        service.showWaitlist(itemId);
    }

    private static void listItems() {
        List<LibraryItem> items = service.getItems();

        System.out.println("1. Sort by title");
        System.out.println("2. Sort by year descending, then title");
        System.out.print("Choice: ");

        int choice = Integer.parseInt(sc.nextLine());

        if (choice == 1) {
            Collections.sort(items);
        } else if (choice == 2) {
            items.sort(
                    Comparator.comparingInt(LibraryItem::getYear)
                            .reversed()
                            .thenComparing(LibraryItem::getTitle,String.CASE_INSENSITIVE_ORDER));
        } else {
            System.out.println("Invalid choice");
            return;
        }
        for (LibraryItem item:items) {
            System.out.println(item);
        }
        service.printItemStatistics();
    }

    private static void searchItems() {
        System.out.print("Search title: ");
        String title = sc.nextLine();

        List<LibraryItem> results=service.search(title);

        if (results.isEmpty()) {
            System.out.println("No items found");
        } else {
            for (LibraryItem item:results) {
                System.out.println(item);
            }
            LibraryItem max =CollectionUtils.maxOf(results);
            System.out.println("Alphabetically greatest title: "+max.getTitle());
        }
    }
}
