package entity;

public class Department {

    private String name; //department name;
    private int number; //number of employee;

    public Department(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public static DepartmentBuilder builder() {return new DepartmentBuilder();}

    public static class DepartmentBuilder {
        private String name;
        private int number;

        DepartmentBuilder(){}

        public DepartmentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DepartmentBuilder number(int number) {
            this.number = number;
            return this;
        }

        public Department build() {return new Department(name, number);}
    }

    public String toString() {
        return "entity.Department{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public String getName() {return name;}

    public int getNumber() {return number;}


}
