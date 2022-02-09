package main;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        // Part One: Cinema Visualization
        System.out.print("Enter number of rows: ");
        int rows = userInput.nextInt();
        System.out.print("Enter the number of seats per row: ");
        int numberOfSeats = userInput.nextInt();
        System.out.println("");


        int rowCounter = 1;

        System.out.print("  ");
        // Will Loop to print the number of columns
        for(int column = 1; column <= numberOfSeats; column++) {
            System.out.print(column + " ");
        }
        System.out.println("");
        // Loops until the number of rows are printed
        for(int row = 0; row < rows; row++) {
            System.out.print(rowCounter + " ");
            // Loops until the number of seats are printed
            for(int seat = 1; seat <= numberOfSeats; seat++) {
                System.out.print("S ");
            }
            System.out.println("");
            rowCounter++;
        }

        // Part Two: Total Income Made
        final int totalSeats = rows * numberOfSeats;
        int incomeFrontHalf = 0;
        int incomeBackHalf = 0;
        int income = 0;
        final int frontPrice = 10;
        final int backPrice = 8;

        if (totalSeats < 60) {
            income = totalSeats * 10;
        } else {

            if (rows % 2 == 0) {
                incomeFrontHalf = ((rows / 2) * numberOfSeats) * frontPrice;
                incomeBackHalf = ((rows / 2) * numberOfSeats) * backPrice;
                income = incomeFrontHalf + incomeBackHalf;
            } else {
                incomeFrontHalf = ((rows / 2) * numberOfSeats) * frontPrice;
                incomeBackHalf = ((rows / 2 + 1) * numberOfSeats) * backPrice;
                income = incomeFrontHalf + incomeBackHalf;
            }
        }

        System.out.println("Total income: ");
        System.out.print("$" + income);


    }
}
