package assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.model;

public class Magazine extends LibraryItem {
    private int issueNumber;

    public Magazine(String title, int year, int issueNumber) {
        super(title, year);

        if (issueNumber <= 0) {
            throw new IllegalArgumentException("Invalid issue number");
        }

        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    @Override
    public double lateFee(int daysLate) {
        return Math.max(0, daysLate) * 1.0;
    }

    @Override
    public String toString() {
        return super.toString() + ", Issue Number: " + issueNumber;
    }
}