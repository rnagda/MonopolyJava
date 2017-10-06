public class Player {
    private double inX;
    private double beginX;
    private double inY;
    private double beginY;
    private double currentPositionX;
    private static double MOVE = .082;
    public int totalRoll;
    private int money1;
    
    public Player(double originalX, double originalY, int money) { 
        inX = originalX;
        inY = originalY;
        beginX = originalX;
        beginY = originalY;
        //System.out.println(money);
        money1 = money;
    }
    public double currentPositionX() {
        return inX; 
    }
    public double currentPositionY() {
        return inY;
    }
    public int totalRoll() {
        return totalRoll;
    }
    public void addRoll(int roll) {
        if (totalRoll + roll >= 40) {
            totalRoll = totalRoll + roll - 40;
            moneyChange(-200);
        } else {
            totalRoll += roll;
        }
        //System.out.println(totalRoll);
    }
    public void moveX() {
        int roll = 0;
        System.out.println(totalRoll);
        if (totalRoll <= 10) {
            roll = totalRoll;
        } else if (totalRoll <= 20) {
            roll = 10;
        } else if (totalRoll < 30) {
            roll = 10 - (totalRoll % 10);
        } else if (totalRoll <= 40) {
            roll = 0;
            
        }
        //System.out.println(inX);
        inX = beginX - MOVE * (roll);
    }
    public void moveY() {
        double roll = 0;
        if (totalRoll <= 10) {
            roll = 0;
        } else if (totalRoll < 20) {
            roll = (totalRoll % 10);
        } else if (totalRoll <= 30) {
            roll = 10;
        } else if (totalRoll < 40) {
            roll = 10 - (totalRoll % 30);
        }
        inY = beginY + MOVE * (roll); 
        //System.out.println(inY);
    }
    public int currentMoney() {
        return money1;
    }
    public void moneyChange(int n) {
        money1 -= n;
    }
    
}