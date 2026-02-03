
public class CarTransport extends Trucks{
    private int capacity;
    private int currentLoad;

    public CarTransport(int nrDoors, double enginePower, java.awt.Color color, String modelName, int capacity) 
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
        if (currentLoad < capacity && getCurrentSpeed() == 0) {
            currentLoad++;
        }
    }

    public void unloadCar() {
        if (currentLoad > 0 && getCurrentSpeed() == 0) {
            currentLoad--;
        }
    }
    @Override protected double speedFactor() {
       { 
        return getEnginePower() * 0.01; // sitter där så länge
       }
    }
}
