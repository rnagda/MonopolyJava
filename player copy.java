public class player {   
    private double move = .082;
    private double initialX1 = .91;
    private double initialY1 = .1;
    private double initialX2 = .96;
    private double initialY2 = .04;
    String name;
    int totalRoll = 0; 
    double currentPositionX;
    double positionX;
    double positionY;
    int money;
    int roll1;
    
    public player(String game, int roll) { 
        game = name;
        totalRoll += roll;
        money = 1500; 
    }
    public double math(double initial) {
        if (totalRoll <= 10) {
            currentPositionX = initial - (totalRoll * move); 
        } else {
            currentPositionX = initial - ((totalRoll - (totalRoll % 10)) * move);
        }
        return currentPositionX;
    }   
    public double xPos() {
        math(initialX1);
        return currentPositionX;
    }
    public double yPos() {
        return positionY;
    }
    public String getName() {
        return name;
    }
}