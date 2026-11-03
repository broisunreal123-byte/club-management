
import java.util.ArrayList;
import java.util.Scanner;

public class ClubManagement {
    private ArrayList<Club> clubs;

    public ClubManagement() {
        clubs = new ArrayList<Club>();
    }

    public ArrayList<Club> getClubs() {
        return clubs;
    }

    public void setClubs(ArrayList<Club> loadedClubs) {
        if (loadedClubs != null) {
            clubs = loadedClubs;
        }
    }

    public void AddClub(Scanner sc) {
        System.out.print("Enter club ID: ");
        String clubId = sc.nextLine().trim();
        if (clubId.isEmpty() || clubId.contains("|")) {
            System.out.println("Invalid club ID.");
            return;
        }
        if (SearchClubById(clubId) != null) {
            System.out.println("Club ID already exists.");
            return;
        }

        System.out.print("Enter club name: ");
        String clubName = sc.nextLine().trim();
        if (clubName.isEmpty() || clubName.contains("|")) {
            System.out.println("Invalid club name.");
            return;
        }

        System.out.print("Enter category: ");
        String category = sc.nextLine().trim();
        if (category.isEmpty() || category.contains("|")) {
            System.out.println("Invalid category.");
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
        if (leaderName.isEmpty() || leaderName.contains("|")) {
            System.out.println("Invalid leader name.");
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
        if (clubId == null || clubId.trim().isEmpty()) {
            return null;
        }
        for (Club club : clubs) {
            if (club.getClubId().equalsIgnoreCase(clubId.trim())) {
                return club;
            }
        }
        return null;
    }

    public void SearchClubByName(Scanner sc) {
        System.out.print("Enter club name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Club name cannot be empty.");
            return;
        }
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
        String clubId = sc.nextLine().trim();
        if (clubId.isEmpty()) {
            System.out.println("Club ID cannot be empty.");
            return;
        }
        Club club = SearchClubById(clubId);
        if (club == null) {
            System.out.println("Club not found.");
            return;
        }

        System.out.print("Enter new club name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty() || name.contains("|")) {
            System.out.println("Invalid club name.");
            return;
        }

        System.out.print("Enter new category: ");
        String category = sc.nextLine().trim();
        if (category.isEmpty() || category.contains("|")) {
            System.out.println("Invalid category.");
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
        if (leader.isEmpty() || leader.contains("|")) {
            System.out.println("Invalid leader name.");
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
        String clubId = sc.nextLine().trim();
        if (clubId.isEmpty()) {
            System.out.println("Club ID cannot be empty.");
            return;
        }
        Club club = SearchClubById(clubId);
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
        if (category.isEmpty()) {
            System.out.println("Category cannot be empty.");
            return;
        }
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
}
