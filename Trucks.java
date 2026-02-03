//Trucks
public abstract class Trucks extends Car {
    private double angle;

    public Trucks(int nrDoors, double enginePower, java.awt.Color color, String modelName, double angle) {
        super(nrDoors, enginePower, color, modelName);
        this.angle = angle;
    }

    public double getPlatformAngle(){
        return angle;
    }

    public void raisePlatform(double amount) {
        double new_angle = angle + amount;
        if (getCurrentSpeed() != 0 && new_angle < 0 && new_angle > 70) {
            throw new IllegalArgumentException("Speed must be 0 and angle between 0 and 70");
        }
        angle = new_angle;
    }

    public void lowerPlatform(double amount) {
        double new_angle = angle - amount;
        if (getCurrentSpeed() != 0 && new_angle < 0 && new_angle > 70) {
             throw new IllegalArgumentException("Speed must be 0 and angle between 0 and 70");
        } 
        angle = new_angle;
        //antingen göra så att man får ett felmeddelande eller att den sätts till max eller min
    }

    
}