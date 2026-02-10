import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WorkshopTest{
    private Workshop<Volvo240> workshopvolvo = new Workshop(6);
    private Workshop<Car> workshopcar = new Workshop(6);

    @Test
    void testAddAndRetrieveCar() {
        Volvo240 car1 = new Volvo240();
        Volvo240 car2 = new Volvo240();
        workshopvolvo.addCar(car1);
        workshopvolvo.addCar(car2);
        assertEquals(car1, workshopvolvo.retrieveCar(0));
        assertEquals(car2, workshopvolvo.retrieveCar(0));

        Car car3 = new Saab95();
        Car car4 = new Scania();
        workshopcar.addCar(car3);
        workshopcar.addCar(car4);
        assertEquals(car3, workshopcar.retrieveCar(0));
        assertEquals(car4, workshopcar.retrieveCar(0));



    }

}