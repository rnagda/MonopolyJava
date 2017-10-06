public class monopolyBoard{ 
    public Node head;
    public Node tail;
    
    public void Board(){
        head = null;
        tail = null;
    }
    public static int Chance() {
        double random = Math.ceil(4 * Math.random());
        if (random == 1.0) {
            return -50;
        }
        else if (random == 2.0) {
            return 50;
        }
        else if (random == 3.0) {
            //Go to Jail
            return 1;
        }
        else {
            //Go back three spaces
            return 3;
        }
    }
    
    public boolean isEmpty(){
        return (head == null);
    }
    public Node find(int x){
        Node current = head;
        int a = 0;
        while(a != x) {
            current = current.next;
            a = Node.getSpaceNumber(current);
            //   System.out.println(a);
            
        }
        return current; 
    }    
    
    public void enqueue(String name, int x, int y, boolean owned, boolean ownedTwo, int z) {
        //create the enqueue node
        Node newNode = new Node(name, x, y, owned, owned, z);
        
        if (isEmpty()){
            head = newNode;
            tail = newNode;
            tail.next = head;
            head.next = tail; 
        }
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            tail.next = head;
        }
    }
    
    public static void main(String[] args) {
        monopolyBoard a = new monopolyBoard();
        In inFile = new In("Space.txt");
        int counter = 0;
        int price = 20;
        int rent = 0;
        for (int i = 0; i < 40; i++) {
            if (i == 0) {
                a.enqueue("Go" , 0 , -200, true, true, i);
            }
            else if (i % 10 == 2) {
                a.enqueue("Income tax" , 0 , 150, false, false, i);
            }
            else if (i == 10) {
                a.enqueue("Jail" , 0 , 0, true, true, i);
            }
            else if (i == 20) {
                a.enqueue("Free Space" , 0 , 0, true, true, i);
            }
            else if (i == 30) {
                a.enqueue("Go To Jail" , 0 , 0, true, true, i);
            }
            else if (i % 10 == 7) { 
                a.enqueue("Chance" , 0 , 0 , true, true, i);
            }
            else if (i % 10 != 2 || i % 10 != 7 || i % 10 != 0) {
                
                if (i % 10 == 1 || i % 10 == 6) {
                    price = price + 40;
                    rent = rent + 5; 
                }
                if (i % 10 == 5) {
                    rent = 50; 
                    price = 200;
                }
                a.enqueue(inFile.readString(), price, rent, false, false, i);
                counter++;
                // System.out.println(counter);
            }
            
        }
        
        // Node test = new Node("hi", 0, 50, true, 0);
//    Node.onNode(test);
//    System.out.println(Node.getSpaceNumber(test));
//    System.out.println(Node.getPropertyName(test));
//    System.out.println(Node.getPrice(test));
//    System.out.println(Node.getRent(test));
//    System.out.println(Node.getOwned(test));
//    //Node.onNode(head.next);
        //   System.out.println(Node.getPropertyName(a.find(0)));
        for (int j = 0; j < 40; j++){
            //System.out.println(j);
            System.out.println("SpaceNumber: " + Node.getSpaceNumber(a.find(j)));
            System.out.println("Price: " + Node.getPrice(a.find(j)));
            System.out.println("Rent: " + Node.getRent(a.find(j)));
            System.out.println(Node.getPropertyName(a.find(j)));
        }
//    System.out.println(Node.getPropertyName(a.find(1)));
//    System.out.println(Node.getPropertyName(a.find(2)));
        //  Node.onNode(a.find(1));
    }
}