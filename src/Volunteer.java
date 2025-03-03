import java.util.ArrayList;
import java.util.Scanner;
public class Volunteer extends StaffMember {
    private double salary;
    private ArrayList<StaffMember> staffList;
    ArrayList<Volunteer> volunteerList = new ArrayList<>();
    public Volunteer() {}

    //===== >> Constructor with param << =======
    public Volunteer(int id, String name, String address, double salary) {
        super(id, name, address);
        this.salary = salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public double getSalary() {
        return salary;
    }
    @Override
    public String getType() {
        return "Volunteer";
    }

    @Override
    public double pay() {
        return salary;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + address + " " + salary;
    }

    //=====>> user input (volunteer)
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("ID           = \t");
        id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name   = \t");
        name = sc.nextLine();
        System.out.print("Enter Address= \t");
        address = sc.nextLine();
        System.out.print("Enter Salary = \t");
        salary = sc.nextDouble();

        //===>> Volunteer
        Volunteer addNewVolunteer = new Volunteer(id, name, address, salary);
        volunteerList.add(addNewVolunteer);
        System.out.println("You Added " + name + " of the type volunteer successfully!");
    }
}