
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController{
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car<?>> cars = new ArrayList<>();
    Workshop<Volvo240> volvoWorkshop = new Workshop<>(5);

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240(new Position(1,300)));
        cc.cars.add(new Saab95(new Position(1,100)));
        cc.cars.add(new Scania(new Position(1,200)));

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

           ArrayList<Car<?>> carsToRemove = new ArrayList<>();
           
           //This now works well
           for ( Car<?> car : cars) {

               // Flytta bilen
               car.move();

               int x = (int)car.getPosition().getX(); 
               int y = (int)car.getPosition().getY();
               int panelWidth = frame.drawPanel.getWidth();
               int carWidth = 100; // bildbredd
               int maxX = panelWidth - carWidth;
               int maxY = panelWidth - carWidth;

               car.getPosition().clamp(maxX,0,maxY,0);

               Position p = car.getPosition();
               if(p.getX() == 0 || p.getX() == maxX || p.getY() == 0 || p.getY() == maxY)
               {
                    car.stopEngine();
                    car.turnLeft();
                    car.turnLeft();
                    car.startEngine();
               }

               // Workshop volvo kan köra in. //change to maybe a list  of workshops and see if the car matches the workshop? 
               if (car instanceof Volvo240 volvo) {

                   int vwx = frame.drawPanel.volvoWorkshopPoint.x;
                   int vwy = frame.drawPanel.volvoWorkshopPoint.y;

                   double dx = volvo.getPosition().getX() - vwx;
                   double dy = volvo.getPosition().getY() - vwy;
                   double dist = Math.sqrt(dx * dx + dy * dy);

                   if (dist < 50) {
                       volvoWorkshop.addCar(volvo);
                       carsToRemove.add(volvo);
                       System.out.println("Added a Volvo to workshop"); 
                       System.out.println(volvoWorkshop.getCarsAmount()); //blir som ett id nummer typ. 
                       continue; // rita inte bilen
                   }
                   else {
                    frame.drawPanel.moveVolvo(x, y);
                   }  
               }

               else if (car instanceof Saab95) {
                  frame.drawPanel.moveSaab(x, y);
              }

               else if (car instanceof Scania) {
                   frame.drawPanel.moveScania(x, y);
               }
                // Ta bort bilar som lastats i 
           }
           cars.removeAll(carsToRemove);

           // repaint() calls the paintComponent method of the panel
           frame.drawPanel.repaint();
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
       for (Car<?> car : cars
                ) {
            car.gas(gas);
        }
    }
    void brake(int amount){
        double brake = ((double) amount/100);
        for (Car<?> car : cars) {
            car.brake(brake);
        }
    }
    void turboOn(){
        for (Car<?> car: cars){
            if(car instanceof Saab95 saab){
                saab.setTurboOn();
            }
        }
    }
    void turboOff(){
        for (Car<?> car : cars){
            if(car instanceof Saab95 saab){
                saab.setTurboOff();
            }
        }
    }
    void liftBed(){
        for (Car<?> car : cars){
            if (car instanceof Scania scania){
                scania.getPlatform().raise();
            }
        }
    }
    void lowerBed(){
        for (Car<?> car : cars){
            if(car instanceof Scania scania){
                scania.getPlatform().lower();
            }
        }
    }
    void startAll(){
        for (Car<?> car : cars) car.startEngine();
    }
    void stopAll(){
        for(Car<?> car : cars) car.stopEngine();
    }
    
}
