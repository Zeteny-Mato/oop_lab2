//Scania
import java.awt.*;
public class Scania extends Trucks {
    private double platformAngle; // Vinkeln på lastbilens plattform

    public Scania() {
        super(2, 400, Color.RED, "Scania"); // Skapar en Scania med 2 dörrar och 400 i motorstyrka
        this.platformAngle = 0; // plattformsvinkeln = 0
    }

    public double getPlatformAngle() {
        return platformAngle; // Returnerar plattformsvinkeln
    }

    public void raisePlatform(double angle) {
        double new_angle = platformAngle + angle;
        if (getCurrentSpeed() == 0 && angle >= 0 && angle <= 70 && angle >= 0) {
            if (new_angle < 0) {
                platformAngle = 0; // Sätter plattformsvinkeln till 0 om den nya vinkeln är mindre än 0
                
            }
            else if (new_angle > 70) {
                platformAngle = 70; // Sätter plattformsvinkeln till 70 om den nya vinkeln är större än 70 
            }
            else {
               platformAngle = new_angle; // Sänker plattformen om hastigheten är 0 och vinkeln är inom gränserna
            }
        }
    }

    public void lowerPlatform(double angle) {
        double new_angle = platformAngle - angle;
        if (getCurrentSpeed() == 0 && angle >= 0 && angle <= 70 && angle >= 0) {
            if (new_angle < 0) {
                platformAngle = 0; // Sätter plattformsvinkeln till 0 om den nya vinkeln är mindre än 0
                
            }
            else if (new_angle > 70) {
                platformAngle = 70; // Sätter plattformsvinkeln till 70 om den nya vinkeln är större än 70 
            }
            else {
               platformAngle = new_angle; // Sänker plattformen om hastigheten är 0 och vinkeln är inom gränserna
            }
        }
    }

    @Override
    protected double speedFactor() {
        if (platformAngle > 0) {
            return 0; // Ingen acceleration om plattformen är upphöjd
        }
        else { 
            return getEnginePower() * 0.01; // Justerar acceleration 
        }
    }
}