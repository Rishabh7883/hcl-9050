package assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.model;


public class DVD extends LibraryItem {
    private int durationMinutes;

    public DVD(String title,int year,int durationMinutes){
        super(title, year);

        if (durationMinutes<=0) {
            throw new IllegalArgumentException("Invalid duration");
        }
        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes(){
        return durationMinutes;
    }

    @Override
    public double lateFee(int daysLate) {
        return Math.max(0,daysLate)*5.0;
    }

    @Override
    public String toString() {
        return super.toString() + ", Duration: " + durationMinutes + " minutes";
    }
}
