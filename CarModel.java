
import java.util.ArrayList;
import java.util.List;

public class CarModel {
    private final List<Car<?>> cars = new ArrayList<>();
    private final Workshop<Volvo240> volvoWorkshop = new Workshop<>(5);

    private int worldWidth = 800;
    private int worldHeight = 560;

    public void setWorldSize(int w, int h){
        this.worldWidth = w;
        this.worldHeight = h;
    }
    public void addCar(Car<?> car){
        cars.add(car);
    }
    public List<Car<?>> getCars(){
        return List.copyOf(cars);
    }
    public void gas(double amount) {
        for (Car<?> c : cars) c.gas(amount);
    }
    public void brake(double amount){
        for (Car<?> c : cars) c.brake(amount);
    }
    public void turboOn(){
        for (Car<?> c : cars) 
            if (c instanceof Saab95 saab) saab.setTurboOn();
    }
    public void turboOff(){
        for(Car<?> c : cars)
            if (c instanceof Saab95 saab) saab.setTurboOff();    
    }
    public void liftBed(){
        for (Car<?> c : cars)
            if (c instanceof Scania scania) scania.getPlatform().raise();
    }
    public void lowerBed(){
        for( Car<?> c : cars){
            if (c instanceof Scania scania) scania.getPlatform().lower();
        }
    }
    public void startAll(){
        for (Car<?> c : cars) c.startEngine();
    }
    public void stopAll(){
        for (Car<?> c : cars) c.stopEngine();
    }
    public void update(){
        List<Car<?>> toRemove = new ArrayList<>();
        
        for (Car<?> c : cars){
            c.move();

            int x =(int) c.getPosition().getX();
            int y =(int) c.getPosition().getY();

            if (x< 0 || x > worldWidth-100 || y<0 || y> worldHeight-100){
                //c.stopEngine();
                c.turnLeft();
                c.turnLeft();
                //c.startEngine();
            }
            if ( c instanceof Volvo240 volvo){
                double dx = volvo.getPosition().getX() -300;
                double dy = volvo.getPosition().getY() -300;
                double dist = Math.sqrt(dx*dx + dy*dy);

                if (dist < 50){
                    volvoWorkshop.addCar(volvo);
                    toRemove.add(volvo);
                }
            }
        }
        cars.removeAll(toRemove);
    }
    public Car<?> getLastCar(){
        if(cars.isEmpty()) return null;
        return cars.get(cars.size()-1);
    }
    public Car<?> removeLastCar(){
        if (cars.isEmpty()) return null;
        return cars.remove(cars.size() -1);
    }
    public void addCar(String typeInput){
        if(cars.size()>= 5) return;
        int y =cars.size() * 100;
        Position p = new Position(1,y);
        Car<?> c;

        try {
            c = CarFactory.createCar(typeInput.toLowerCase(),p);
        }
        catch (Exception e){
            List<String> types = CarFactory.getSupportedTypes();
            int i = (int)(Math.random()* types.size());
            c = CarFactory.createCar(types.get(i),p);
        }
        cars.add(c);
    }
}
