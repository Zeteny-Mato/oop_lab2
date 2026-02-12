import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;

public class CarTransportTest {
    private CarTransport carTransport =
    new CarTransport(2,300,Color.BLUE, "CarTransport", 6);

    @Test
    void getCapacity(){
        assertEquals(5, carTransport.getCapacity());
    }

    @Test
    void getCurrentLoad(){ //init check
        assertEquals(0, carTransport.getCurrentLoad());
    }

    @Test
    void loadCar(){
        carTransport.loadCar();
        assertEquals(1, carTransport.getCurrentLoad());
    }

    @Test
    void unloadCar(){
        carTransport.unloadCar();
        assertEquals(0, carTransport.getCurrentLoad());
    }

    @Test
    void capacityCheck(){
        for(int i = 0; i < 6; i++) {
            carTransport.loadCar();
        }
        assertThrows(IllegalArgumentException.class, carTransport::loadCar);
    }

    @Test
    void CannotUnloadWhenEmpty(){
        assertThrows(IllegalArgumentException.class, carTransport::unloadCar);
    }

    @Test
    void cannotLoadWhenMoving(){
        carTransport.startEngine();
        carTransport.gas(1.0);

        assertThrows(IllegalArgumentException.class, carTransport::loadCar);
    }

    @Test
    void cannotUnloadWhenMoving(){
        carTransport.loadCar();
        carTransport.startEngine();
        carTransport.gas(1.0);

        assertThrows(IllegalArgumentException.class, carTransport::unloadCar);
    }

    @Test 
    void canAccelPlatformDown(){
        carTransport.startEngine();
        carTransport.gas(1.0);
        
        assertTrue(carTransport.getCurrentSpeed() > 0);
    }

    @Test
    void cantAccelPlatformUp(){
        carTransport.raisePlatform(30);
        carTransport.startEngine();
        carTransport.gas(1.0);

        assertEquals(0, carTransport.getCurrentSpeed());
    }
}
