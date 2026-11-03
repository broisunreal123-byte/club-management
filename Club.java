/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Club extends Model{
    private String clubId;
    private String clubName;
    private String category;
    private String foundedDate;
    private String leaderName;
    private int memberCount;

   
    public Club() {
    }


    public Club(String clubId, String clubName, String category, String foundedDate, 
            String leaderName, int memberCount, String status) {
        super(status);
        this.clubId = clubId;
        this.clubName = clubName;
        this.category = category;
        this.foundedDate = foundedDate;
        this.leaderName = leaderName;
        this.memberCount = memberCount;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFoundedDate() {
        return foundedDate;
    }

    public void setFoundedDate(String foundedDate) {
        this.foundedDate = foundedDate;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public static void PrintHeader() {
        System.out.println("=================================================================================================================");
        System.out.printf("|%-5s|%-15s|%-10s|%-10s|%-20s|%-5s|%-5s|%n", "ID", "Name", "Category", "Founded", "Leader", "Members", "Status");
        System.out.println("=================================================================================================================");
    }

    @Override
    public void display() {
        System.out.printf("|%-5s|%-15s|%-10s|%-10s|%-20s|%-5s|%-5s|%n", 
                clubId, clubName, category, foundedDate, leaderName, memberCount, getStatus());
    }

    @Override
    public String toFileString() {
        return clubId + "|" + clubName + "|" + category + "|" + foundedDate + "|" + leaderName + "|" + memberCount + "|" + getStatus();
    }
}
