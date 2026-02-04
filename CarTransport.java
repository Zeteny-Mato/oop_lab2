import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class CarTransport <T extends Car>extends Trucks{
    private int capacity;
    private int currentLoad;
    private List<T> loadedCars = new ArrayList<>();
    
//lista för bilar på flaket. 
    public CarTransport(int nrDoors, double enginePower, Color color, String modelName, int capacity, List<T> loadedCars) 
    {
        super(nrDoors, enginePower, color, modelName, 70);
        this.capacity = capacity;
        this.currentLoad = 0;
        this.loadedCars = loadedCars;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public void loadCar() {
        if (currentLoad >= capacity || getCurrentSpeed() != 0) {
            throw new IllegalArgumentException("Cannot load car: capacity exceeded or truck is moving");
        }
        loadedCars.add(loadedCars.size(), null); // Placeholder for the car being loaded
        currentLoad++;
    }

    public void unloadCar() {
        if (currentLoad == 0 || getCurrentSpeed() != 0) {
            throw new IllegalArgumentException("Cannot unload car: no cars loaded or truck is moving");
        }
        loadedCars.remove(loadedCars.size() - 1);
        currentLoad--;
    }
}
