package entity;
public class Attendance {

    private double overtime; //Hours
    private int absence; //Days
    private int vacation; //Days

    public  Attendance(double overtime, int absence, int vacation) {
        this.overtime = overtime;
        this.absence = absence;
        this.vacation = vacation;
    }

    public static AttendanceBuilder builder() {return new AttendanceBuilder(); }

    public static class AttendanceBuilder {

        private  double overtime;
        private int absence;
        private int vacation;

        AttendanceBuilder(){
    }

    public AttendanceBuilder overtime(double overtime) {
        this.overtime = overtime;
        return this;
    }

    public AttendanceBuilder absence(int absence) {
            this.absence = absence;
            return this;
    }

    public AttendanceBuilder vacation(int vacation) {
            this.vacation = vacation;
            return this;
    }

    public Attendance build() {return new Attendance(overtime, absence, vacation);}

    }

    public String toString() {
        return "Attendance{" +
                "overtime='" + overtime + '\'' +
                ", absence='" + absence + '\'' +
                "vacation='" + vacation +
                '}';
    }

    public double getOvertime() {return overtime;}

    public int getAbsence() {return absence;}

    public int getVacation() {return vacation;}


}
