import java.awt.*;

public class Saab95 extends Car<TurboEngine> {
    public Saab95(Position position) {
        super(new TurboEngine(125),position,2, Color.red, "Saab95"); // Anropar baskonstruktorn
    }

    protected void setTurboOn() {
        getEngine().setTurbo(true);                     // Sätter turbo på
    }

    protected void setTurboOff() {
        getEngine().setTurbo(false);                     // Sätter turbo av
    }
}