import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WorkshopTest{
    private Workshop<Volvo240> workshop = new Workshop(6);

    @Test
    void testAddAndRetrieveCar() {
        Volvo240 car1 = new Volvo240();
        Volvo240 car2 = new Volvo240();
        workshop.addCar(car1);
        workshop.addCar(car2);
        assertEquals(car1, workshop.retrieveCar(0));
        assertEquals(car2, workshop.retrieveCar(0));
    }



}