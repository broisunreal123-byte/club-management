
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import models.Registration;

public class FileHandler {
    private static final String CLUB_FILE = "clubs.txt";
    private static final String REGISTRATION_FILE = "registrations.txt";

    private static boolean isValidDate(String date) {
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

    public static void SaveClub(ArrayList<Club> clubs) {
        try {
            FileWriter fw = new FileWriter(CLUB_FILE);
            for (Club club : clubs) {
                fw.write(club.getClubId() + "|"
                        + club.getClubName() + "|"
                        + club.getCategory() + "|"
                        + club.getFoundedDate() + "|"
                        + club.getLeaderName() + "|"
                        + club.getMemberCount() + "|"
                        + club.getStatus() + "\n");
            }
            fw.close();
            System.out.println("Clubs saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving clubs.");
        }
    }

    public static ArrayList<Club> LoadClub() {
        ArrayList<Club> clubs = new ArrayList<Club>();
        File file = new File(CLUB_FILE);
        if (!file.exists()) {
            System.out.println("clubs.txt not found.");
            return clubs;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|", -1);
                if (data.length != 7) {
                    System.out.println("Skipped invalid club.");
                    continue;
                }
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].trim();
                }
                if (data[0].isEmpty() || data[1].isEmpty() ||
                        data[2].isEmpty() || data[4].isEmpty() ||
                        !isValidDate(data[3])) {
                    System.out.println("Skipped invalid club.");
                    continue;
                }
                if (!data[6].equalsIgnoreCase("Active") &&
                        !data[6].equalsIgnoreCase("Inactive")) {
                    System.out.println("Skipped invalid club.");
                    continue;
                }
                boolean duplicate = false;
                for (Club club : clubs) {
                    if (club.getClubId().equalsIgnoreCase(data[0])) {
                        duplicate = true;
                        break;
                    }
                }
                if (duplicate) {
                    System.out.println("Skipped duplicate club ID.");
                    continue;
                }
                try {
                    int count = Integer.parseInt(data[5]);
                    if (count < 0) {
                        System.out.println("Skipped invalid club.");
                        continue;
                    }
                    clubs.add(new Club(data[0], data[1], data[2], data[3],
                            data[4], count, data[6]));
                } catch (NumberFormatException e) {
                    System.out.println("Skipped invalid club.");
                }
            }
            br.close();
            System.out.println("Clubs loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading clubs.");
        }
        return clubs;
    }

    public static void SaveRegistration(ArrayList<Registration> registrations) {
        try {
            FileWriter fw = new FileWriter(REGISTRATION_FILE);
            for (Registration r : registrations) {
                fw.write(r.getRegister_id() + "|"
                        + r.getName_id() + "|"
                        + r.getStudent_name() + "|"
                        + r.getClub_id() + "|"
                        + r.getJoin_date() + "|"
                        + r.getStatus() + "\n");
            }
            fw.close();
            System.out.println("Registrations saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving registrations.");
        }
    }

    public static ArrayList<Registration> LoadRegistration() {
        ArrayList<Registration> registrations = new ArrayList<Registration>();
        File file = new File(REGISTRATION_FILE);
        if (!file.exists()) {
            System.out.println("registrations.txt not found.");
            return registrations;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|", -1);
                if (data.length != 6) {
                    System.out.println("Skipped invalid registration.");
                    continue;
                }
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].trim();
                }
                if (data[0].isEmpty() || data[1].isEmpty() ||
                        data[2].isEmpty() || data[3].isEmpty() ||
                        !isValidDate(data[4])) {
                    System.out.println("Skipped invalid registration.");
                    continue;
                }
                if (!data[5].equalsIgnoreCase("Active") &&
                        !data[5].equalsIgnoreCase("Inactive")) {
                    System.out.println("Skipped invalid registration.");
                    continue;
                }
                boolean duplicate = false;
                for (Registration r : registrations) {
                    if (r.getRegister_id().equalsIgnoreCase(data[0])) {
                        duplicate = true;
                        break;
                    }
                }
                if (duplicate) {
                    System.out.println("Skipped duplicate register ID.");
                    continue;
                }
                registrations.add(new Registration(data[0], data[1],
                        data[2], data[3], data[4], data[5]));
            }
            br.close();
            System.out.println("Registrations loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading registrations.");
        }
        return registrations;
    }

    public static void main(String[] args) {
        ArrayList<Club> clubs = new ArrayList<Club>();
        clubs.add(new Club("C001", "Football Club", "Sports",
                "10/09/2024", "Nguyen Van An", 20, "Active"));
        clubs.add(new Club("C002", "Music Club", "Arts",
                "11/09/2024", "Tran Thi Binh", 15, "Active"));

        ArrayList<Registration> registrations = new ArrayList<Registration>();
        registrations.add(new Registration("K001", "SE01", "Nguyen Van An",
                "C001", "12/09/2024", "Active"));

        System.out.println("===== SAVE DEMO =====");
        SaveClub(clubs);
        SaveRegistration(registrations);

        System.out.println("\n===== LOAD CLUBS =====");
        ArrayList<Club> loadedClubs = LoadClub();
        for (Club c : loadedClubs) {
            c.displayClub();
        }

        System.out.println("\n===== LOAD REGISTRATIONS =====");
        ArrayList<Registration> loadedRegistrations = LoadRegistration();
        for (Registration r : loadedRegistrations) {
            System.out.println(r);
        }
    }
}
