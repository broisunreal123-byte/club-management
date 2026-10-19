import java.util.Scanner;
import business.RegistrationManagement;

public class Main {

    public static void showMainMenu() {
        System.out.println("\n========== CLUB MANAGEMENT SYSTEM ==========");
        System.out.println("1. Club Management");
        System.out.println("2. Registration Management");
        System.out.println("0. Exit");
        System.out.println("============================================");
    }

    public static void showClubMenu() {
        System.out.println("\n========== CLUB MENU ==========");
        System.out.println("1. Add club");
        System.out.println("2. Display all clubs");
        System.out.println("3. Search club by ID");
        System.out.println("4. Search club by name");
        System.out.println("5. Update club");
        System.out.println("6. Delete club");
        System.out.println("7. Filter clubs by status");
        System.out.println("8. Filter clubs by category");
        System.out.println("0. Back to main menu");
        System.out.println("===============================");
    }

    public static void clubMenu(Scanner sc, ClubManagement clubManagement) {
        int choice;

        do {
            showClubMenu();
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1:
                    clubManagement.AddClub(sc);
                    break;

                case 2:
                    clubManagement.DisplayClubs();
                    break;

                case 3:
                    System.out.print("Enter club ID: ");
                    String id = sc.nextLine().trim();
                    Club club = clubManagement.SearchClubById(id);

                    if (club != null) {
                        club.displayClub();
                    } else {
                        System.out.println("Club not found.");
                    }
                    break;

                case 4:
                    clubManagement.SearchClubByName(sc);
                    break;

                case 5:
                    clubManagement.UpdateClub(sc);
                    break;

                case 6:
                    clubManagement.DeleteClub(sc);
                    break;

                case 7:
                    System.out.print("Enter status (Active/Inactive): ");
                    String status = sc.nextLine().trim();
                    clubManagement.FilterClubStatus(status);
                    break;

                case 8:
                    System.out.print("Enter category: ");
                    String category = sc.nextLine().trim();
                    clubManagement.FilterClubCategory(category);
                    break;

                case 0:
                    System.out.println("Back to main menu.");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ClubManagement clubManagement = new ClubManagement();
        RegistrationManagement registrationManagement = new RegistrationManagement();

        int choice;

        do {
            showMainMenu();
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1:
                    clubMenu(sc, clubManagement);
                    break;

                case 2:
                    registrationManagement.menu();
                    break;

                case 0:
                    System.out.println("Program ended.");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }
}

