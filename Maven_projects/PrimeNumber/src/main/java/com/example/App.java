package com.example;

import java.util.Scanner;

public class App {

    // Method to check prime
    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int num = sc.nextInt();

        if (isPrime(num)) {
            System.out.println(num + " is a PRIME number.");
        } else {
            System.out.println(num + " is NOT a prime number.");
        }

        sc.close();
    }
}
