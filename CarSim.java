public class CarSim {
    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(CarFactory.createCar("saab95",new Position(1,100)));
        cc.cars.add(CarFactory.createCar("scania",new Position(1,200)));
        cc.cars.add(CarFactory.createCar("volvo240",new Position(1,300)));

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);
        for (Car<?> car : cc.cars){
            cc.frame.drawPanel.addCar(car);
        }

        // Start the timer
        cc.startTimer();
    }
}
 
 