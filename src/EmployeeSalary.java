import java.util.Scanner;
public class EmployeeSalary extends StaffMember {
    private double salary;
    private double bonus;
    public EmployeeSalary() {}

    //====>> EmployeeSalary
    public EmployeeSalary(int id,
                          String name,
                          String address,
                          double salary,
                          double bonus)
    {
        super(id, name, address);
        this.salary = salary;
        this.bonus = bonus;
    }
    //====>> Payment
    @Override
    public double pay() {
        return salary + bonus;
    }

    @Override
    public String getType() {
        return "Employee Salary";
    }
    //===>> input data
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Name: \t");
        name = sc.nextLine();
        System.out.print("Enter Address: \t");
        address = sc.nextLine();
        System.out.print("Enter Salary: \t");
        salary = sc.nextDouble();
        System.out.print("Enter Bonus: \t");
        bonus = sc.nextDouble();
    }
    //===>> get salary
    public double getSalary() {
        return salary;
    }
    //===>> salary bonus
    public double getBonus() {
        return bonus;
    }
}