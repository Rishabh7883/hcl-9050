package assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {
    private final int id;
    private String name;
    private String email;
    private final List<LibraryItem> borrowedItems = new ArrayList<>();

    public Member(int id, String name, String email) {
        this.id = id;
        setName(name);
        setEmail(email);
    }

    public Member(int id, String name, String email,
                  List<LibraryItem> borrowedItems) {
        this(id, name, email);

        if (borrowedItems != null) {
            this.borrowedItems.addAll(borrowedItems);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public void setEmail(String email) {
        if (email==null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.email=email;
    }

    public List<LibraryItem> getBorrowedItems(){
        return Collections.unmodifiableList(
                new ArrayList<>(borrowedItems));
    }

    public void addBorrowedItem(LibraryItem item){
        borrowedItems.add(item);
    }

    public void removeBorrowedItem(LibraryItem item){
        borrowedItems.remove(item);
    }

    public int getBorrowedCount(){
        return borrowedItems.size();
    }

    @Override
    public String toString(){
        return "Member ID: " + id + ", Name: " + name
                + ", Email: " + email
                + ", Borrowed Items: " + borrowedItems.size();
    }
}