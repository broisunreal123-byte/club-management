package business;

import models.Registration;
import java.util.ArrayList;
import java.util.Scanner;

public class RegistrationManagement {
    private ArrayList<Registration> list = new ArrayList<Registration>();
    private final Scanner sc;

    public RegistrationManagement(Scanner scanner) {
        this.sc = scanner;

        // Keep original sample registrations
        list.add(new Registration("K001", "SE01", "Nguyen Van An",
                                  "C001", "10/09/2024", "Active"));
        list.add(new Registration("K002", "SE02", "Tran Thi Binh",
                                  "C001", "11/09/2024", "Active"));
    }

    public RegistrationManagement() {
        this(new Scanner(System.in));
    }

    // LAB 6: Get data for saving
    public ArrayList<Registration> getRegistrations() {
        return list;
    }

    // LAB 6: Load data from text file
    public void setRegistrations(ArrayList<Registration> loadedRegistrations) {
        if (loadedRegistrations != null) {
            list = loadedRegistrations;
        }
    }

    public void AddRegister() {
        System.out.println("\n--- ADD NEW REGISTRATION ---");
        System.out.print("Enter register id: ");
        String regId = sc.nextLine().trim();

        if (regId.isEmpty()) {
            System.out.println("Register ID cannot be empty!");
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

        System.out.print("Enter student name: ");
        String studentName = sc.nextLine().trim();

        System.out.print("Enter club id: ");
        String clubId = sc.nextLine().trim();

        System.out.print("Enter join date: ");
        String joinDate = sc.nextLine().trim();

        System.out.print("Enter status: ");
        String status = sc.nextLine().trim();

        Registration r = new Registration(regId, studentId, studentName,
                                          clubId, joinDate, status);
        list.add(r);

        System.out.println("Add registration successfully!");
    }

    public void DeleteRegister() {
        System.out.println("\n--- DELETE REGISTRATION ---");
        System.out.print("Enter register id to delete: ");
        String regId = sc.nextLine().trim();

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

        boolean found = false;
        System.out.println("Result:");

        for (Registration r : list) {
            if (r.getName_id().contains(keyword) ||
                r.getClub_id().contains(keyword)) {
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
