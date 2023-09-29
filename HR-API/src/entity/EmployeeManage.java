package entity;

public class EmployeeManage {

    //Employee Management

    private String id; //employee's id

    private String status; //part-time or full-time

    private double contractLength; //Length of contract

    public EmployeeManage (String id, String status, double contractLength) {
        this.id = id;
        this.status = status;
        this.contractLength = contractLength;

    }

    public static EmployeeBuilder builder() {return new EmployeeBuilder(); }

    public static class EmployeeBuilder {

        private String id;
        private String status;
        private double contractLength;

        EmployeeBuilder(){

        }

        public EmployeeBuilder id(String id) {
            this.id = id;
            return this;
        }

        public EmployeeBuilder status(String status) {
            this.status = status;
            return this;
        }

        public EmployeeBuilder contractLength(double contractLength){
            this.contractLength = contractLength;
            return this;
        }

        public EmployeeManage build() {return new EmployeeManage(id, status, contractLength);}

    }

    public String  toString() {
        return "Employee{" + "id=' " + id + '\'' + ", status=' " + status + '\'' +
                ", contractLength=" + contractLength + '}';
    }

    public String getId() {return id;}

    public String getStatus() {return status;}

    public double getContractLength() {return contractLength;}





}
