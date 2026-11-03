/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.ArrayList;
import java.util.Scanner;
import model.Club;
import model.Registration;
import static utils.Utils.InputClubId;
import static utils.Utils.InputDate;
import static utils.Utils.InputRegisterId;
import static utils.Utils.InputStatus;
import static utils.Utils.InputStudentId;
import static utils.Utils.InputText;


/**
 *
 * @author ADMIN
 */
public class RegistrationManagement {
    private ArrayList<Registration> registrations;
    private Scanner sc;
    private ClubManagement clubManagement;

    public RegistrationManagement(Scanner sc, ClubManagement clubManagement){
        this.sc = sc;
        this.clubManagement = clubManagement;
        this.registrations = new ArrayList<Registration>();
    }

    public ArrayList<Registration> getRegistrations(){
        return registrations;
    }

    public void setRegistrations(ArrayList<Registration> loadedRegistrations){
        if(loadedRegistrations != null){
            registrations = loadedRegistrations;
        }
    }

    public Registration FindRegisterById(String registerId){
        for (Registration r : registrations){
            if(r.getRegisterId().equalsIgnoreCase(registerId)){
                return r;
            }
        }
        return null;
    }

    public void AddRegister(){
        System.out.println("\n--- ADD NEW REGISTRATION ---");
        String registerId = InputRegisterId(sc);
        if(FindRegisterById(registerId) != null){
            System.out.println("Error: Register ID already exists!");
            return;
        }
        String studentId = InputStudentId(sc);
        String studentName = InputText(sc, "Enter student name: ");
        if(IsStudentNameConflict(studentId, studentName)){
            return;
        }
        String clubId = InputClubId(sc);
        Club club = clubManagement.FindClubById(clubId);
        if(club == null){
            System.out.println("Error: Club does not exist!");
            return;
        }
        if(!club.getStatus().equalsIgnoreCase("Active")){
            System.out.println("Error: This club is Inactive, cannot register!");
            return;
        }
        if(IsActiveMember(studentId, clubId)){
            System.out.println("Error: This student is already an active member of this club!");
            return;
        }
        String joinDate = InputDate(sc, "Enter join date (dd/MM/yyyy): ");
        String status = InputStatus(sc, "Enter status (Active/Left): ", "Active", "Left");
        registrations.add(new Registration(registerId, studentId, studentName, club.getClubId(), joinDate, status));
        UpdateMemberCount();
        System.out.println("Add registration successfully!");
    }

    public void DeleteRegister() {
        System.out.println("\n--- DELETE REGISTRATION ---");
        String registerId = InputText(sc, "Enter register ID to delete: ");
        Registration r = FindRegisterById(registerId);
        if(r == null){
            System.out.println("Register ID not found!");
            return;
        }
        registrations.remove(r);
        UpdateMemberCount();
        System.out.println("Delete registration successfully!");
    }

    public void SearchMember(){
        System.out.println("\n--- SEARCH MEMBER ---");
        String keyword = InputText(sc, "Enter student ID or club ID: ");
        ArrayList<Registration> result = new ArrayList<Registration>();
        for (Registration r : registrations) {
            if (r.getStudentId().equalsIgnoreCase(keyword) || r.getClubId().equalsIgnoreCase(keyword)) {
                result.add(r);
            }
        }
        PrintRegistrationList(result, "No matching member found!");
    }
    
    public void DeleteRegistrationsByClubId(String clubId){
    for (int i = registrations.size() - 1; i >= 0; i--){
        Registration registration = registrations.get(i);
        if(registration.getClubId().equalsIgnoreCase(clubId)){
            registrations.remove(i);
        }
    } UpdateMemberCount();
    }

    public void ListStudents(){
        System.out.println("\n--- LIST STUDENTS BY CLUB ---");
        String clubId = InputText(sc, "Enter club ID: ");
        Club club = clubManagement.FindClubById(clubId);
        if(club == null){
            System.out.println("Club not found!");
            return;
        }
        System.out.println("Students of club " + club.getClubId() + " - " + club.getClubName() + ":");
        ArrayList<Registration> result = new ArrayList<Registration>();
        for (Registration r : registrations){
            if(r.getClubId().equalsIgnoreCase(club.getClubId())){
                result.add(r);
            }
        }
        PrintRegistrationList(result, "No students found in this club!");
    }

    public void UpdateMemberCount(){
        for (Club club : clubManagement.getClubs()){
            club.setMemberCount(0);
        }
        for (Registration r : registrations) {
            if(r.getStatus().equalsIgnoreCase("Active")) {
                Club club = clubManagement.FindClubById(r.getClubId());
                if(club != null) {
                    club.setMemberCount(club.getMemberCount() + 1);
                }
            }
        }
    }

    private boolean IsActiveMember(String studentId, String clubId) {
        for (Registration r : registrations) {
            if(r.getStudentId().equalsIgnoreCase(studentId) && r.getClubId().equalsIgnoreCase(clubId) 
                    && r.getStatus().equalsIgnoreCase("Active")) {
                return true;
            }
        }return false;
    }

    private boolean IsStudentNameConflict(String studentId, String studentName) {
        for (Registration r : registrations) {
            if (r.getStudentId().equalsIgnoreCase(studentId) && !r.getStudentName().equalsIgnoreCase(studentName)) {
                System.out.println("Error: Student ID " + studentId + " already belongs to \"" + r.getStudentName() + "\"!");
                return true;
            }
        }return false;
    }

    private void PrintRegistrationList(ArrayList<Registration> list, String emptyMessage) {
        if(list.isEmpty()){
            System.out.println(emptyMessage);
            return;
        }
        Registration.PrintHeader();
        for (Registration r : list) {
            r.display();
        }
        System.out.println("Total: " + list.size() + " registration(s).");
    }
}
