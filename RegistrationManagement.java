
package business;

import models.Registration;
import java.util.ArrayList;
import java.util.Scanner;

public class RegistrationManagement {
    private ArrayList<Registration> list = new ArrayList<Registration>();
    private ArrayList<String> clubIds = new ArrayList<String>();
    private final Scanner sc;

    public RegistrationManagement(Scanner scanner) {
        this.sc = scanner;
        list.add(new Registration("K001", "SE01", "Nguyen Van An",
                "C001", "10/09/2024", "Active"));
        list.add(new Registration("K002", "SE02", "Tran Thi Binh",
                "C001", "11/09/2024", "Active"));
    }

    public RegistrationManagement() {
        this(new Scanner(System.in));
    }

    public ArrayList<Registration> getRegistrations() {
        return list;
    }

    public void setRegistrations(ArrayList<Registration> loadedRegistrations) {
        if (loadedRegistrations != null) {
            list = loadedRegistrations;
        }
    }

    public void setClubIds(ArrayList<String> ids) {
        clubIds = ids;
    }

    private boolean clubExists(String clubId) {
        for (String id : clubIds) {
            if (id.equalsIgnoreCase(clubId)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidDate(String date) {
        if (date.length() != 10) {
            return false;
        }
        if (date.charAt(2) != '/' || date.charAt(5) != '/') {
            return false;
        }
        for (int i = 0; i < date.length(); i++) {
            if (i != 2 && i != 5) {
                if (date.charAt(i) < '0' || date.charAt(i) > '9') {
                    return false;
                }
            }
        }
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(3, 5));
        int year = Integer.parseInt(date.substring(6, 10));
        if (year < 1 || month < 1 || month > 12) {
            return false;
        }
        int maxDay = 31;
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxDay = 30;
        }
        if (month == 2) {
            maxDay = 28;
            if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                maxDay = 29;
            }
        }
        if (day < 1 || day > maxDay) {
            return false;
        }
        return true;
    }

    public void AddRegister() {
        System.out.println("\n--- ADD NEW REGISTRATION ---");
        System.out.print("Enter register id: ");
        String regId = sc.nextLine().trim();
        if (regId.isEmpty() || regId.contains("|")) {
            System.out.println("Invalid register ID!");
            return;
        }
        for (Registration r : list) {
            if (r.getRegister_id().equalsIgnoreCase(regId)) {
                System.out.println("Error: Register ID already exists!");
                return;
            }
        }

        System.out.print("Enter student id: ");
        String studentId = sc.nextLine().trim();
        if (studentId.isEmpty() || studentId.contains("|")) {
            System.out.println("Invalid student ID!");
            return;
        }

        System.out.print("Enter student name: ");
        String studentName = sc.nextLine().trim();
        if (studentName.isEmpty() || studentName.contains("|")) {
            System.out.println("Invalid student name!");
            return;
        }

        System.out.print("Enter club id: ");
        String clubId = sc.nextLine().trim();
        if (clubId.isEmpty() || clubId.contains("|")) {
            System.out.println("Invalid club ID!");
            return;
        }
        if (!clubExists(clubId)) {
            System.out.println("Club ID does not exist!");
            return;
        }
        for (Registration r : list) {
            if (r.getName_id().equalsIgnoreCase(studentId) &&
                    r.getClub_id().equalsIgnoreCase(clubId)) {
                System.out.println("Student already registered in this club!");
                return;
            }
        }

        System.out.print("Enter join date (dd/MM/yyyy): ");
        String joinDate = sc.nextLine().trim();
        if (!isValidDate(joinDate)) {
            System.out.println("Invalid join date!");
            return;
        }

        System.out.print("Enter status (Active/Inactive): ");
        String status = sc.nextLine().trim();
        if (status.equalsIgnoreCase("Active")) {
            status = "Active";
        } else if (status.equalsIgnoreCase("Inactive")) {
            status = "Inactive";
        } else {
            System.out.println("Invalid status!");
            return;
        }

        Registration r = new Registration(regId, studentId, studentName,
                clubId, joinDate, status);
        list.add(r);
        System.out.println("Add registration successfully!");
    }

    public void DeleteRegister() {
        System.out.println("\n--- DELETE REGISTRATION ---");
        System.out.print("Enter register id to delete: ");
        String regId = sc.nextLine().trim();
        if (regId.isEmpty()) {
            System.out.println("Register ID cannot be empty!");
            return;
        }

        boolean found = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRegister_id().equalsIgnoreCase(regId)) {
                list.remove(i);
                found = true;
                System.out.println("Delete successfully!");
                break;
            }
        }
        if (!found) {
            System.out.println("Not found this register id!");
        }
    }

    public void SearchMember() {
        System.out.println("\n--- SEARCH MEMBER ---");
        System.out.print("Enter student id or club id to search: ");
        String keyword = sc.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("Search keyword cannot be empty!");
            return;
        }

        boolean found = false;
        System.out.println("Result:");
        for (Registration r : list) {
            if (r.getName_id().toLowerCase().contains(keyword.toLowerCase()) ||
                    r.getClub_id().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(r.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching member found!");
        }
    }

    public void ListStudents() {
        System.out.println("\n--- LIST STUDENTS BY CLUB ---");
        System.out.print("Enter club id: ");
        String clubId = sc.nextLine().trim();
        if (clubId.isEmpty()) {
            System.out.println("Club ID cannot be empty!");
            return;
        }
        if (!clubExists(clubId)) {
            System.out.println("Club ID does not exist!");
            return;
        }

        boolean found = false;
        System.out.println("Students in club " + clubId + ":");
        for (Registration r : list) {
            if (r.getClub_id().equalsIgnoreCase(clubId)) {
                System.out.println("- " + r.getName_id() + " : "
                        + r.getStudent_name());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No students found in this club!");
        }
    }

    public void menu() {
        int choice;
        do {
            System.out.println("\n=== REGISTRATION MENU ===");
            System.out.println("1. Add new registration");
            System.out.println("2. Delete registration");
            System.out.println("3. Search member");
            System.out.println("4. List students by club");
            System.out.println("0. Back to main menu");
            System.out.print("Your choice: ");
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                choice = -1;
            }
            switch (choice) {
                case 1:
                    AddRegister();
                    break;
                case 2:
                    DeleteRegister();
                    break;
                case 3:
                    SearchMember();
                    break;
                case 4:
                    ListStudents();
                    break;
                case 0:
                    System.out.println("Returning...");
                    break;
                default:
                    System.out.println("Invalid choice! Please choose from 0 to 4.");
            }
        } while (choice != 0);
    }
}
