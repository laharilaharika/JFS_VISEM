package com.example;

import java.util.Scanner;

public class App {

    // Method to reverse a number
    public static int reverseNumber(int num) {
        int reversed = 0;
        while (num != 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        return reversed;
    }

    // Main method to run program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Reverse Number Program ===");
        System.out.print("Enter a number: ");

        int number = sc.nextInt();
        int result = reverseNumber(number);

        System.out.println("Reversed Number: " + result);

        sc.close();
    }
}
