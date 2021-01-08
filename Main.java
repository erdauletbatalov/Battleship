package battleship;

import java.io.IOException;
import java.util.*;


public class Main {
    static Scanner scanner = new Scanner(System.in);



    static int count1 = 0;
    static int count2 = 0;
    static int irr;

    public static void main(String[] args) {
        // Declaring sea array

        //Player's 1 field
        String[][] Player1 = new String[11][11];

        //Player's 2 field
        String[][] Player2 = new String[11][11];

        //Setting 5 types of ships
        shipType Aircraft = new shipType();
        Aircraft.setShipName("Aircraft Carrier");
        Aircraft.setShipLength(5);

        shipType Battleship = new shipType();
        Battleship.setShipName("Battleship");
        Battleship.setShipLength(4);

        shipType Submarine = new shipType();
        Submarine.setShipName("Submarine");
        Submarine.setShipLength(3);

        shipType Cruiser = new shipType();
        Cruiser.setShipName("Cruiser");
        Cruiser.setShipLength(3);

        shipType Destroyer = new shipType();
        Destroyer.setShipName("Destroyer");
        Destroyer.setShipLength(2);



        // Creating a starting battlefields
        for (int row = 0; row <= 10; row++) {
            for (int col = 0; col <= 10; col++) {

                if (row == 0 && col == 0) {
                    Player1[row][col] = " ";
                    Player2[row][col] = " ";
                } else if (row == 0) {
                    Player1[row][col] = String.format("%d", col);
                    Player2[row][col] = String.format("%d", col);
                } else if (col == 0) {
                    Player1[row][col] = String.format("%c", (char) (64 + row));
                    Player2[row][col] = String.format("%c", (char) (64 + row));
                } else {
                    Player1[row][col] = "~";
                    Player2[row][col] = "~";
                }
            }
        }

        //Placing ships on field 1
        System.out.println("Player 1, place your ships on the game field\n");

        printBattlefield(Player1);

        //Take position!
        setUnit(Player1, Aircraft);

        setUnit(Player1, Battleship);

        setUnit(Player1, Submarine);

        setUnit(Player1, Cruiser);

        setUnit(Player1, Destroyer);

        promptEnterKey();

        char[] allShips1 = {Aircraft.coordinates[0], Aircraft.coordinates[1], Aircraft.coordinates[2], Aircraft.coordinates[3],
                Battleship.coordinates[0], Battleship.coordinates[1], Battleship.coordinates[2], Battleship.coordinates[3],
                Submarine.coordinates[0], Submarine.coordinates[1], Submarine.coordinates[2], Submarine.coordinates[3],
                Cruiser.coordinates[0], Cruiser.coordinates[1], Cruiser.coordinates[2], Cruiser.coordinates[3],
                Destroyer.coordinates[0], Destroyer.coordinates[1], Destroyer.coordinates[2], Destroyer.coordinates[3]};

        System.out.println("Player 2, place your ships on the game field\n");

        //Placing ships on field 2
        printBattlefield(Player2);

        //Take position!
        setUnit(Player2, Aircraft);

        setUnit(Player2, Battleship);

        setUnit(Player2, Submarine);

        setUnit(Player2, Cruiser);

        setUnit(Player2, Destroyer);

        promptEnterKey();

        //Ships' coordinates for checking if one of them or all of them destroyed or not
        char[] allShips2 = {Aircraft.coordinates[0], Aircraft.coordinates[1], Aircraft.coordinates[2], Aircraft.coordinates[3],
                Battleship.coordinates[0], Battleship.coordinates[1], Battleship.coordinates[2], Battleship.coordinates[3],
                Submarine.coordinates[0], Submarine.coordinates[1], Submarine.coordinates[2], Submarine.coordinates[3],
                Cruiser.coordinates[0], Cruiser.coordinates[1], Cruiser.coordinates[2], Cruiser.coordinates[3],
                Destroyer.coordinates[0], Destroyer.coordinates[1], Destroyer.coordinates[2], Destroyer.coordinates[3]};

        //while all ships aren't destroyed

        for(irr = 2;;irr++) {
            if(irr % 2 == 0) {
                printFoggedBattlefield(Player2);
                System.out.println("---------------------");
                printBattlefield(Player1);
                System.out.println("\n\nPlayer " + 1 + ", it's your turn:\n");
                shot(Player2, allShips2);
                System.out.println(count2);
                promptEnterKey();
            }
            else if(irr % 2 == 1) {
                printFoggedBattlefield(Player1);
                System.out.println("---------------------");
                printBattlefield(Player2);
                System.out.println("\n\nPlayer " + 2 + ", it's your turn:\n");
                shot(Player1, allShips1);
                System.out.println(count1);
                promptEnterKey();
            }
            if(count1 == 5 || count2 == 5)
                break;
        }
    }

    public static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player");
        try {
            System.in.read();} catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Printing a battlefield
    public static void printBattlefield(String[][] sea) {
        for (int row = 0; row <= 10; row++) {
            for (int col = 0; col <= 10; col++) {
                System.out.print(sea[row][col] + " ");
            }
            System.out.println();
        }
    }

    //printing the sea full of '~', 'X' and 'M'
    public static void printFoggedBattlefield(String[][] sea) {
        // Printing a battlefield
        for (int row = 0; row <= 10; row++) {
            for (int col = 0; col <= 10; col++) {
                if( "O".equals(sea[row][col]) ) {
                    System.out.print("~ ");
                    continue;
                }
                System.out.print(sea[row][col] + " ");
            }
            System.out.println();
        }
    }

    //Game starts, tale a shot
//    public static void endOfPlacing(String[][] sea) {
//        System.out.printf("\nThe game starts!\n\n");
//
//        printFoggedBattlefield(sea);
//
//        System.out.println("\nTake a shot!\n");
//    }

    //Shooting process
    public static void shot(String[][] sea, char[] allShips) {

        Scanner scanner = new Scanner(System.in);
try {
    for (; ; ) {
        // Entering input values
        String shotCoordinates = scanner.next();


        // Shooting Coordinates parsing process
        int rowShot = shotCoordinates.charAt(0);
        int colShot = 0;
        if (shotCoordinates.length() == 3) {
            if ((int) (shotCoordinates.charAt(1)) == 49 && (int) (shotCoordinates.charAt(2)) == 48)
                colShot = 10;
        } else
            colShot = shotCoordinates.charAt(1);


        // Converting variables
        int rowShotInt = rowShot - 64;

        int colShotInt;
        if (colShot == 10)
            colShotInt = 10;
        else
            colShotInt = colShot - 48;

        // Shooting
        try {
            if(rowShotInt == 10 && colShotInt == 10) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                count2 = 5;
                count1 = 5;
                break;
            }

            if ("X".equals(sea[rowShotInt][colShotInt])) {

                System.out.println("\nYou hit a ship! Try again:\n");
                continue;
            }
            if ("O".equals(sea[rowShotInt][colShotInt])) {
                sea[rowShotInt][colShotInt] = "X";

                if (!isDestroyed(sea, allShips)) {
                    System.out.println("\nYou hit a ship!\n");
                } else if (count1 == 5 || count2 == 5) {
                    System.out.println("You sank the last ship. You won. Congratulations!");
                    break;
                } else {
                    System.out.println("\nYou sank a ship!\n");
                }


                break;
            } else if ("~".equals(sea[rowShotInt][colShotInt])) {
                sea[rowShotInt][colShotInt] = "M";

                System.out.println("\nYou missed!\n");
                break;
            } else {
                System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
            }
        } catch (Exception e) {
            System.out.println("\nError! You entered the wrong coordinates! Try again:\n"); // it will be printed
        }
    }
}
            catch (Exception e) {
        System.out.println("\nError! You entered the wrong coordinates! Try again:\n"); // it will be printed
    }
    }

    //Checking if a ship is destroyed or not, also counting destroyed ships
    public static boolean isDestroyed(String[][] sea, char[] allShips) {

        boolean result = false;
        for(int i = 0; i < 20; i+=4) {


            boolean notDestroyed = false;
            if(allShips[i] == 0)
                continue;

            if (allShips[i] == allShips[i+2]) {
                for (int j = allShips[i+1]; j <= allShips[i+3]; j++) {
                    if (sea[allShips[i]][j].equals("O")) {
                        notDestroyed = true;
                        break;
                    }
                }
                if(notDestroyed)
                    continue;

                result = true;

                if(irr % 2 == 0)
                    count2++;
                else
                    count1++;

                allShips[i] = 0;
                return result;
            }
            else if (allShips[i+1] == allShips[i+3]) {
                for (int j = allShips[i]; j <= allShips[i+2]; j++) {
                    if (sea[j][allShips[i + 1]].equals("O")) {
                        notDestroyed = true;
                        break;
                    }
                }
                if(notDestroyed)
                    continue;

                result = true;

                if(irr % 2 == 0)
                    count2++;
                else
                    count1++;

                allShips[i] = 0;
                return result;
            }
        }
        return result;
    }

    //Setting ship of the certain type to the sea
    public static void setUnit(String[][] sea, shipType unit) {

        System.out.printf("\nEnter the coordinates of the %s (%d cells):\n\n", unit.getShipName(), unit.getShipLength());


        Scanner scanner = new Scanner(System.in);



        for (; ; ) {
            // Entering input values
            String startCoordinates = scanner.next();
            String endCoordinates = scanner.next();

            // Starting Coordinates parsing process
            int rowStart = startCoordinates.charAt(0);
            int colStart = 0;
            if(startCoordinates.length() == 3) {
                if((int)(startCoordinates.charAt(1)) == 49 && (int)(startCoordinates.charAt(2)) == 48)
                    colStart = 10;
            } else
                colStart = startCoordinates.charAt(1);

            // Ending Coordinates parsing process
            int rowEnd = endCoordinates.charAt(0);
            int colEnd = 0;
            if(endCoordinates.length() == 3) {
                if((int)(endCoordinates.charAt(1)) == 49 && (int)(endCoordinates.charAt(2)) == 48)
                    colEnd = 10;
            } else
                colEnd = endCoordinates.charAt(1);



            //Converting variables
            int rowStartInt = rowStart - 64;
            int rowEndInt = rowEnd - 64;

            int colStartInt;
            if(colStart == 10)
                colStartInt = 10;
            else
                colStartInt = colStart - 48;

            int colEndInt;
            if(colEnd == 10)
                colEndInt = 10;
            else
                colEndInt = colEnd - 48;

            //Replacing variables if condition is true
            if(rowEndInt < rowStartInt) {
                int temp = rowStartInt;
                rowStartInt = rowEndInt;
                rowEndInt = temp;
            }
            if(colEndInt < colStartInt) {
                int temp = colStartInt;
                colStartInt = colEndInt;
                colEndInt = temp;
            }


            // Error checking
            if ((rowStartInt != rowEndInt && colStartInt != colEndInt)) {
                System.out.println("\nError! Wrong ship location! Try again:\n\n");
                continue;
            }
            if ((rowEndInt - rowStartInt != unit.getShipLength() - 1 && colStartInt == colEndInt)
                    || (colEndInt - colStartInt != unit.getShipLength() - 1) && rowStartInt == rowEndInt) {
                System.out.printf("\nError! Wrong length of the %s! Try again:\n\n", unit.getShipName());
                continue;
            }
            //Ship placing Errors
            boolean errorFlag = false;
            if (rowStartInt == rowEndInt) {
                for (int j = colStartInt; j <= colEndInt; j++) {
                    //1st Error
                    if ("O".equals(sea[rowStartInt][j])) {
                        System.out.println("\nError! This place is already taken!\n\n");
                        errorFlag = true;
                        break;
                    }
                    //2nd Error
                    if((rowStartInt < 10 && rowEndInt < 10 && colStartInt < 10 && colEndInt < 10) && (
                            ("O".equals(sea[rowStartInt - 1][j - 1]))
                                    || ("O".equals(sea[rowStartInt - 1][j]))
                                    || ("O".equals(sea[rowStartInt - 1][j + 1]))
                                    || ("O".equals(sea[rowStartInt][j + 1]))
                                    || ("O".equals(sea[rowStartInt + 1][j + 1]))
                                    || ("O".equals(sea[rowStartInt + 1][j]))
                                    || ("O".equals(sea[rowStartInt + 1][j - 1]))
                                    || ("O".equals(sea[rowStartInt][j - 1]))
                    )) {
                        System.out.println("\nError! You placed it too close to another one. Try again:\n\n");
                        errorFlag = true;
                        break;
                    }
                    //3d Error
                    else if((rowStartInt == 10 && colEndInt < 10) && (
                            ("O".equals(sea[rowStartInt - 1][j - 1]))
                                    || ("O".equals(sea[rowStartInt - 1][j]))
                                    || ("O".equals(sea[rowStartInt - 1][j + 1]))
                                    || ("O".equals(sea[rowStartInt][j + 1]))
                                    || ("O".equals(sea[rowStartInt][j - 1]))
                    )) {
                        System.out.println("\nError! You placed it too close to another one. Try again:\n\n");
                        errorFlag = true;
                        break;
                    }
                    //4th Error
                    else if((rowStartInt == 10 && colEndInt == 10) && (
                            ("O".equals(sea[rowStartInt - 1][j - 1]))
                                    || ("O".equals(sea[rowStartInt - 1][j]))
                                    || ("O".equals(sea[rowStartInt][j - 1]))
                    )) {
                        System.out.println("\nError! You placed it too close to another one. Try again:\n\n");
                        errorFlag = true;
                        break;
                    }
                    //5th Error
                    else if((rowStartInt < 10 && colEndInt == 10) && (
                            ("O".equals(sea[rowStartInt - 1][j - 1]))
                                    || ("O".equals(sea[rowStartInt - 1][j]))
                                    || ("O".equals(sea[rowStartInt][j - 1]))
                                    || ("O".equals(sea[rowStartInt+1][j - 1]))
                                    || ("O".equals(sea[rowStartInt+1][j]))
                    )) {
                        System.out.println("\nError! You placed it too close to another one. Try again:\n\n");
                        errorFlag = true;
                    }
                }
            } else if (colStartInt == colEndInt) {
                for (int i = rowStartInt; i <= rowEndInt; i++) {
                    //1 Error
                    if ("O".equals(sea[i][colStartInt])) {
                        System.out.println("\nError! This place is already taken!\n\n");
                        errorFlag = true;
                        break;
                    }
                    //2nd Error
                    if ((rowStartInt < 10 && rowEndInt < 10 && colStartInt < 10 && colEndInt < 10) && ("O".equals(sea[i - 1][colStartInt - 1])
                            || ("O".equals(sea[i][colStartInt - 1]))
                            || ("O".equals(sea[i + 1][colStartInt - 1]))
                            || ("O".equals(sea[i - 1][colStartInt]))
                            || ("O".equals(sea[i + 1][colStartInt]))
                            || ("O".equals(sea[i - 1][colStartInt + 1]))
                            || ("O".equals(sea[i + 1][colStartInt + 1]))
                            || ("O".equals(sea[i][colStartInt + 1]))
                    )) {
                        System.out.println("\nError! You placed it too close to another one. Try again:\n\n");
                        errorFlag = true;
                        break;
                    }
                    //3rd Error
                    if ((colStartInt == 10 && rowEndInt < 10) && ("O".equals(sea[i - 1][colStartInt - 1])
                            || ("O".equals(sea[i][colStartInt - 1]))
                            || ("O".equals(sea[i + 1][colStartInt - 1]))
                            || ("O".equals(sea[i - 1][colStartInt]))
                            || ("O".equals(sea[i + 1][colStartInt]))
                    )) {
                        System.out.println("\nError! You placed it too close to another one. Try again:\n\n");
                        errorFlag = true;
                        break;
                    }
                    //4th Error
                    if ((colStartInt == 10 && rowEndInt == 10) && ("O".equals(sea[i - 1][colStartInt - 1])
                            || ("O".equals(sea[i][colStartInt - 1]))
                            || ("O".equals(sea[i - 1][colStartInt]))
                    )) {
                        System.out.println("\nError! You placed it too close to another one. Try again:\n\n");
                        errorFlag = true;
                        break;
                    }
                    //5th Error
                    if ((colStartInt < 10 && rowEndInt == 10) && ("O".equals(sea[i - 1][colStartInt - 1])
                            || ("O".equals(sea[i][colStartInt - 1]))
                            || ("O".equals(sea[i - 1][colStartInt]))
                            || ("O".equals(sea[i - 1][colStartInt + 1]))
                            || ("O".equals(sea[i][colStartInt + 1]))
                    )) {
                        System.out.println("\nError! You placed it too close to another one. Try again:\n\n");
                        errorFlag = true;
                        break;
                    }
                }
            }
            if (errorFlag) {
                continue;
            }


            // Setting the Aircraft

            unit.coordinates[0] = (char) rowStartInt;
            unit.coordinates[1] = (char) colStartInt;
            unit.coordinates[2] = (char) rowEndInt;
            unit.coordinates[3] = (char) colEndInt;

            if (rowStartInt == rowEndInt) {
                int j, n = 0;
                for (j = colStartInt, n = 0; j <= colEndInt; j++, n++) {
                    sea[rowStartInt][j] = "O";

                }



                printBattlefield(sea);

                break;
            } else if (colStartInt == colEndInt) {
                for (int i = rowStartInt; i <= rowEndInt; i++) {
                    sea[i][colStartInt] = "O";
                }

                printBattlefield(sea);

                break;
            }

        }
    }
}



class shipType {
    String shipName;
    int shipLength;
    char[] coordinates = new char[4];

    public void setShipName(String name) {
        this.shipName = name;
    }

    public void setShipLength(int len) {
        this.shipLength = len;
    }


    public String getShipName() {
        return this.shipName;
    }
    public int getShipLength() {
        return this.shipLength;
    }
}

