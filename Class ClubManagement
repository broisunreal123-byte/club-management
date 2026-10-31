
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ClubManagement {
    private ArrayList<Club> clubs;

    public ClubManagement() {
        clubs = new ArrayList<Club>();
    }

    // LAB 6: Get club list for saving
    public ArrayList<Club> getClubs() {
        return clubs;
    }

    // LAB 6: Load club list from file
    public void setClubs(ArrayList<Club> loadedClubs) {
        if (loadedClubs != null) {
            clubs = loadedClubs;
        }
    }

    public void AddClub(Scanner sc) {
        System.out.print("Enter club ID: ");
        String clubId = sc.nextLine().trim();
        if (clubId.isEmpty()) {
            System.out.println("Club ID cannot be empty.");
            return;
        }
        if (SearchClubById(clubId) != null) {
            System.out.println("Club ID already exists.");
            return;
        }

        System.out.print("Enter club name: ");
        String clubName = sc.nextLine().trim();
        if (clubName.isEmpty()) {
            System.out.println("Club name cannot be empty.");
            return;
        }

        System.out.print("Enter category: ");
        String category = sc.nextLine().trim();
        if (category.isEmpty()) {
            System.out.println("Category cannot be empty.");
            return;
        }

        System.out.print("Enter founded date (dd/MM/yyyy): ");
        String foundedDate = sc.nextLine().trim();
        if (!isValidDate(foundedDate)) {
            System.out.println("Invalid founded date.");
            return;
        }

        System.out.print("Enter leader name: ");
        String leaderName = sc.nextLine().trim();
        if (leaderName.isEmpty()) {
            System.out.println("Leader name cannot be empty.");
            return;
        }

        System.out.print("Enter member count: ");
        int memberCount;
        try {
            memberCount = Integer.parseInt(sc.nextLine().trim());
            if (memberCount < 0) {
                System.out.println("Member count must be >= 0.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid member count.");
            return;
        }

        System.out.print("Enter status (Active/Inactive): ");
        String status = sc.nextLine().trim();
        if (status.equalsIgnoreCase("Active")) {
            status = "Active";
        } else if (status.equalsIgnoreCase("Inactive")) {
            status = "Inactive";
        } else {
            System.out.println("Invalid status.");
            return;
        }

        clubs.add(new Club(clubId, clubName, category, foundedDate,
                           leaderName, memberCount, status));
        System.out.println("Club added successfully.");
    }

    public Club SearchClubById(String clubId) {
        for (Club club : clubs) {
            if (club.getClubId().equalsIgnoreCase(clubId)) {
                return club;
            }
        }
        return null;
    }

    public void SearchClubByName(Scanner sc) {
        System.out.print("Enter club name: ");
        String name = sc.nextLine().trim();
        boolean found = false;

        for (Club club : clubs) {
            if (club.getClubName().toLowerCase().contains(name.toLowerCase())) {
                club.displayClub();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Club not found.");
        }
    }

    public void UpdateClub(Scanner sc) {
        System.out.print("Enter club ID to update: ");
        Club club = SearchClubById(sc.nextLine().trim());

        if (club == null) {
            System.out.println("Club not found.");
            return;
        }

        System.out.print("Enter new club name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Club name cannot be empty.");
            return;
        }

        System.out.print("Enter new category: ");
        String category = sc.nextLine().trim();
        if (category.isEmpty()) {
            System.out.println("Category cannot be empty.");
            return;
        }

        System.out.print("Enter new founded date (dd/MM/yyyy): ");
        String foundedDate = sc.nextLine().trim();
        if (!isValidDate(foundedDate)) {
            System.out.println("Invalid founded date.");
            return;
        }

        System.out.print("Enter new leader name: ");
        String leader = sc.nextLine().trim();
        if (leader.isEmpty()) {
            System.out.println("Leader name cannot be empty.");
            return;
        }

        System.out.print("Enter new member count: ");
        int count;
        try {
            count = Integer.parseInt(sc.nextLine().trim());
            if (count < 0) {
                System.out.println("Member count must be >= 0.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid member count.");
            return;
        }

        System.out.print("Enter new status (Active/Inactive): ");
        String status = sc.nextLine().trim();

        if (status.equalsIgnoreCase("Active")) {
            status = "Active";
        } else if (status.equalsIgnoreCase("Inactive")) {
            status = "Inactive";
        } else {
            System.out.println("Invalid status.");
            return;
        }

        club.setClubName(name);
        club.setCategory(category);
        club.setFoundedDate(foundedDate);
        club.setLeaderName(leader);
        club.setMemberCount(count);
        club.setStatus(status);

        System.out.println("Club updated successfully.");
    }

    public void DeleteClub(Scanner sc) {
        System.out.print("Enter club ID to delete: ");
        Club club = SearchClubById(sc.nextLine().trim());

        if (club == null) {
            System.out.println("Club not found.");
            return;
        }

        clubs.remove(club);
        System.out.println("Club deleted successfully.");
    }

    public void FilterClubStatus(String status) {
        if (!status.equalsIgnoreCase("Active") &&
            !status.equalsIgnoreCase("Inactive")) {
            System.out.println("Invalid status.");
            return;
        }

        boolean found = false;
        for (Club club : clubs) {
            if (club.getStatus().equalsIgnoreCase(status)) {
                club.displayClub();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No clubs found with this status.");
        }
    }

    public void FilterClubCategory(String category) {
        boolean found = false;

        for (Club club : clubs) {
            if (club.getCategory().equalsIgnoreCase(category)) {
                club.displayClub();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No clubs found in this category.");
        }
    }

    public void DisplayClubs() {
        if (clubs.isEmpty()) {
            System.out.println("No clubs available.");
            return;
        }

        for (Club club : clubs) {
            club.displayClub();
        }
    }

    private boolean isValidDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);

        try {
            format.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
