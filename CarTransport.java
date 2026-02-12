import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class CarTransport <T extends Car>extends Trucks{
    private int capacity; //Max antal bilar
    public List<T> loadedCars = new ArrayList<>(); //Lista över alla lastade bilar
    private int unloadOffset = 0; //Håller koll på hur långt ifrån nästa lossad bil ska placeras 
    
//konstruktor
    public CarTransport(int capacity) 
    {
        //int nrDoors, double enginePower, Color color, String modelName
        super(2, 400, Color.BLUE, "CarTransport", 70); //anropar turcks konstruktorn
        this.capacity = capacity; //sätter maxkapacitet
        //this.loadedCars = new ArrayList<>(); //skapar en tom lista för lastade bilar
    }

    public int getCapacity() {
        return capacity; //Returnerar maxkapaciteten
    }

    public void loadCar(T car) {
        // rampen måste vara nere, transporten får inte vara full, bilen måste vara i närheten och det får inte lastas en annan transport
        if (getPlatformAngle() != 0) { //Ramp måste vara nere
            throw new IllegalArgumentException("Ramp must be down to load cars");
        }
        if (loadedCars.size() >= capacity || getCurrentSpeed() != 0) {
            throw new IllegalArgumentException("Transport is full or truck is moving");
        }
        else if (!isCarInRange(car)) {
            throw new IllegalArgumentException("Car is out of range");
        }
        else if (car instanceof CarTransport) {
            throw new IllegalArgumentException("Cannot load another car transport");
        }
        loadedCars.add(car); //Lägger på bilen på flak
        car.stopEngine(); //Stänger av motorn på bilen
        car.x = this.getX(); //Säter bilens position till lastbilens position
        car.y = this.getY();
        unloadOffset = 0; //Nollställer offset när ny bil lastas
    }

    public T unloadCar() {
        if (getPlatformAngle() != 0) { //Ramp måste vara nere
            throw new IllegalArgumentException("Ramp must be down to unload cars");
        }
        if (loadedCars.size() == 0 || getCurrentSpeed() != 0) {
            throw new IllegalArgumentException("No cars loaded or truck is moving");
        }
        T car = loadedCars.remove(loadedCars.size() - 1); //Tar bort sista bilen, first in i transporten är sist ut
        car.x = this.x + 1 + unloadOffset; //Placerar bilen bredvid lastbilen när den lastas av med offset
        car.y = this.y;
        unloadOffset += 1; //Nästa bil placeras ett steg längre bort
        return car;
    }
//Kollar om bilen är nära nog för att lastas
    private boolean isCarInRange(Car car) {
        double max_Distance = 10.0;  //Max avstånd för att kunna lasta bilen
        double dx = car.getX() - this.getX(); //Beräknar avståndet i x-led
        double dy = car.getY() - this.getY(); //Beräknar avståndet i y-led
        double distance = Math.sqrt(dx * dx + dy * dy); //Beräknar det totala avståndet
        return distance <= max_Distance; //Returnerar true om bilen är inom räckvidd, annars false
    }
    public void movewithloadedCars(){ //Flyttar med alla lastade bilar
        for (T car : loadedCars) {
            car.x = this.getX();
            car.y = this.getY();
        }
    }
    public boolean GetRampPosition()
    {
        if(getPlatformAngle() == 0)
        {
            return false; //nere
        }
        return true; //uppe
    }
    @Override //Får inte köra när rampen är nere
    public void gas(double amount) {
        if (GetRampPosition() == false) return;
        super.gas(amount); //annars får köra
    }
    @Override //Ser till att lastade bilar följer med
    public void move() {
        super.move(); //flyttar lastbilen
        movewithloadedCars(); //flyttar alla lastade bilar med lastbilen
    }
}
