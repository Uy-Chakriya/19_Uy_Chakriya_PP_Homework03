import java.util.Scanner;
public class HourlySalaryEmployee extends StaffMember{
    private int hoursWorked;
    private double rate;
    public HourlySalaryEmployee() {}
    public HourlySalaryEmployee(int id,
                                String name,
                                String address,
                                int hoursWorked,
                                double rate) {
        super(id, name, address);
        this.hoursWorked = hoursWorked;
        this.rate = rate;
    }
    // ========>> Pay
    @Override
    public double pay() {
        return hoursWorked * rate;
    }

    @Override
    public String getType() {
        return "Hourly Employee";
    }

    //=========>> User Input Data
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Name    = \t");
        name = sc.nextLine();
        System.out.print("Enter Address = \t");
        address = sc.nextLine();
        System.out.print("Enter Hours Worked= \t");
        hoursWorked = sc.nextInt();
        System.out.print("Enter Rate    = \t");
        rate = sc.nextDouble();
    }

    public void setHourWorked(int newHours) {
        hoursWorked = newHours;
    }

    public int getHourWorked() {
        return hoursWorked;
    }

    public double getRate() {
        return rate;
    }
}