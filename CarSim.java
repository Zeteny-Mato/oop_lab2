public class CarSim {
    public static void main(String[] args) {
        // Instance of this class
       CarModel model = new CarModel();
       

        model.addCar(CarFactory.createCar("saab95",new Position(1,0)));
        model.addCar(CarFactory.createCar("scania",new Position(1,100)));
        model.addCar(CarFactory.createCar("volvo240",new Position(1,200)));

        CarController controller = new CarController(model); 
        CarView view = new CarView(controller); 
        model.addObs(view);
        controller.setView(view); controller.startTimer(); 
    }
}
 
 