/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import model.Club;
import model.Registration;
import model.Model;

/**
 *
 * @author ADMIN
 */
public class FileIO {
    public static final String CLUB_FILE = "clubs.txt";
    public static final String REGISTRATION_FILE = "registrations.txt";
    private static void saveToFile(String fName, ArrayList<? extends Model> list){
        if (list.isEmpty()) {
            System.out.println("Empty list");
        }
        try {
            File f = new File(fName);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Model x : list) {
                pw.println(x.toFileString());
            }
            pw.close();
            fw.close();
            System.out.println("Save file successfully!");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    private static ArrayList<String> readLines(String fName) {
        ArrayList<String> list = new ArrayList<String>();
        try{
            File f = new File(fName);
            if(!f.exists()) {
                return list;
            }
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                if(!line.trim().isEmpty()){
                    list.add(line);
                }
            }
        }catch(FileNotFoundException e) {
            System.out.println(e);
        }
        return list;
    }

    public static void saveClub(ArrayList<Club> clubs) {
        saveToFile(CLUB_FILE, clubs);
    }

    public static void saveRegistration(ArrayList<Registration> registrations) {
        saveToFile(REGISTRATION_FILE, registrations);
    }

    public static ArrayList<Club> loadClub() {
        ArrayList<Club> clubs = new ArrayList<Club>();
        ArrayList<String> list = readLines(CLUB_FILE);

        for (String line : list) {
            String[] data = line.split("\\|", -1);
            if(data.length != 7){
                System.out.println("Invalid club data: " + line);
                continue;
            }
            try{
                int memberCount = Integer.parseInt(data[5].trim());
                boolean duplicated = false;
                for (Club club : clubs) {
                    if(club.getClubId().equalsIgnoreCase(data[0])){
                        duplicated = true;
                        break;
                    }
                }
                if(!duplicated){
                    Club club = new Club(
                            data[0], data[1], data[2],
                            data[3], data[4], memberCount, data[6]);
                    clubs.add(club);
                }else{
                    System.out.println("Duplicate club ID: " + data[0]);
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid club data: " + line);
            }
        }
        System.out.println("Loaded " + clubs.size() + " clubs.");
        return clubs;
    }

    public static ArrayList<Registration> loadRegistration() {
        ArrayList<Registration> registrations = new ArrayList<Registration>();
        ArrayList<String> list = readLines(REGISTRATION_FILE);
        for (String line : list) {
            String[] data = line.split("\\|", -1);
            if(data.length != 6){
                System.out.println("Invalid registration data: " + line);
                continue;
            }

            boolean duplicated = false;
            for(Registration registration : registrations) {
                if (registration.getRegisterId()
                        .equalsIgnoreCase(data[0])) {
                    duplicated = true;
                    break;
                }
            }
            if(!duplicated) {
                Registration registration = new Registration(data[0], data[1], data[2],data[3], data[4], data[5]);
                registrations.add(registration);
            }else{
                System.out.println("Duplicate registration ID: " + data[0]);
            }
        }
        System.out.println("Loaded " + registrations.size() + " registrations.");
        return registrations;
    }
}
