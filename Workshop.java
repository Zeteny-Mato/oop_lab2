import java.util.ArrayList;
import java.util.List;

public class Workshop<T extends Car> {
    private int capacity;
    private List<T> cars_amount = new ArrayList<>();

    public Workshop(int capacity) {
        this.capacity = capacity;
    }

    public void addCar(T car) {
        if (cars_amount.size() >= capacity) {
            throw new IllegalStateException("Workshop is full");
        }
        cars_amount.add(car);
    }

    public T retrieveCar(int index) {
        if (index < 0 || index >= cars_amount.size()) {
            throw new IndexOutOfBoundsException("No car at the given index");
        }
        return cars_amount.remove(index);
    }
    
}
