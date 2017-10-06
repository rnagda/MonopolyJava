public class Node{
    public String propertyName;
    public int price;
    public int rent;
    public boolean isOwnedPlayerOne;
    public boolean isOwnedPlayerTwo;
    public int spaceNumber;
    public Node next;
    public Node prev;
    
    
    //regular property
    public Node(String name, int x, int y, boolean owned1, boolean owned2, int z) { //name, cost, rent, property owned status, space number 
        propertyName = name; 
        price = x;
        isOwnedPlayerOne = owned1;
        isOwnedPlayerTwo = owned2;
        rent = y; 
        spaceNumber = z;
        next = null;
        prev = null;
    }
    public static void changeOwnership(int player, Node current) {
        if (player == 1) {
            current.isOwnedPlayerOne = true;
        } 
        if (player == 2) {
            current.isOwnedPlayerTwo = true;
        }
    }
//chance
    public Node(String name) {
        propertyName = name;  
        next = null;
        prev = null;
    }
//income tax
    public Node(String name, int a) {
        propertyName = name;
        rent = a; 
        next = null;
        prev = null;
    }
    public static void onNode(Node n) {
        
        System.out.println("Space Number: " + n.spaceNumber + ", Propery Name: " + n.propertyName + ", Price: " + n.price + 
                           ", Rent: " + n.rent);
        
    }
    public static String getPropertyName(Node n) {
        
        return n.propertyName;       
    }
    public static int getPrice(Node n) {
        
        return n.price;       
    }
    public static int getRent(Node n) {
        
        return n.rent;       
    }
    public static boolean getOwnedPlayerOne(Node n) {
        
        return n.isOwnedPlayerOne;       
    }
    public static boolean getOwnedPlayerTwo(Node n) {
        
        return n.isOwnedPlayerTwo;       
    }
    public static int getSpaceNumber(Node n) {
        
        return n.spaceNumber;       
    }
}

