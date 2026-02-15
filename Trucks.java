//Trucks
public abstract class Trucks extends Car {
    private double angle; //vinkel på flaket 0 = nere, 70 = uppe


    public Trucks(int nrDoors, double enginePower, java.awt.Color color, String modelName, double angle) {
        super(nrDoors, enginePower, color, modelName);
        this.angle = angle; //Sätter startvinkeln
    }

    public double getPlatformAngle(){
        return angle; //Returnerar vinkeln på flaket
    }

    public void raiseorlowerPlatform(double amount) {
        double new_angle = angle + amount;
        if (getCurrentSpeed() != 0 )
            throw new IllegalStateException("Speed must be 0 to raise the platform");
        if (new_angle < 0 || new_angle > 70) {
            throw new IllegalArgumentException("Angle must be between 0 and 70");
        }
        angle = new_angle;
    }

    
    @Override
    protected double speedFactor() {
        if (getPlatformAngle() == 0) {
            return 0; // Ingen acceleration om plattformen är nere. 
        }
        return getEnginePower() * 0.01; // Justerar acceleration 
        
    }

    @Override
    public void gas(double amount) {
        if (getPlatformAngle() == 0) {
            throw new IllegalStateException("Cannot accelerate with platform down");
        }
        super.gas(amount);
    }


    
// 0 är nere, 70 är uppe
    
}