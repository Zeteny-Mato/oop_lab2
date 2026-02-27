import java.awt.*;

public class Volvo240 extends Car<CombustionEngine> {
    
    public Volvo240(Position position) {
        super(new CombustionEngine(100),position,4, Color.pink, "Volvo240"); // Anropar baskonstruktorn
    }
}
