import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;
import java.util.ArrayList;
import java.util.Scanner;

//=====>> Whole Method
public class Method {
    Scanner sc = new Scanner(System.in);
    private int lastAssignedId;
    ArrayList<StaffMember> staff = new ArrayList<>();

    private String RESET = "\u001B[0m";
    private String GREEN = "\u001B[32m";
    private String RED = "\u001B[31m";

    public Method() {
        this.staff = new ArrayList<>();
        this.lastAssignedId = 0;
        initializeDefaultData();
    }

    // ====>> តម្លៃដើម៧
    private void initializeDefaultData() {
        staff.add(new Volunteer(1, "John Doe", "123 Main St", 0.0));
        staff.add(new EmployeeSalary(2, "Jane Smith", "456 Elm St", 5000.0, 1000.0));
        staff.add(new HourlySalaryEmployee(3, "Alice Johnson", "789 Oak St", 40, 20.0));
        staff.add(new Volunteer(4, "Bob Brown", "321 Pine St", 0.0));
        staff.add(new EmployeeSalary(5, "Charlie Davis", "654 Maple St", 6000.0, 1200.0));
        staff.add(new HourlySalaryEmployee(6, "Eve Wilson", "987 Cedar St", 35, 25.0));
        staff.add(new Volunteer(7, "Frank Moore", "135 Birch St", 0.0));
    }

    // ====>> display table
    public static void displayTable() {
        Table table = new Table(1);
        table.addCell("====[+] STAFF MANAGEMENT SYSTEM [+]====");
        table.addCell("1. Insert Employee");
        table.addCell("2. Update Employee");
        table.addCell("3. Display Employee");
        table.addCell("4. Remove Employee");
        table.addCell("5. Exit");
        System.out.println(table.render());
    }

    // ====>> GenerateNewId
    private int generateNewId() {
        lastAssignedId++;
        return lastAssignedId;
    }

    // ====>> Input employee
    public void InsertEmployee() {
        System.out.println("=========== Input Employee ============");
        displayTable();

        while (true) {
            System.out.println("Please choose 1 type: ");
            Table t = new Table(4);
            t.addCell("1. Volunteer");
            t.addCell("2. Salaried Employee");
            t.addCell("3. Hourly Employee");
            t.addCell("4. Back");
            System.out.println(t.render());

            System.out.print("=> Enter Type Number: ");
            int typeOption = sc.nextInt();
            sc.nextLine();

            switch (typeOption) {
                case 1:
                    Volunteer volunteer = new Volunteer();
                    volunteer.inputData();
                    int newVolunteerId = generateNewId();
                    staff.add(new Volunteer(newVolunteerId, volunteer.name, volunteer.address, volunteer.getSalary()));
                    System.out.println("New Volunteer added with ID: " + newVolunteerId);
                    break;

                case 2:
                    EmployeeSalary salariedEmployee = new EmployeeSalary();
                    salariedEmployee.inputData();
                    int newSalariedId = generateNewId();
                    staff.add(new EmployeeSalary(newSalariedId, salariedEmployee.name, salariedEmployee.address, salariedEmployee.getSalary(), salariedEmployee.getBonus()));
                    System.out.println("New Salaried Employee added with ID: " + newSalariedId);
                    break;

                case 3:
                    HourlySalaryEmployee hourlyEmployee = new HourlySalaryEmployee();
                    hourlyEmployee.inputData();
                    int newHourlyId = generateNewId();
                    staff.add(new HourlySalaryEmployee(newHourlyId, hourlyEmployee.name, hourlyEmployee.address, hourlyEmployee.getHourWorked(), hourlyEmployee.getRate()));
                    System.out.println("New Hourly Employee added with ID: " + newHourlyId);
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // ======>> Display employee
    public void DisplayEmployee() {
        System.out.println("============ Display Employee ============");
        Table t = new Table(9, BorderStyle.UNICODE_BOX_HEAVY_BORDER);
        t.addCell(GREEN+"Type"+RESET, new CellStyle(CellStyle.HorizontalAlign.center), 1);
        t.addCell(GREEN+"ID"+RESET, new CellStyle(CellStyle.HorizontalAlign.center), 1);
        t.addCell(GREEN+"Name"+RESET, new CellStyle(CellStyle.HorizontalAlign.center), 1);
        t.addCell(GREEN+"Address"+RESET, new CellStyle(CellStyle.HorizontalAlign.center), 1);
        t.addCell(GREEN+"Salary"+RESET, new CellStyle(CellStyle.HorizontalAlign.center), 1);
        t.addCell(GREEN+"Bonus"+RESET, new CellStyle(CellStyle.HorizontalAlign.center), 1);
        t.addCell(GREEN+"Hour"+RESET, new CellStyle(CellStyle.HorizontalAlign.center), 1);
        t.addCell(GREEN+"Rate"+RESET, new CellStyle(CellStyle.HorizontalAlign.center), 1);
        t.addCell(GREEN+"Pay"+RESET, new CellStyle(CellStyle.HorizontalAlign.center), 1);

        staff.forEach(staffMember -> {
            t.addCell(staffMember.getType());
            t.addCell(String.valueOf(staffMember.id));
            t.addCell(staffMember.name);
            t.addCell(staffMember.address);

            if (staffMember instanceof EmployeeSalary) {
                EmployeeSalary salariedEmployee = (EmployeeSalary) staffMember;
                t.addCell(String.valueOf(salariedEmployee.getSalary()));
                t.addCell(String.valueOf(salariedEmployee.getBonus()));
                t.addCell("");
                t.addCell("");
            } else if (staffMember instanceof HourlySalaryEmployee) {
                HourlySalaryEmployee hourlyEmployee = (HourlySalaryEmployee) staffMember;
                t.addCell("");
                t.addCell("");
                t.addCell(String.valueOf(hourlyEmployee.getHourWorked()));
                t.addCell(String.valueOf(hourlyEmployee.getRate()));
            } else if (staffMember instanceof Volunteer) {
                Volunteer volunteer = (Volunteer) staffMember;
                t.addCell(String.valueOf(volunteer.getSalary()));
                t.addCell("");
                t.addCell("");
                t.addCell("");
            }

            t.addCell(String.valueOf(staffMember.pay()));
        });

        System.out.println(t.render());
    }

    // =====>>> Update employee
    public void UpdateEmployee() {
        System.out.println("============ Update Employee ============");
        System.out.print("=> Enter ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        StaffMember staffMemberToUpdate = staff.stream()
                                               .filter(staffMember -> staffMember.id == id)
                                               .findFirst()
                                               .orElse(null);

        if (staffMemberToUpdate == null) {
            System.out.println("Employee with ID " + id + " not found.");
            return;
        }

        System.out.println("Employee Details:");
        Table t = new Table(2);
        t.addCell("ID");
        t.addCell(String.valueOf(staffMemberToUpdate.id));
        t.addCell("Name");
        t.addCell(staffMemberToUpdate.name);
        t.addCell("Address");
        t.addCell(staffMemberToUpdate.address);
        t.addCell("Type");
        t.addCell(staffMemberToUpdate.getType());
        System.out.println(t.render());

        //====>> Select 1 to update

        System.out.println("Choose one column to update:");
        System.out.println("1. Name\t 2. Address \t3. Salary \t4. Cancel");
        System.out.print("=> Select Column Number: ");
        int column = sc.nextInt();
        sc.nextLine();

        switch (column) {
            // =====>> 1. update by Name
            case 1:
                    System.out.println("Enter new Name: ");
                    String newName = sc.nextLine();
                    staffMemberToUpdate.name = newName;
                    break;
            // =====>> 2. update by address
            case 2:
                    System.out.println("Enter new Address: ");
                    String newAddress = sc.nextLine();
                    staffMemberToUpdate.address = newAddress;
                    break;
            // =====>> 3. update by Salary
            case 3:
                    if (staffMemberToUpdate instanceof EmployeeSalary) {
                        System.out.println("Enter new Salary: ");
                        double newSalary = sc.nextDouble();
                        ((EmployeeSalary) staffMemberToUpdate).setSalary(newSalary);
                    } else if (staffMemberToUpdate instanceof HourlySalaryEmployee) {
                        System.out.print("Enter new Hours Worked: ");
                        int newHours = sc.nextInt();
                        ((HourlySalaryEmployee) staffMemberToUpdate).setHourWorked(newHours);
                    } else if (staffMemberToUpdate instanceof Volunteer) {
                        System.out.print("Enter new Salary: ");
                        double newSalary = sc.nextDouble();
                        ((Volunteer) staffMemberToUpdate).setSalary(newSalary);
                    }
                    break;
            case 0:
                    System.out.println("Canceled");
                    break;
            default:
                    System.out.println("Invalid option. Update canceled.");
        }
        System.out.println("Employee details updated successfully.");
    }

    // =====>>> Remove employee
    public void RemoveEmployee() {
        System.out.println("=========== Remove Employee ===========");
        System.out.print("=> Enter ID to remove: ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean removed = staff.removeIf(staffMember -> staffMember.id == id);

        if (removed) {
            System.out.println("Employee with ID " + id + " removed successfully.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }
}