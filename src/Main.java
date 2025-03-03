import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //====>> 7 members as default
        List<StaffMember> staffList = new ArrayList<>();
        staffList.add(new Volunteer(1, "Alice", "Kep", 1000));
        staffList.add(new EmployeeSalary(2, "Jonh", "Phnom Penh", 5000, 500));
        staffList.add(new HourlySalaryEmployee(3, "Mina", "Koh Kong", 40, 25));
        staffList.add(new Volunteer(4, "David", "Phnom Penh", 900));
        staffList.add(new EmployeeSalary(5, "Dev", "Seam Reab", 6000, 1000));
        staffList.add(new HourlySalaryEmployee(6, "SuSaJS", "KomPong Spue", 35, 30));
        staffList.add(new Volunteer(7, "Jesorn", "Kom Pot", 950));

//=========>>> 5 OPTIONS <<<=========

        Method method = new Method();
        int option;
        do {
            Method.displayTable();
            System.out.print("Choose an option: \t\t");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    method.InsertEmployee();
                    break;
                case 2:
                    method.UpdateEmployee();
                    break;
                case 3:
                    method.DisplayEmployee();
                    break;
                case 4:
                    method.RemoveEmployee();
                    break;
                case 5:
                    System.out.println("Exited from the system !\t");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.\t");
            }
        } while (option != 5);
    }
}