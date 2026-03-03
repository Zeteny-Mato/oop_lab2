import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController implements ActionListener{
    private final CarModel model;
    private final CarView view;

    private final int delay = 50;
    private Timer timer;
    public CarController(CarModel model, CarView view) {
        this.model = model;
        this.view = view;
        this.timer = new Timer(delay, this);
    }

    public void startTimer() {
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        model.update();
        view.repaint();
    }

    void gas(int Amount){
        model.gas(Amount / 100);
    }

    void brake(int Amount){
        model.brake(Amount / 100);
     }

    void turboOn(){ model.turboOn();}
    void turboOff(){ model.turboOff();}
    void liftBed(){ model.liftBed();}
    void lowerBed(){ model.lowerBed();}
    void startAll(){ model.startAll();}
    void stopAll(){ model.stopAll();}
}
