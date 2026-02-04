import java.awt.*;
public class CarTransport extends Trucks{
    private int capacity;
    private int currentLoad;

    public CarTransport(int nrDoors, double enginePower, Color color, String modelName, int capacity) 
    {
        super(nrDoors, enginePower, color, modelName, 0);
        this.capacity = capacity;
        this.currentLoad = 0;
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
        currentLoad++;
    }

    public void unloadCar() {
        if (currentLoad == 0 || getCurrentSpeed() != 0) {
            throw new IllegalArgumentException("Cannot unload car: no cars loaded or truck is moving");
        }
        currentLoad--;
    }
    @Override
    protected double speedFactor() {
        if (getPlatformAngle() == 0) {
            return 0; // Ingen acceleration om plattformen är upphöjd
        }
        else { 
            return getEnginePower() * 0.01; // Justerar acceleration 
        }
    }
}
