import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class CarTransport <T extends Car>extends Trucks{
    private int capacity;
    private List<T> loadedCars;
    
//lista för bilar på flaket. 
    public CarTransport(int nrDoors, double enginePower, Color color, String modelName, int capacity, List<T> loadedCars) 
    {
        super(nrDoors, enginePower, color, modelName, 70);
        this.capacity = capacity;
        this.loadedCars = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public void loadCar(T car) {
        if (loadedCars.size() >= capacity || getCurrentSpeed() != 0) {
            throw new IllegalArgumentException("Transport is full or truck is moving");
        }
        else if (!isCarInRange(car)) {
            throw new IllegalArgumentException("car is out of range");
        }
        else if (car instanceof CarTransport) {
            throw new IllegalArgumentException("Cannot load another car transport");
        }
        loadedCars.add(car); //Lägger på bilen på flak
        car.stopEngine(); //Stänger av motorn på bilen
        car.x = this.getX(); //Säter bilens position till lastbilens position
        car.y = this.getY();
    }

    public T unloadCar() {
        if (loadedCars.size() == 0 || getCurrentSpeed() != 0) {
            throw new IllegalArgumentException("No cars loaded or truck is moving");
        }
        T car = loadedCars.remove(loadedCars.size() - 1);
        car.x = this.x + 2; //Bilens position är brevid lastbilen. 
        car.y = this.y;
        return car;
    }
//Kollar om bilen är nära nog för att lastas
    private boolean isCarInRange(Car car) {
        double max_Distance = 10.0;
        double dx = car.getX() - this.getX();
        double dy = car.getY() - this.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance <= max_Distance;
    }
    public void movewithloadedCars(){
        
    }
}
