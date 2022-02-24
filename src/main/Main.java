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

        String[][] seatingArray = new String[rows][numberOfSeats];

        int rowCounter = 1;

        System.out.println("Cinema:");
        System.out.print("  ");
        // Will Loop to print the number of columns
        for(int column = 1; column <= numberOfSeats; column++) {
            System.out.print(column + " ");
        }
        System.out.println();
        // Loops until the number of rows are printed
        for(int row = 0; row < rows; row++) {
            System.out.print(rowCounter + " ");
            // Loops until the number of seats are printed
            for(int seat = 1; seat <= numberOfSeats; seat++) {
                System.out.print("S ");
            }
            System.out.println();
            rowCounter++;
        }
        System.out.println();

        // This is for when the user chooses the seat in the cinema
        System.out.println("Enter a row number:");
        int chosenRow = userInput.nextInt();
        System.out.println("Enter a seat number in that row:");
        int chosenSeat = userInput.nextInt();
        System.out.println();


        // Part Two: Total Income Made
        final int totalSeats = rows * numberOfSeats;
        int incomeFrontHalf;
        int frontHalfSeats;
        int incomeBackHalf;
        int income;
        final int frontPrice = 10;
        final int backPrice = 8;

        if (totalSeats < 60) {
            income = totalSeats * 10;
            System.out.println("Ticket price: $" + frontPrice);
        } else {

            // Runs if total number of rows are even
            if (rows % 2 == 0) {
                incomeFrontHalf = ((rows / 2) * numberOfSeats) * frontPrice;
                frontHalfSeats = rows / 2;
                incomeBackHalf = ((rows / 2) * numberOfSeats) * backPrice;
                income = incomeFrontHalf + incomeBackHalf;

                if (chosenRow <= frontHalfSeats) {
                    System.out.println("Ticket price: $" + frontPrice);
                } else {
                    System.out.println("Ticket price: $" + backPrice);
                }
            } else {
                incomeFrontHalf = ((rows / 2) * numberOfSeats) * frontPrice;
                frontHalfSeats = rows / 2;
                incomeBackHalf = ((rows / 2 + 1) * numberOfSeats) * backPrice;
                income = incomeFrontHalf + incomeBackHalf;

                if (chosenRow <= frontHalfSeats) {
                    System.out.println("Ticket price: $" + frontPrice);
                } else {
                    System.out.println("Ticket price: $" + backPrice);
                }
            }
        }

        System.out.print("Total income: ");
        System.out.println("$" + income);
        System.out.println();


        // Part Three: Get Ticket Price and Shows the Picked Seat

    }

    public static void showCinema(String[][] seatingArray, int numberOfSeats) {
        int rowCounter = 1;

        System.out.println("Cinema:");
        // Redraws the seating map
        System.out.print("  ");
        for (int column = 1; column <= numberOfSeats; column++) {
            System.out.print(column + " ");
        }
        System.out.println();

        for (int row = 0; row < seatingArray.length; row++) {
            System.out.print(rowCounter + " ");
            for (int singleSeat = 0; singleSeat < seatingArray[row].length; singleSeat++) {
                if (seatingArray[row][singleSeat] == null || !(seatingArray[row][singleSeat].equals("B"))) {
                    // This targets the seat coordinates the user chose to sit in
                    seatingArray[row][singleSeat] = "S";
                }
                System.out.print(seatingArray[row][singleSeat] + " ");
            }
            System.out.println();
            rowCounter++;
        }
    }
}
