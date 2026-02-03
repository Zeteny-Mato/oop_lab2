//Please help me boss, im dying, or something....
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


class Volvo240Test {
    private final Volvo240 volvo240 = new Volvo240();
    
        @Test
        void getNrDoors() 
        {
        assertEquals(4, volvo240.getNrDoors());
        }
        @Test
        void getEnginePower() 
        {
        assertEquals(100, volvo240.getEnginePower());
        }
        @Test
        void getCurrentSpeed() 
        {
            assertEquals(0, volvo240.getCurrentSpeed());
        }

        @Test
        void getColor() 
        {
            assertEquals(Color.pink, volvo240.getColor());
        }

        @Test
        void getModelName() 
        {
            assertEquals("Volvo240", volvo240.getModelName());
        }

        @Test
        void startEngine() 
        {
            volvo240.startEngine();
            assertEquals(0.1, volvo240.getCurrentSpeed());
        }

        @Test
        void stopEngine() 
        {
            volvo240.startEngine();
            volvo240.stopEngine();
            assertEquals(0, volvo240.getCurrentSpeed());
        }

        @Test
        void gas()
        {
            volvo240.startEngine();
            volvo240.gas(1);
            assertTrue(volvo240.getCurrentSpeed() > 0.1);
        }

        @Test
        void brake()
        {
            volvo240.startEngine();
            volvo240.gas(1);
            double before = volvo240.getCurrentSpeed();
            volvo240.brake(1);
            assertTrue(volvo240.getCurrentSpeed() < before);
        }

        @Test
        void speedFactor()
        {
            double expected = volvo240.getEnginePower() * 0.01 * volvo240.trimFactor;
            assertEquals(expected, volvo240.speedFactor());
        }

        @Test
        void move()
        {
            volvo240.startEngine();
            double beforeY = volvo240.getY();
            volvo240.move();
            assertTrue(volvo240.getY() > beforeY);
        }

        @Test
        void turnLeft()
        {
            int before = volvo240.getDirection();
            volvo240.turnLeft();
            assertEquals((before + 3) % 4, volvo240.direction);
        }

        @Test
        void turnRight()
        {
            int before = volvo240.getDirection();
            volvo240.turnRight();
            assertEquals((before + 1) % 4, volvo240.direction);
        }
}
      
        








    