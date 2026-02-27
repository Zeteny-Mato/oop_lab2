//Should be fully functional, however how Truck inhers to these rules are
//determined by how we use these method in Trucks when we declare a Platform
public class AngularPlatform implements Platform{
    private double angle;
    private final double maxAngle = 70;
    private final double minAngle = 0;
    private final int amount = 10;

    public AngularPlatform(double startDegree){
        this.angle = startDegree;
    }

    public double getangle(){
        return angle;
    }

    //should check if platform is down correctly i think? 
    @Override
    public boolean platformState() {
        return angle >= 70;
    }

    //Raises platform
    @Override
    public void raise() {
        raiseOrLowerPlatform(amount);
    }

    //Lowers platform
    @Override
    public void lower(){
        raiseOrLowerPlatform(-amount);
    }

    //stages changes of platform
    private void raiseOrLowerPlatform(double amount) {
        double newAngle = angle + amount;
        if (newAngle < minAngle || newAngle > maxAngle) {
            throw new IllegalArgumentException("Angle must be between 0 and 70");
        }
        angle = newAngle;
    }
}
