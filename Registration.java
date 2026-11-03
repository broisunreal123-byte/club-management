/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Registration extends Model {
    private String registerId;
    private String studentId;
    private String studentName;
    private String clubId;
    private String joinDate;

    public Registration() {
    }

    public Registration(String registerId, String studentId, String studentName, String clubId, String joinDate, String status) {
        super(status);
        this.registerId = registerId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.clubId = clubId;
        this.joinDate = joinDate;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    

    public static void PrintHeader() {
        System.out.println("==================================================================================================");
        System.out.printf("|%-10s|%-10s|%-20s|%-8s|%-12s|%-8s|%n", "Register", "Student", "Name", "Club", "Join date", "Status");
        System.out.println("==================================================================================================");
    }

    @Override
    public void display() {
        System.out.printf("|%-10s|%-10s|%-20s|%-8s|%-12s|%-8s|%n", 
                registerId, studentId, studentName, clubId, joinDate, getStatus());
    }

    @Override
    public String toFileString() {
        return registerId + "|" + studentId + "|" + studentName + "|" + clubId + "|" + joinDate + "|" + getStatus();
    }
    
}
