//Scania
import java.awt.*;
public class Scania extends Trucks {

    public Scania() {
        super(2, 400, Color.RED, "Scania", 0); // Skapar en Scania med 2 dörrar och 400 i motorstyrka
    }

    @Override
    protected double speedFactor() {
        if (getPlatformAngle() == 0) {
            return 0; // Ingen acceleration om plattformen är upphöjd
        }
        else { 
            return getEnginePower() * 0.01; // Justerar acceleration 
        }
    }
}