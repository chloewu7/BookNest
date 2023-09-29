package entity;

public class Salary {

    private int salary;
    private int bonus;
    private int deduction;

    public Salary(int salary, int bonus, int deduction) {
        this.salary = salary;
        this.bonus = bonus;
        this.deduction = deduction;
    }

    public static SalaryBuilder builder() {return new SalaryBuilder();}

    public static class SalaryBuilder{

        private int salary;
        private int bonus;
        private int deduction;

        SalaryBuilder(){}

        public SalaryBuilder salary(int salary){
            this.salary = salary;
            return this;
        }

        public SalaryBuilder bonus(int bonus) {
            this.bonus = bonus;
            return this;
        }

        public SalaryBuilder deduction(int deduction) {
            this.deduction = deduction;
            return this;
        }

        public Salary build() {return new Salary(salary, bonus, deduction); }

    }

    public String toString() {
        return "entity.Salary{" +
                "salary='" + salary + '\'' +
                ", bonus='" + bonus + '\'' +
                ", deduction=" + deduction +
                '}';
    }

    public int getSalary() {return salary;}

    public int getBonus() {return bonus;}

    public int getDeduction() {return deduction;}

}
