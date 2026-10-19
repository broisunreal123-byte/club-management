import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import models.Registration;

public class FileHandler {

    private static final String CLUB_FILE = "clubs.txt";
    private static final String REGISTRATION_FILE = "registrations.txt";

    // Save clubs
    public static void SaveClub(ArrayList<Club> clubs) {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(CLUB_FILE),
                        StandardCharsets.UTF_8))) {

            for (Club club : clubs) {
                bw.write(club.getClubId() + "|"
                        + club.getClubName() + "|"
                        + club.getCategory() + "|"
                        + club.getFoundedDate() + "|"
                        + club.getLeaderName() + "|"
                        + club.getMemberCount() + "|"
                        + club.getStatus());
                bw.newLine();
            }

            System.out.println("Clubs saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving clubs: "
                    + e.getMessage());
        }
    }

    // Load clubs
    public static ArrayList<Club> LoadClub() {
        ArrayList<Club> clubs = new ArrayList<>();
        File file = new File(CLUB_FILE);

        if (!file.exists()) {
            return clubs;
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file),
                        StandardCharsets.UTF_8))) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|", -1);

                if (data.length == 7) {
                    try {
                        int count = Integer.parseInt(data[5]);

                        clubs.add(new Club(
                                data[0], data[1], data[2],
                                data[3], data[4], count, data[6]));

                    } catch (NumberFormatException e) {
                        System.out.println("Skipped invalid club.");
                    }
                }
            }

            System.out.println("Clubs loaded successfully.");

        } catch (IOException e) {
            System.out.println("Error loading clubs: "
                    + e.getMessage());
        }

        return clubs;
    }

    // Save registrations
    public static void SaveRegistration(
            ArrayList<Registration> registrations) {

        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(REGISTRATION_FILE),
                        StandardCharsets.UTF_8))) {

            for (Registration r : registrations) {
                bw.write(r.getRegister_id() + "|"
                        + r.getName_id() + "|"
                        + r.getStudent_name() + "|"
                        + r.getClub_id() + "|"
                        + r.getJoin_date() + "|"
                        + r.getStatus());
                bw.newLine();
            }

            System.out.println("Registrations saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving registrations: "
                    + e.getMessage());
        }
    }

    // Load registrations
    public static ArrayList<Registration> LoadRegistration() {
        ArrayList<Registration> registrations = new ArrayList<>();
        File file = new File(REGISTRATION_FILE);

        if (!file.exists()) {
            return registrations;
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file),
                        StandardCharsets.UTF_8))) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|", -1);

                if (data.length == 6) {
                    registrations.add(new Registration(
                            data[0], data[1], data[2],
                            data[3], data[4], data[5]));
                }
            }

            System.out.println("Registrations loaded successfully.");

        } catch (IOException e) {
            System.out.println("Error loading registrations: "
                    + e.getMessage());
        }

        return registrations;
    }

    // Demo FileHandler
    public static void main(String[] args) {

        ArrayList<Club> clubs = new ArrayList<>();

        clubs.add(new Club(
                "C001", "Football Club", "Sports",
                "10/09/2024", "Nguyen Van An", 20, "Active"));

        clubs.add(new Club(
                "C002", "Music Club", "Arts",
                "11/09/2024", "Tran Thi Binh", 15, "Active"));

        ArrayList<Registration> registrations = new ArrayList<>();

        registrations.add(new Registration(
                "K001", "SE01", "Nguyen Van An",
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

        ArrayList<Registration> loadedRegistrations =
                LoadRegistration();

        for (Registration r : loadedRegistrations) {
            System.out.println(r);
        }
    }
}
