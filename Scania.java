//Scania
import java.awt.*;
public class Scania extends Trucks<CombustionEngine, AngularPlatform>{
    public Scania(Position position) {
        super(new CombustionEngine(400),position, new AngularPlatform(70),2, Color.RED, "Scania"); // Skapar en Scania med 2 dörrar och 400 i motorstyrka
    }
}