/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class Utils {
    private static String Input(Scanner sc, String message, String regex, String errorMessage) {
        while (true) {
            System.out.print(message);
            String text = sc.nextLine().trim();
            if (text.matches(regex)) {
                return text;
            }
            System.out.println("Error: " + errorMessage);
        }
    }

    public static String InputText(Scanner sc, String message){
        return Input(sc, message, "[^|]+", "This field cannot be empty or contain the character '|'.");
    }

    public static String InputClubId(Scanner sc){
        return Input(sc, "Enter club ID (e.g. C01): ", "(?i)C\\d{2,}", 
                "Club ID must be 'C' followed by at least 2 digits.").toUpperCase();
    }

    public static String InputRegisterId(Scanner sc){
        return Input(sc, "Enter register ID (e.g. K01): ", "(?i)K\\d{2,}", 
                "Register ID must be 'K' followed by at least 2 digits.").toUpperCase();
    }

    public static String InputStudentId(Scanner sc){
        return Input(sc, "Enter student ID (e.g. SE0001): ", "(?i)SE\\d{4,}", 
                "Student ID must be 'SE' followed by at least 4 digits.").toUpperCase();
    }

    public static String InputStatus(Scanner sc, String message, String option1, String option2){
        String status = Input(sc, message, "(?i)" + option1 + "|" + option2, 
                "Status must be " + option1 + " or " + option2 + ".");
        return status.equalsIgnoreCase(option1) ? option1 : option2;
    }

    public static String InputDate(Scanner sc, String message){
        while (true) {
            String date = Input(sc, message, "\\d{2}/\\d{2}/\\d{4}", "Date must be in format dd/MM/yyyy.");
            if(IsRealDate(date)){
                return date;
            }
            System.out.println("Error: This date does not exist.");
        }
    }

    public static int InputChoice(Scanner sc, String message){
        System.out.print(message);
        try{
            return Integer.parseInt(sc.nextLine().trim());
        }catch(NumberFormatException e) {
            return -1;
        }
    }

    private static boolean IsRealDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        try{
            format.parse(date);
            return true;
        }catch(ParseException e) {
            return false;
        }
    }
}
