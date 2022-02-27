package main;
import java.util.Scanner;

public class Main {

    private static int totalIncome;
    private static int currentIncome;

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter the number of rows: ");
        int rows = userInput.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int numberOfSeats = userInput.nextInt();

        String[][] seatingArray = new String[rows][numberOfSeats];
        // Draws the cinema so the array is not empty
        createCinema(seatingArray);
        // Calculates the total income if all seats are sold
        setTotalIncome(rows, numberOfSeats);
        // Shows the main menu
        showMenu(seatingArray, rows, numberOfSeats);

    }

    public static void showMenu(String[][] seatingArray, int rows, int numberOfSeats) {
        Scanner userInput = new Scanner(System.in);
        // This loop will re-iterate until the user exits the program
        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int input = userInput.nextInt();

            switch (input) {
                case 1:
                    showCinema(seatingArray, numberOfSeats);
                    break;
                case 2:
                    getTicketPrice(seatingArray, rows, numberOfSeats);
                    System.out.println();
                    break;
                case 3:
                    statistics(seatingArray);
                    break;
                // Exits the program
                case 0:
                    return;
            }
        }

    }

    public static void statistics(String[][] seatingArray) {
        System.out.printf("\nNumber of purchased tickets: %d\n", getTakenSeats(seatingArray));
        System.out.printf("Percentage: %.2f%%\n", getPercentageOfSeats(seatingArray));
        System.out.printf("Current income: $%d\n", getIncome());
        System.out.printf("Total income: $%d\n\n", getTotalIncome());
    }

    // Returns the number of seats sold
    public static int getTakenSeats(String[][] seatingArray) {
        int takenSeats = 0;
        for (String[] strings : seatingArray) {
            for (String string : strings) {
                if (string.equals("B")) {
                    takenSeats++;
                }
            }
        }
        return takenSeats;
    }

    // Returns the amount of seats compared to the total seats by percentage
    public static double getPercentageOfSeats(String[][] seatingArray) {
        int totalSeats = 0;
        for (String[] row : seatingArray) {
            for (String seat : row) {
                totalSeats++;
            }
        }
        int totalTakenSeats = getTakenSeats(seatingArray);

        return (1.0 * totalTakenSeats / totalSeats) * 100 ;
    }

    public static void createCinema(String[][] seatingArray) {
        for (int i = 0; i < seatingArray.length; i++) {
            for (int j = 0; j < seatingArray[i].length; j++) {
                seatingArray[i][j] = "S";
            }
        }
    }

    // Outputs the full cinema that shows the column and row numbers
    public static void showCinema(String[][] seatingArray, int numberOfSeats) {
        int rowCounter = 1;

        System.out.println("Cinema:");
        // Draws Columns
        System.out.print("  ");
        for (int i = 1; i <= numberOfSeats; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Draws the "S" as long as it is null or doesn't have "B"
        // Draws the rest of the seating map
        for (int j = 0; j < seatingArray.length; j++) {
            System.out.print(rowCounter + " ");
            for (int singleSeat = 0; singleSeat < seatingArray[j].length; singleSeat++) {
                if (seatingArray[j][singleSeat] == null || !(seatingArray[j][singleSeat].equals("B"))) {
                    // This targets the seat coordinates the user chose to sit in
                    seatingArray[j][singleSeat] = "S";
                }
                System.out.print(seatingArray[j][singleSeat] + " ");
            }
            System.out.println();
            rowCounter++;
        }

    }

    public static void getTicketPrice(String[][] seatingArray, int rows, int numberOfSeats) {
        Scanner userInput = new Scanner(System.in);
        // Will keep re-iterating until the user enters valid seat numbers
        while (true) {
            // Asks the user their chosen seat
            System.out.println("\nEnter a row number:");
            int chosenRow = userInput.nextInt();
            System.out.println("Enter a seat number in that row:");
            int chosenSeat = userInput.nextInt();

            // If user enters out of bounds values
            if (chosenRow > rows || chosenSeat > numberOfSeats) {
                System.out.println("\nWrong input!");
                continue;
            }

            int totalSeats = rows * numberOfSeats;

            // This outputs the price of a ticket based on its position
            // If the total seats in the cinema is less than 60 then the price for all tickets is 10$
            // Else if the chosen seat is located at the front half of the cinema, ticket price is 10$
            // Else the price of a ticket is 8$ if the chosen seat is at the back of the cinema
            int priceOfTicket = totalSeats <= 60 ? 10 : chosenRow <= rows / 2 ? 10 : 8;
            System.out.println("\nTicket price: $" + priceOfTicket);
            boolean alreadyTaken = false;
            // Will change the chosen seat to taken as long as it's not already taken
            for (int i = 0; i < seatingArray.length && !alreadyTaken; i++) {
                for (int j = 0; j < seatingArray[i].length; j++) {
                    if (i == chosenRow - 1 && j == chosenSeat - 1) {
                        if (seatingArray[i][j].equals("B")) {
                            System.out.println("\nThat ticket has already been purchased!");
                            alreadyTaken = true;
                            break;
                        } else {
                            seatingArray[i][j] = "B";
                            // Will add the price of the ticket bought to the income made
                            calculateIncome(priceOfTicket);
                        }
                    }
                }
            }
            // Will break the loop if the seat chosen is not taken
            if (!alreadyTaken) {
                break;
            }
        }

    }

    public static void calculateIncome(int priceOfTicket) {
        currentIncome += priceOfTicket;
    }

    public static void setTotalIncome(int rows, int numberOfSeats) {
        final int totalSeats = rows * numberOfSeats;
        int incomeFrontHalf;
        int incomeBackHalf;
        int income;
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
        totalIncome = income;
    }

    public static int getTotalIncome() {
        return totalIncome;
    }

    public static int getIncome() {
        return currentIncome;
    }
}
