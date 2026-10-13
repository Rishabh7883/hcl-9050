package assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.model;

public abstract class LibraryItem implements Comparable<LibraryItem>, Borrowable {
    private static int counter = 1001;

    private final int id;
    private String title;
    private int year;
    private boolean available;

    public LibraryItem(String title,int year) {
        if (title==null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title cannot be empty");
        }

        if (year<1900 || year>java.time.Year.now().getValue()) {
            throw new IllegalArgumentException("Invalid year");
        }

        this.id=counter++;
        this.title=title;
        this.year=year;
        this.available=true;
    }

    public abstract double lateFee(int daysLate);

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        if (title==null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title=title;
    }

    public int getYear() {
        return year;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrow() {
        available = false;
    }

    public void giveBack() {
        available = true;
    }

    @Override
    public int compareTo(LibraryItem other) {
        return title.compareToIgnoreCase(other.title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        if (!(obj instanceof LibraryItem)) return false;

        LibraryItem other=(LibraryItem) obj;
        return id==other.id;
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(id);
    }

    @Override
    public String toString(){
        return "ID: " + id + ", Title: " + title + ", Year: " + year
                + ", Available: " + available;
    }
}