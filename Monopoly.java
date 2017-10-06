import java.util.ArrayList;
public class Monopoly {
    private static ArrayList<String> propertiesOne = new ArrayList<String>(28);
    private static ArrayList<String> propertiesTwo = new ArrayList<String>(28);
    private int counter = 0;
    /* This is the start of the game
     * It simply requires the player 
     * to press space to start the game
     */
    private static void begin() {
        
        // Instructions for user
        line(19.0/20, "Welcome to Monopoly" );
        line(17.0/20, "To begin, press the spacebar");
        lineC(1.0/20, "Created by Romit Nagda");
        while (true) {
            if (PennDraw.hasNextKeyTyped()) {
                char letter = PennDraw.nextKeyTyped();
                
                if (" ".indexOf(letter) != -1) {
                    return;
                } 
            }
        }
    }
    private static int numPlay() {
        line(18.0/20, "Enter the number of players (1-4)");
        while (true) {
            if (PennDraw.hasNextKeyTyped()) {
                char num = PennDraw.nextKeyTyped();
                int numero = num - 48;
                if ("1234 ".indexOf(num) != -1) {
                    return numero;
                } 
            }
        }
    }
    /* This method rolls the first dice
     * Pressing the space bar initializes the roll
     */
    private static int rollDice1() { 
        lineG(16.3 / 20, "Press spacebar to roll dice");
        int die1 = 0;
        while (true) {
            if (PennDraw.hasNextKeyTyped()) {
                char space = PennDraw.nextKeyTyped();  
                if (" ".indexOf(space) != -1) {
                    /* Each run through randomly picks one of 
                     * the 6 die faces and that image is displayed
                     * enableAnimation lets us display each face
                     * and make it seem like we are shuffling through
                     * die faces
                     */
                    PennDraw.enableAnimation(20);
                    boolean user = true;
                    int time = 0;      
                    
                    while (user == true) {
                        int dice1 = (int) Math.ceil(Math.random() * 6);
                        String pic = "die" + dice1 + ".png";   
                        PennDraw.picture(.23, .75, pic);
                        /* After 40 iterations save the 
                         * final die face as the die 
                         * that is rolled.
                         */
                        time++; 
                        if (time == 40) {
                            die1 = dice1;
                            user = false;
                        }
                        PennDraw.advance();
                    }
                    
                    PennDraw.disableAnimation();
                    // Returns value of die rolled
                    return die1;
                }
            }
        }
    } 
    // Repeats rollDice1 for second die
    private static int rollDice2() { 
        int die2 = 0;
        while (true) {
            PennDraw.enableAnimation(20);
            boolean user = true;
            int time = 0;            
            while (user == true) {
                int dice2 = (int) Math.ceil(Math.random() * 6);
                String pic1 = "die" + dice2 + ".png";
                // Draws second die adjacent to first 
                PennDraw.picture(.315, .75, pic1);
                
                time++; 
                if (time == 40) {
                    die2 = dice2;
                    user = false;
                }
                PennDraw.advance();
            }
            PennDraw.disableAnimation();
            // Returns value of the second die
            return die2; 
        }
    }
    /* Gives each player to pick a game piece
     * Depening on which number is picked
     * a string containing the name of the 
     * image file for that piece is sent returned
     */
    private static String gamePiece(String player) {
        // Calls line to print out a string at a certain location on the canvas
        line(19.0/20, player + " choose a game piece (Enter a number from 1-6)");
        line(17.0/20, "1. Iron");
        line(16.0/20, "2. Shoe");
        line(15.0/20, "3. Dog");
        line(14.0/20, "4. Ship");
        line(13.0/20, "5. Car");
        line(12.0/20, "6. Hat");
        while (true) {
            if (PennDraw.hasNextKeyTyped()) {
                int letter = PennDraw.nextKeyTyped() - 48;
                if (letter == 1) return "iron.png";
                if (letter == 2) return "shoe.png";
                if (letter == 3) return "dog.png";
                if (letter == 4) return "ship.png";
                if (letter == 5) return "car.png";
                if (letter == 6) return "hat.png";
            }
        } 
        
    }
    /* This line method is used to write statements
     * on the left side of the canvas (x-location: .1)
     * It takes in a double pos that is the y-position
     * for each message and a string msg that is the message
     */
    private static void line(double pos, String msg) {
        PennDraw.textLeft(.1, pos, msg);
    }
    /* This line method is used to write statements
     * less to the left side of the canvas (x-location: .15)
     * It is used to write the message of "Press Space to 
     * roll dice" and "press space to see properties owned"
     */
    private static void lineG(double pos, String msg) {
        PennDraw.textLeft(.15, pos, msg);
    }
    /* This line method is used to write statements
     * near the center of the canvas (x-location: .5)
     * It is used to write property info on the card
     * when player lands on a property
     */
    private static void lineC(double pos, String msg) {
        PennDraw.textLeft(.5, pos, msg);
    }
    /* This line method is used to write statements
     * near the right of the canvas (x-location: .65)
     * It is used to write current money of player
     * and whose turn it is
     */
    private static void lineM(double pos, String msg) {
        PennDraw.textLeft(.65, pos, msg);
    }
    /* This method uses a linked list to create the game board
     * The linked list has 40 nodes, each with specific 
     * properties. Specific types of spots on the board
     * are grouped with similar properties and then 
     * we use enqueue to create that node.
     */
    private static void createBoard(monopolyBoard a){
        //Inputs the file of all the properties
        In inFile = new In("Space.txt");
        //sets the initial price
        int price = 20;
        //sets the initial rent
        int rent = 0;
        //for loop to iterate through the properties
        for (int i = 0; i < 40; i++) {
            //Adds the Go space
            if (i == 0) {
                a.enqueue("Go" , 0 , -200, true, true, i);
            }
            //Adds Income tax
            else if (i % 10 == 2) {
                a.enqueue("Income tax" , 0 , 150, false, false, i);
            }
            //Adds Jail
            else if (i == 10) {
                a.enqueue("Jail" , 0 , 0, true, true, i);
            }
            //Adds Free Space
            else if (i == 20) {
                a.enqueue("Free Space" , 0 , 0, true, true, i);
            }
            //Adds Go To Jail
            else if (i == 30) {
                a.enqueue("Go To Jail" , 0 , 0, true, true, i);
            }
            else if (i % 10 == 7) { 
                a.enqueue("Chance" , 0 , 0 , true, true, i);
            }
            else if (i % 10 == 5) {
                 a.enqueue(inFile.readString(), 200, 50, false, false, i);
            }
            //Loop to add all the properties
            else if (i % 10 != 2 || i % 10 != 7 || i % 10 != 0) {
                //loop to iterate through to increase price and rent
                if (i % 10 == 1 || i % 10 == 6) {
                    price = price + 40;
                    rent = rent + 5; 
                }
                //Adds the properties
                a.enqueue(inFile.readString(), price, rent, false, false, i);
            }
        }
    }
    /* This method returns back the outcome of landing on 
     * each space. Depending on what space the player lands on
     * the method calls that node to get information about 
     * that space.
     */
    private static int printInfo(Node current,int money, int player) {
        // Print out name of property at specific location
        lineC(11.6 / 20, Node.getPropertyName(current));
        // Initialize the return variable 
        int returnVal = 0;
        /* The way the board was created, the 7, 17, 27,
         * and 37 nodes are Chance so we look to see if 
         * the node landed on is a chance
         */
        if (Node.getSpaceNumber(current) % 10 == 7) {
            // call Chance method in monopolyBoard
            returnVal = monopolyBoard.Chance();
            // Chance randomly returns one of 4 outcoms
            lineC(9.0 / 20, "The Chance card result:");
            // If it returns -50, the player made $50
            // The outcome of the turn was -50
            if (returnVal  == -50) {
                lineC(8.0 / 20, "You got $50");
            }
            // If it returns 50, the player loses $50
            if (returnVal  == 50) {
                lineC(8.0 / 20, "You lost $50");
            }
            // If 1 is returned player goes to jail
            // 1 is given a meaning in PlayerTurn 
            // where 1 means to move player to jail
            if (returnVal  == 1) {
                lineC(8.0 / 20, "You go to jail");
            }
            // 3 signifies moving back 3 spots
            // 3 is also given meaning in PlayerTurn
            if (returnVal  == 3) {
                lineC(8.0 / 20, "You move back 3 spaces");
            }
            // End turn
            lineC(7.0 / 20, "Press any key to end turn");
            while (true) {
                if (PennDraw.hasNextKeyTyped()) {
                    break;
                }
            }
        } 
        // Node 2, 12, 22, 32 are Income taxes
        else if (Node.getSpaceNumber(current) % 10 == 2) {
            // Outcome of turn is loss of 150
            returnVal = 150;
            lineC(9.0 / 20, "You paid $150 Income Tax");
            // End turn
            lineC(8.0 / 20, "Press any key to end turn");
            while (true) {
                if (PennDraw.hasNextKeyTyped()) {
                    break;
                }
            }
        } 
        // Node 30 is Go To Jail space
        else if (Node.getSpaceNumber(current) == 30) {
            // Returning 1 signifies moving player to jail
            returnVal = 1;
            lineC(9.0 / 20, "You go to jail");
            // End turn
            lineC(8.0 / 20, "Press any key to end turn");
            while (true) {
                if (PennDraw.hasNextKeyTyped()) {
                    break;
                }
            }
        } else if (player == 1) {
            // If player 1 lands on a player two property, pay rent
            if (Node.getOwnedPlayerTwo(current) == true) {
                lineC(9.0 / 20, "Property Owned");
                // Get Rent of property by calling getRent on that node
                lineC(8.0 / 20, "Pay Rent: $" + Node.getRent(current));
                lineC(7.0 / 20, "Press any key to end turn");
                while (true) {
                    if (PennDraw.hasNextKeyTyped()) {
                        // 5 holds significance in PlayerTurn
                        returnVal = 5;
                        // End turn
                        break;
                    }
                }
            } 
            // If player 1 lands on unowned property, option of buying 
            else if (Node.getOwnedPlayerOne(current) == false) {
                // Get price of property by calling on getPrice of node
                lineC(9.0 / 20, "Buy Property for $" + Node.getPrice(current));
                lineC(8.0 / 20, "Press 1 to purchase");
                lineC(7.0 / 20, "Press any key to end turn");
                while (true) {
                    if (PennDraw.hasNextKeyTyped()) {
                        // '1' has character value 49, 49-48 = 1
                        int letter = PennDraw.nextKeyTyped() - 48;
                        // Check to see if 1 was pressed
                        if (letter == 1) {
                            // If bought, outcome of turn is cost of property bought
                            returnVal = Node.getPrice(current);
                            // Adds property to list of properties bought by player 1
                            propertiesOne.add(Node.getPropertyName(current));
                            // End turn
                            break;
                        }
                        else {
                            // If not bought no money change, outcome = 0
                            returnVal = 0;
                            // End turn
                            break;
                        }
                    }
                }
            } else {
                // If property isn't owned by player two and 
                // propert is owned, player 1 must own
                lineC(9.0 / 20, "You own this property");
                lineC(8.0 / 20, "Press any key to end turn");
                while (true) {
                    if (PennDraw.hasNextKeyTyped()) {
                        // No change in money, so 0 outcome
                        returnVal = 0;
                        // End turn
                        break;
                    }
                }
            }
        } else if (player == 2) {
            // If player 2 lands on a player 1 property, pay rent
            if (Node.getOwnedPlayerOne(current) == true) {
                lineC(9.0 / 20, "Property Owned");
                // Get Rent of property by calling getRent on that node
                lineC(8.0 / 20, "Pay Rent: $" + Node.getRent(current));
                lineC(7.0 / 20, "Press any key to end turn");
                while (true) {
                    if (PennDraw.hasNextKeyTyped()) {
                        // 5 holds significance in PlayerTurn
                        returnVal = 5;
                        // End turn
                        break;
                    }
                }
            } 
            // If player 2 lands on unowned property, option of buying 
            else if (Node.getOwnedPlayerTwo(current) == false) {
                // Get price of property by calling on getPrice of node
                lineC(9.0 / 20, "Buy Property for $" + Node.getPrice(current));
                lineC(8.0 / 20, "Press 1 to purchase");
                lineC(7.0 / 20, "Press any key to end turn");
                while (true) {
                    if (PennDraw.hasNextKeyTyped()) {
                        // '1' has character value 49, 49-48 = 1
                        int letter = PennDraw.nextKeyTyped() - 48;
                        // Check to see if 1 was pressed
                        if (letter == 1) {
                            // If bought, outcome of turn is cost of property bought
                            returnVal = Node.getPrice(current);
                            // Adds property to list of properties bought by player 2
                            propertiesTwo.add(Node.getPropertyName(current));
                            // End turn
                            break;
                        }
                        else {
                            // If not bought no money change, outcome = 0
                            returnVal = 0;
                            // End turn
                            break;
                        }
                    }
                }
            } else {
                // If property isn't owned by player 1 and 
                // propert is owned, player 2 must own
                lineC(9.0 / 20, "You own this property");
                lineC(8.0 / 20, "Press any key to end turn");
                while (true) {
                    if (PennDraw.hasNextKeyTyped()) {
                        // No change in money, so 0 outcome
                        returnVal = 0;
                        // End turn
                        break;
                    }
                }
            }
        }
        // Returns outcome of the turn 
        // Outcome is the change in money
        // Positive returnVal means loss of money
        return returnVal;
    }
    // Redraws board with players in new position
    // Closes the card that opens when a player lands on a spot
    // Resets dice roll
    private static void draw(double x1, double y1, double x2, 
                             double y2, String g1, String g2, Player one, Player two) {
        // Clear canvas
        PennDraw.clear();
        // Draw board
        PennDraw.picture(.5, .5, "monopoly.png");
        // Show the money left for player after turn
        lineM(16.7 / 20, "Player 1 has $" + one.currentMoney());
        lineM(15.9 / 20, "Player 2 has $" + two.currentMoney());
        // Draw player pieces in new location
        PennDraw.picture(x1, y1, g1);
        PennDraw.picture(x2, y2, g2);
    }
    /* Simulation of one rount that consists of player 1 
     * and player 2 playing one turn each
     * Input: two players profiles, their gamepiece, 
     * and linked list board
     * Output: the total change in money between two players
     */
    private static int playerTurn(Player player1, Player player2, 
                                  String gamePiece1, String gamePiece2, monopolyBoard a) {
        lineM(10.0 / 20, "Player 1 turn");
        // Roll dice 1
        int dice1 = rollDice1();
        // Roll dice 2
        int dice2 = rollDice2();
        // Total the outcomes of the rolls
        int roll = dice1 + dice2;
        // Add the roll to totalRoll
        // totalRoll tallies what node player is at
        player1.addRoll(roll);
        // Move the player 
        player1.moveX();
        player1.moveY();
        // Draw the player
        PennDraw.picture(player1.currentPositionX(), player1.currentPositionY(), gamePiece1);
        PennDraw.picture(player2.currentPositionX(), player2.currentPositionY(), gamePiece2);
        // Set current to the node player ends at end of turn
        int total = player1.totalRoll;
        Node current = a.find(total);
        // Draw card to display info of that node
        PennDraw.picture(.6, .45, "card.jpg");
        // call printInfo to get info of the node
        // get the outcome (money change) of turn
        int turnOutcome = printInfo(current, player1.currentMoney(), 1);
        /*
         System.out.println(turnOutcome);
         System.out.println(current.isOwnedPlayerOne);
         System.out.println(Node.getPropertyName(current));
         */
        // If 1 is returned, set player to node 10 (jail)
        if (turnOutcome == 1) {
            player1.totalRoll = 10;
            player1.moveX();
            player1.moveY();
        } 
        // If 3 returned, move player back 3 spaces
        else if (turnOutcome == 3) {
            player1.addRoll(-3);
            player1.moveX();
            player1.moveY();
        }
        // If 5 returned, take rent from one player
        // Add rent to other player
        else if (turnOutcome == 5) {
            player1.moneyChange(Node.getRent(current));
            player2.moneyChange((-1) * Node.getRent(current));
        } 
        // If outcome represents that property was bought
        // Change boolean of own to true for that property
        else {
            if (turnOutcome % 40 == 20 || turnOutcome == 200) {
                Node.changeOwnership(1, current);
            } 
            // Subtract the price of property from player money
            player1.moneyChange(turnOutcome);
        }
        // call Draw to refresh board
        draw(player1.currentPositionX(), player1.currentPositionY(), 
             player2.currentPositionX(), player2.currentPositionY(), 
             gamePiece1, gamePiece2, player1, player2);
        
        /*
         for (int j = 0; j < 40; j++){
         System.out.println(j);
         System.out.println((a.find(j)).isOwnedPlayerOne);
         }
         */
        lineM(10.0 / 20, "Player 2 turn");
        // Roll dice 1 for Player 2
        int dice3 = rollDice1();
        // Roll dice  2 for Pplayer 2
        int dice4 = rollDice2();
        // Sum the dice outcomes
        int roll1 = dice3 + dice4;
        // Add outcome to totalRoll
        player2.addRoll(roll1);
        // Move player 2
        player2.moveX();
        player2.moveY();
        // Draw player's new piece locations 
        PennDraw.picture(player1.currentPositionX(), player1.currentPositionY(), gamePiece1);
        PennDraw.picture(player2.currentPositionX(), player2.currentPositionY(), gamePiece2);
        // Set current to the node player ends at end of turn
        int total1 = player2.totalRoll;
        Node current1 = a.find(total1);
        // Draw card to display info of that node
        PennDraw.picture(.6, .45, "card.jpg");
        // call printInfo to get info of the node
        // get the outcome (money change) of turn
        int turnOutcome1 = printInfo(current1, player2.currentMoney(), 2);
        // If 1 is returned, set player to node 10 (jail)
        if (turnOutcome1 == 1) {
            player2.totalRoll = 10;
            player2.moveX();
            player2.moveY();
        } 
        // If 3 returned, move player back 3 spaces
        else if (turnOutcome1 == 3) {
            player2.addRoll(-3);
            player2.moveX();
            player2.moveY();
        } 
        // If 5 returned, take rent from one player
        // Add rent to other player
        else if (turnOutcome1 == 5) {
            player2.moneyChange(Node.getRent(current1));
            player1.moneyChange((-1) * Node.getRent(current1));
        } 
        // If outcome represents that property was bought
        // Change boolean of own to true for that property
        else {
            if (turnOutcome1 % 40 == 20 || turnOutcome1 == 200) {
                Node.changeOwnership(2, current1);
            } 
            // Subtract the price of property from player money
            player2.moneyChange(turnOutcome1);
        }
        // Draw refreshed board
        draw(player1.currentPositionX(), player1.currentPositionY(), 
             player2.currentPositionX(), player2.currentPositionY(), 
             gamePiece1, gamePiece2, player1, player2);
        // Give player option of seeing what properties each player owns
        lineG(16.3 / 20, "Press Space Bar to see properties owned");
        while (true) {
            if (PennDraw.hasNextKeyTyped()) {
                char letter = PennDraw.nextKeyTyped();
                
                if (" ".indexOf(letter) != -1) {
                    PennDraw.clear();
                    double y = 18.4;
                    // Incrementally display player 1s properties 
                    line(19.4 / 20, "Player 1 Owns: ");
                    for (String property: propertiesOne) {
                        line(y / 20, property);
                        y -= .7;
                    }
                    double x = 18.4;
                    lineM(19.4 / 20, "Player 2 Owns: ");
                    for (String property2: propertiesTwo) {
                        // Incrementally display player 2s properties
                        lineM(x / 20, property2);
                        x -= .7;
                    }
                    lineC(2.0/20, "Press Space Bar to continue with game");
                    while (true) {
                        if (PennDraw.hasNextKeyTyped()) {
                            char letter1 = PennDraw.nextKeyTyped();
                            
                            if (" ".indexOf(letter1) != -1) {
                                // Return to game
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }
        // Draw board again
        draw(player1.currentPositionX(), player1.currentPositionY(), 
             player2.currentPositionX(), player2.currentPositionY(), 
             gamePiece1, gamePiece2, player1, player2);
        
        int totalMoney = player1.currentMoney() + player2.currentMoney();
        // Return total change of money
        return totalMoney;
    }
    /* This method recursively calls playerTurn until one player is below 0
     * It checks if a player is at or below 0 if the total money less than or equal
     * to either one of their currentMoney which indicates one player lost
     */
    public static void play(Player player1, Player player2, String gamePiece1, String gamePiece2, monopolyBoard a) {
        int money = playerTurn(player1, player2, gamePiece1, gamePiece2, a);
        int money1 = money - player1.currentMoney();
        int money2 = money - player2.currentMoney();
        while (money1 > 0 && money2 > 0){
            play(player1, player2, gamePiece1, gamePiece2, a);
        }
    }
    public static void main(String[]args) {
        PennDraw.setCanvasSize(690, 690);
        // Create monopoly board
        monopolyBoard a = new monopolyBoard();
        createBoard(a);
        /*
         for (int j = 0; j < 40; j++){
         //System.out.println(j);
         System.out.println("SpaceNumber: " + Node.getSpaceNumber(a.find(j)));
         System.out.println("Price: " + Node.getPrice(a.find(j)));
         System.out.println("Rent: " + Node.getRent(a.find(j)));
         System.out.println(Node.getPropertyName(a.find(j)));
         System.out.println(Node.getOwnedPlayerOne(a.find(j)));
         System.out.println(Node.getOwnedPlayerTwo(a.find(j)));
         }
         */
        begin();
        PennDraw.clear();
        int numPlayers = numPlay();
        System.out.println(numPlayers);
        PennDraw.clear();
        // Create game pieces
        String gamePiece1 = "";
        String gamePiece2 = ""; 
        gamePiece1 = gamePiece("Player 1");
        PennDraw.clear();
        gamePiece2 = gamePiece("Player 2");        
        PennDraw.clear();
        // Create player objects
        Player player1 = new Player(.91, .1, 1500);
        Player player2 = new Player(.91, .1, 1500);
        // Draw board
        draw(player1.currentPositionX(), player1.currentPositionY(), 
             player2.currentPositionX(), player2.currentPositionY(), 
             gamePiece1, gamePiece2, player1, player2);
        // Call play to play the game
        play(player1, player2, gamePiece1, gamePiece2, a);
        // Determine winner
        if (player1.currentMoney() <= 0) {
            PennDraw.clear();
            lineC(10.0/20, "Player 2 wins!!!");
        } if (player2.currentMoney() <= 0) {
            PennDraw.clear();
            lineC(10.0/20, "Player 1 wins!!!");
        }
    }
}
