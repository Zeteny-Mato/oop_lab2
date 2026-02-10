import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


class ScaniaTest {
    private final Scania scania = new Scania();

    @Test
    void getNrDoors() {
        assertEquals(2,scania.getNrDoors());
    }

    @Test
    void getEnginePower() {
        assertEquals(400,scania.getEnginePower());
    }

    @Test
    void getColor() {
        assertEquals(Color.RED, scania.getColor());
    }

    @Test
    void lowerPlatform() {
        scania.lowerPlatform(70);
        assertEquals(0, scania.getPlatformAngle());
    }

    @Test
    void raisePlatform() {
        scania.lowerPlatform(70);
        scania.raisePlatform(70);
        assertEquals(70, scania.getPlatformAngle());
    }
}