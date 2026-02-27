import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WorkshopTest{
    private Workshop<Volvo240> workshopvolvo = new Workshop(6);
    private Workshop<Car> workshopcar = new Workshop(6);

    @Test
    void testAddAndRetrieveCar() {
        Volvo240 car1 = new Volvo240(new Position(0,100));
        Volvo240 car2 = new Volvo240(new Position(100,100));
        workshopvolvo.addCar(car1);
        workshopvolvo.addCar(car2);
        //workshopvolvo.addCar(new Saab95());


        assertEquals(car1, workshopvolvo.retrieveCar(car1));
        //assertEquals(car2, workshopvolvo.retrieveCar());

        Car car3 = new Saab95(new Position(0,200));
        Car car4 = new Scania(new Position(200,100));
        workshopcar.addCar(car3);
        workshopcar.addCar(car4);
       // assertEquals(car3, workshopcar.retrieveCar(0));
        //assertEquals(car4, workshopcar.retrieveCar(0));



    }

}