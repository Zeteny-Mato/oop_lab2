import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();
    Workshop<Volvo240> volvoWorkshop = new Workshop<>(5);

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());
        //cc.cars.add(new Volvo240());

        cc.cars.get(0).y = 300;
        cc.cars.get(1).y = 100;
        cc.cars.get(2).y = 200;
        //cc.cars.get(3).y = 300; dubbelkollar att ifall man lägger till en till volvo att båda skickas till listan. 

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
       @Override
       public void actionPerformed(ActionEvent e) {

           ArrayList<Car> carsToRemove = new ArrayList<>();
           

           for (Car car : cars) {

               // 1. Flytta bilen
               car.move();

               // 2. Hämta position
               int x = (int) Math.round(car.getX());
               int y = (int) Math.round(car.getY());

               // 3. Väggkoll
               int panelWidth = frame.drawPanel.getWidth();
               int carWidth = 100; // bildbredd
               int maxX = panelWidth - carWidth;
               int maxy = panelWidth - carWidth;

               if (x < 0 || x > maxX || y<0 || y > maxy) {

                   // Stoppa
                   car.stopEngine();

                   // Vänd
                   car.turnLeft();
                   car.turnLeft();

                   // Start
                   car.startEngine();

                   // Klampa in i rutan
                   if (x < 0) x = 0;
                   if (x > maxX) x = maxX;
                   if (y<0) y = 0;
                   if (y>maxy) y = maxy;

                   // Uppdatera bilens position
                   car.x = x;
                   car.y = y;
               }

               // 4. Workshop volvo kan köra in. 
               if (car instanceof Volvo240 volvo) {

                   int wx = frame.drawPanel.volvoWorkshopPoint.x;
                   int wy = frame.drawPanel.volvoWorkshopPoint.y;

                   double dx = volvo.getX() - wx;
                   double dy = volvo.getY() - wy;
                   double dist = Math.sqrt(dx * dx + dy * dy);

                   if (dist < 50) {
                       volvoWorkshop.addCar(volvo);
                       carsToRemove.add(volvo);
                       System.out.println("Added a Volvo to workshop!"); 
                       System.out.println(volvoWorkshop.getCarsAmount()); //blir som ett id nummer typ. 
                       continue; // rita inte bilen
                   }

                   frame.drawPanel.moveVolvo(x, y);
               }

               else if (car instanceof Saab95) {
                  frame.drawPanel.moveSaab(x, y);
              }

               else if (car instanceof Scania) {
                   frame.drawPanel.moveScania(x, y);
               }
                // 5. Ta bort bilar som lastats i 
           }
           cars.removeAll(carsToRemove);

           // 6. Rita om
           frame.drawPanel.repaint();
        }
    }



    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
       for (Car car : cars
                ) {
            car.gas(gas);
        }
    }
    void brake(int amount){
        double brake = ((double) amount/100);
        for (Car car : cars) {
            car.brake(brake);
        }
    }
    void turboOn(){
        for (Car car: cars){
            if(car instanceof Saab95 saab){
                saab.setTurboOn();
            }
        }
    }
    void turboOff(){
        for (Car car : cars){
            if(car instanceof Saab95 saab){
                saab.setTurboOff();
            }
        }
    }
    void liftBed(){
        for (Car car : cars){
            if (car instanceof Scania scania){
                scania.raiseorlowerPlatform(10);
            }
        }
    }
    void lowerBed(){
        for (Car car : cars){
            if(car instanceof Scania scania){
                scania.raiseorlowerPlatform(-10);
            }
        }
    }
    void startAll(){
        for (Car car : cars) car.startEngine();
    }
    void stopAll(){
        for(Car car : cars) car.stopEngine();
    }
    
}
