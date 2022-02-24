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
        showMenu(seatingArray, rows, numberOfSeats);

    }

    public static void showMenu(String[][] seatingArray, int rows, int numberOfSeats) {
        Scanner scanner = new Scanner(System.in);
        // Main menu of the program
        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("0. Exit");

            int input = scanner.nextInt();
            // Exits the program
            if (input == 0) {
                break;
            }

            switch (input) {
                case 1:
                    showCinema(seatingArray, numberOfSeats);
                    break;
                case 2:
                    getTicketPrice(seatingArray, rows, numberOfSeats);
                    System.out.println();
                    break;
                case 0:
                    return;
            }
        }
    }

    public static void showCinema(String[][] seatingArray, int numberOfSeats) {
        int rowCounter = 1;

        System.out.println("Cinema:");
        // Draws the header column of the seating map
        System.out.print("  ");
        for (int column = 1; column <= numberOfSeats; column++) {
            System.out.print(column + " ");
        }
        System.out.println();

        // Draws the rest of the seating map
        // It will print out "S" for available seats and "B" for taken seats
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

    public static void getTicketPrice(String[][] seatingArray, int rows, int numberOfSeats) {
        Scanner scanner = new Scanner(System.in);
        // Asks the user their chosen seat
        System.out.println("Enter a row number:");
        int chosenRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int chosenSeat = scanner.nextInt();

        int totalSeats = rows * numberOfSeats;
        // This outputs the price of an individual ticket based on its position
        // If the total seats in the cinema is less than 60 then the price for all tickets is 10$
        // Else if the chosen seat is located in front half of the cinema the ticket price is 10$
        // Else the price of a ticket is 8$ if the chosen seat is in the back of the cinema
        System.out.println("Ticket price: $" + (totalSeats <= 60 ? 10 : chosenRow <= rows / 2 ? 10 : 8));

        for (int i = 0; i < seatingArray.length; i++) {
            for (int j = 0; j < seatingArray[i].length; j++) {
                if (i == chosenRow - 1 && j == chosenSeat -1) {
                    seatingArray[i][j] = "B";
                }
            }
        }
    }
}
