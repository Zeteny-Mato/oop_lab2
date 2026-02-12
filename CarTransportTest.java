import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;

public class CarTransportTest {
    private CarTransport<Car> carTransport = new CarTransport(5);
    private Volvo240 volvo = new Volvo240();

    @Test
    void getCapacity(){
        assertEquals(5, carTransport.getCapacity());
    }

    @Test
    void getCurrentLoad(){ //init check
        assertEquals(0, carTransport.loadedCars.size());
    }

    @Test
    void loadandunloadCar(){
 
        carTransport.loadCar(volvo);
        assertEquals(1, carTransport.loadedCars.size());
        carTransport.unloadCar();
        assertEquals(0, carTransport.loadedCars.size());
    }


    @Test
    void CannotUnloadWhenEmpty(){
        assertThrows(IllegalArgumentException.class, carTransport::unloadCar);
    }

    @Test
    void cannotLoadWhenMoving(){
        ;
        carTransport.startEngine();
        carTransport.gas(1.0);

        assertThrows(IllegalArgumentException.class, () -> carTransport.loadCar(volvo));
    }

    @Test
    void cannotUnloadWhenMoving(){
        carTransport.loadCar(volvo);
        carTransport.startEngine();
        carTransport.gas(1.0);

        assertThrows(IllegalArgumentException.class, carTransport::unloadCar);
    }

    @Test 
    void cantAccelPlatformup(){
        carTransport.startEngine();
        carTransport.gas(1.0);
        
        assertEquals(0, carTransport.getCurrentSpeed());
    }

    @Test
    void cantAccelPlatformDown(){
        carTransport.raiseorlowerPlatform(-30);
        carTransport.startEngine();
        carTransport.gas(1.0);

        
        assertTrue(carTransport.getCurrentSpeed() > 0);
    }
}
