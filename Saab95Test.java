import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;

//TODO Fix getCurrentSpeed what do we compare the method too? Fix Saab95 specific cases
public class Saab95Test {
    private Saab95 saab95 = new Saab95();

    //Car testcases
    @Test
    void getNrDoors() {
        assertEquals(2,saab95.getNrDoors());
    }

    @Test
    void getEnginePower() {
        assertEquals(125,saab95.getEnginePower());
    }

    @Test
    void getCurrentSpeed() {
        assertEquals(0,saab95.getCurrentSpeed());
    }

    @Test
    void getColor() {
        assertEquals(Color.red,saab95.getColor());
    }

    @Test
    void getModelName() {
        assertEquals("Saab95",saab95.getModelName());
    }

    //SAAB specific testcases
    @Test
    void setTurboOn(){
        saab95.setTurboOn();
        assertTrue(saab95.turboOn);
    }

    @Test
    void setTurboOff(){
        saab95.setTurboOff();
        assertFalse(saab95.turboOn);
    }
    @Test
        void startEngine() 
        {
            saab95.startEngine();
            assertEquals(0.1, saab95.getCurrentSpeed());
        }

        @Test
        void stopEngine() 
        {
            saab95.startEngine();
            saab95.stopEngine();
            assertEquals(0, saab95.getCurrentSpeed());
        }

        @Test
        void gas()
        {
            saab95.startEngine();
            saab95.gas(1);
            assertTrue(saab95.getCurrentSpeed() > 0.1);
        }

        @Test
        void brake()
        {
            saab95.startEngine();
            saab95.gas(1);
            double before = saab95.getCurrentSpeed();
            saab95.brake(1);
            assertTrue(saab95.getCurrentSpeed() < before);
        }

        @Test
        void speedFactor()
        {
            double expected;
            if(saab95.turboOn)
            {
                expected = saab95.getEnginePower() * 0.01 * 1.3;
            }
            else
            {
                expected = saab95.getEnginePower() * 0.01 * 1.0;

            }

            assertEquals(expected, saab95.speedFactor());
        }

        @Test
        void move()
        {
            saab95.startEngine();
            double beforeY = saab95.getY();
            saab95.move();
            assertTrue(saab95.getY() > beforeY);
        }

        @Test
        void turnLeft()
        {
            int before = saab95.getDirection();
            saab95.turnLeft();
            assertEquals((before + 3) % 4, saab95.direction);
        }

        @Test
        void turnRight()
        {
            int before = saab95.getDirection();
            saab95.turnRight();
            assertEquals((before + 1) % 4, saab95.direction);
        }
}


