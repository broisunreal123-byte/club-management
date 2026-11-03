/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.ArrayList;
import java.util.Scanner;
import model.Club;
import static utils.Utils.InputClubId;
import static utils.Utils.InputDate;
import static utils.Utils.InputStatus;
import static utils.Utils.InputText;

/**
 *
 * @author ADMIN
 */
public class ClubManagement {
    private ArrayList<Club> clubs;
    private Scanner sc;

    public ClubManagement(Scanner sc){
        this.sc = sc;
        this.clubs = new ArrayList<Club>();
    }

    public ArrayList<Club> getClubs(){
        return clubs;
    }

    public void setClubs(ArrayList<Club> loadedClubs){
        if(loadedClubs != null){
            clubs = loadedClubs;
        }
    }

    public Club FindClubById(String clubId){
        for(Club club : clubs){
            if(club.getClubId().equalsIgnoreCase(clubId)){
                return club;
            }
        }
        return null;
    }

    public void AddClub(){
        System.out.println("\n--- ADD NEW CLUB ---");
        String clubId = InputClubId(sc);
        if(FindClubById(clubId) != null){
            System.out.println("Error: Club ID already exists!");
            return;
        }
        String clubName = InputText(sc, "Enter club name: ");
        String category = InputText(sc, "Enter category: ");
        String foundedDate = InputDate(sc, "Enter founded date (dd/MM/yyyy): ");
        String leaderName = InputText(sc, "Enter leader name: ");
        String status = InputStatus(sc, "Enter status (Active/Inactive): ", "Active", "Inactive");
        clubs.add(new Club(clubId, clubName, category, foundedDate, leaderName, 0, status));
        System.out.println("Add club successfully!");
    }

    public void SearchClubById(){
        System.out.println("\n--- SEARCH CLUB BY ID ---");
        String clubId = InputText(sc, "Enter club ID: ");
        Club club = FindClubById(clubId);
        if(club == null){
            System.out.println("Club not found!");
            return;
        }
        Club.PrintHeader();
        club.display();
    }

    public void SearchClubByName(){
        System.out.println("\n--- SEARCH CLUB BY NAME ---");
        String keyword = InputText(sc, "Enter club name (or part of name): ");
        ArrayList<Club> result = new ArrayList<Club>();
        for(Club club : clubs){
            if(club.getClubName().toLowerCase().contains(keyword.toLowerCase())){
                result.add(club);
            }
        }
        PrintClubList(result, "Club not found!");
    }

    public void UpdateClub(){
        System.out.println("\n--- UPDATE CLUB ---");
        String clubId = InputText(sc, "Enter club ID to update: ");
        Club club = FindClubById(clubId);
        if(club == null){
            System.out.println("Club not found!");
            return;
        }
        System.out.println("Current information:");
        Club.PrintHeader();
        club.display();
        System.out.println("Enter new information:");
        String clubName = InputText(sc, "Enter new club name: ");
        String category = InputText(sc, "Enter new category: ");
        String foundedDate = InputDate(sc, "Enter new founded date (dd/MM/yyyy): ");
        String leaderName = InputText(sc, "Enter new leader name: ");
        String status = InputStatus(sc, "Enter new status (Active/Inactive): ", "Active", "Inactive");
        club.setClubName(clubName);
        club.setCategory(category);
        club.setFoundedDate(foundedDate);
        club.setLeaderName(leaderName);
        club.setStatus(status);
        System.out.println("Update club successfully!");
    }

    public void DeleteClub() {
        System.out.println("\n--- DELETE CLUB ---");
        String clubId = InputText(sc, "Enter club ID to delete: ");
        Club club = FindClubById(clubId);
        if(club == null){
            System.out.println("Club not found!");
            return;
        }
        if(club.getMemberCount() > 0){
            System.out.println("Error: Cannot delete this club because it still has " + club.getMemberCount() + " active member(s)!");
            return;
        }
        clubs.remove(club);
        System.out.println("Delete club successfully!");
    }

    public void FilterClubStatus(){
        System.out.println("\n--- FILTER CLUBS BY STATUS ---");
        String status = InputStatus(sc, "Enter status (Active/Inactive): ", "Active", "Inactive");
        ArrayList<Club> result = new ArrayList<Club>();
        for (Club club : clubs) {
            if (club.getStatus().equalsIgnoreCase(status)) {
                result.add(club);
            }
        }
        PrintClubList(result, "No clubs found with this status!");
    }

    public void FilterClubCategory(){
        System.out.println("\n--- FILTER CLUBS BY CATEGORY ---");
        String category = InputText(sc, "Enter category: ");
        ArrayList<Club> result = new ArrayList<Club>();
        for (Club club : clubs) {
            if (club.getCategory().equalsIgnoreCase(category)) {
                result.add(club);
            }
        }
        PrintClubList(result, "No clubs found in this category!");
    }

    public void DisplayClubs(){
        System.out.println("\n--- CLUB LIST ---");
        PrintClubList(clubs, "No clubs available!");
    }

    private void PrintClubList(ArrayList<Club> list, String emptyMessage){
        if(list.isEmpty()){
            System.out.println(emptyMessage);
            return;
        }
        Club.PrintHeader();
        for (Club club : list) {
            club.display();
        }
        System.out.println("Total: " + list.size() + " club(s).");
    }
}
