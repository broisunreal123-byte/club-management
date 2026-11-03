/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import control.ClubManagement;
import control.RegistrationManagement;
import java.io.File;
import java.util.Scanner;
import utils.FileIO;
import static utils.Utils.InputChoice;

/**
 *
 * @author ADMIN
 */
public class Main {
    public static void ShowMainMenu() {
        System.out.println("\n========== CLUB MANAGEMENT SYSTEM ==========");
        System.out.println("1. Club Management");
        System.out.println("2. Registration Management");
        System.out.println("3. Save data to files");
        System.out.println("4. Load data from files");
        System.out.println("0. Exit");
        System.out.println("============================================");
    }

    public static void ShowClubMenu() {
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

    public static void ShowRegistrationMenu() {
        System.out.println("\n========== REGISTRATION MENU ==========");
        System.out.println("1. Add new registration");
        System.out.println("2. Delete registration");
        System.out.println("3. Search member by student ID or club ID");
        System.out.println("4. List students of a club");
        System.out.println("0. Back to main menu");
        System.out.println("=======================================");
    }

    public static void ClubMenu(Scanner sc, ClubManagement clubManagement) {
        int choice;
        do{
            ShowClubMenu();
            choice = InputChoice(sc, "Enter your choice: ");
            switch (choice) {
                case 1:
                    clubManagement.AddClub();
                    break;
                case 2:
                    clubManagement.DisplayClubs();
                    break;
                case 3:
                    clubManagement.SearchClubById();
                    break;
                case 4:
                    clubManagement.SearchClubByName();
                    break;
                case 5:
                    clubManagement.UpdateClub();
                    break;
                case 6:
                    clubManagement.DeleteClub();
                    break;
                case 7:
                    clubManagement.FilterClubStatus();
                    break;
                case 8:
                    clubManagement.FilterClubCategory();
                    break;
                case 0:
                    System.out.println("Back to main menu.");
                    break;
                default:
                    System.out.println("Invalid choice! Please choose from 0 to 8.");
            }
        }while(choice != 0);
    }

    public static void RegistrationMenu(Scanner sc, RegistrationManagement registrationManagement) {
        int choice;
        do{
            ShowRegistrationMenu();
            choice = InputChoice(sc, "Enter your choice: ");
            switch(choice){
                case 1:
                    registrationManagement.AddRegister();
                    break;
                case 2:
                    registrationManagement.DeleteRegister();
                    break;
                case 3:
                    registrationManagement.SearchMember();
                    break;
                case 4:
                    registrationManagement.ListStudents();
                    break;
                case 0:
                    System.out.println("Back to main menu.");
                    break;
                default:
                    System.out.println("Invalid choice! Please choose from 0 to 5.");
            }
        }while(choice != 0);
    }

    public static void SaveData(ClubManagement clubManagement, RegistrationManagement registrationManagement) {
        FileIO.saveClub(clubManagement.getClubs());
        FileIO.saveRegistration(registrationManagement.getRegistrations());
    }

    public static void LoadData(ClubManagement clubManagement, RegistrationManagement registrationManagement) {
        if(new File(FileIO.CLUB_FILE).exists()){
            clubManagement.setClubs(FileIO.loadClub());
        }else{
            System.out.println(FileIO.CLUB_FILE + " not found. Keeping current clubs.");
        }
        if(new File(FileIO.REGISTRATION_FILE).exists()){
            registrationManagement.setRegistrations(FileIO.loadRegistration());
        }else{
            System.out.println(FileIO.REGISTRATION_FILE + " not found. Keeping current registrations.");
        }
        registrationManagement.UpdateMemberCount();
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ClubManagement clubManagement = new ClubManagement(sc);
        RegistrationManagement registrationManagement = new RegistrationManagement(sc, clubManagement);
        LoadData(clubManagement, registrationManagement);
        int choice;
        do{
            ShowMainMenu();
            choice = InputChoice(sc, "Enter your choice: ");
            switch (choice) {
                case 1:
                    ClubMenu(sc, clubManagement);
                    break;
                case 2:
                    RegistrationMenu(sc, registrationManagement);
                    break;
                case 3:
                    SaveData(clubManagement, registrationManagement);
                    break;
                case 4:
                    LoadData(clubManagement, registrationManagement);
                    break;
                case 0:
                    SaveData(clubManagement, registrationManagement);
                    System.out.println("Program ended.");
                    break;
                default:
                    System.out.println("Invalid choice! Please choose from 0 to 4.");
            }
        }while(choice != 0);
    }
}
