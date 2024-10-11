package com.beyonder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

public class DateDifferenceChecker {
    public static void main(String[] args) {
        // The date from your policy
        String policyDate = "2023-08-10 12:00:11";
        
        // Parse the policy date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime policyDateTime = LocalDateTime.parse(policyDate, formatter);
        
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();
        
        // Calculate the difference in days
        long daysBetween = ChronoUnit.DAYS.between(now, policyDateTime);
        
        System.out.println("Days between now and the policy date: " + daysBetween);
        
        // You can also check if the policy date is in the future
        if (daysBetween > 0) {
            System.out.println("The policy date is in the future.");
        } else if (daysBetween < 0) {
            System.out.println("The policy date is in the past.");
        } else {
            System.out.println("The policy date is today.");
        }
    }
}