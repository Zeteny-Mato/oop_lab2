import java.util.ArrayList;
import java.util.List;

public class Workshop<T extends Car> {
    private int capacity; //Max antal bilar som kan vara i verkstaden
    private List<T> cars_amount = new ArrayList<>(); //Lista över alla bilar i verkstaden

    public Workshop(int capacity) {
        this.capacity = capacity; //Sätter maxkapaciteten när verkstaden skapas
    }

    public void addCar(T car) {
        //Om Verkstaden är full -> kasta ett undantag
        if (cars_amount.size() >= capacity) {
            throw new IllegalStateException("Workshop is full");
        } 
        //Annars läggs bill till i listan
        cars_amount.add(car);
    }

    public T retrieveCar(int index) {
        if (index < 0 || index >= cars_amount.size()) { //Kollar att indexet är giltigt
            throw new IndexOutOfBoundsException("No car at the given index");
        }
        //Tar bort och returnerar bilen på det angivna indexet
        return cars_amount.remove(index);
    }
    
}
