
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class CarView extends JFrame implements ModelObserver{

    private final DrawPanel drawPanel;
    private final int X = 800;
    private final int Y = 800;
    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo On");
    JButton turboOffButton = new JButton("Saab Turbo Off");
    JButton liftBedButton = new JButton("Scania lift bed");
    JButton lowerBedButton = new JButton("Scania lower bed");
    JButton addCarButton = new JButton("Add car");
    JButton removeCarButton = new JButton("Remove car");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    JTextField carType = new JTextField(20);
    

    private final JSpinner gasSpinner;

    public CarView(CarController controller) {

        setTitle("CarSim");
        setPreferredSize(new Dimension(X,Y));
        setLayout(new FlowLayout(FlowLayout.LEFT,  0, 0));

        drawPanel = new DrawPanel(controller.getModel(),X, Y-240);
        add(drawPanel);

       // for (Car<?> c : controller.getModel().getCars()){
          //  drawPanel.addCar(c);
       // }

        SpinnerModel model = new SpinnerNumberModel(
            0,
            0,
            100,
            1);
        gasSpinner = new JSpinner(model);

        JPanel gasPanel = new JPanel();
        gasPanel.add(new JLabel("Gas amount"));
        gasPanel.add(gasSpinner);
        add(gasPanel);
        
        JPanel controls = new JPanel(new GridLayout(2,4));

        controls.add(gasButton, 0);
        controls.add(brakeButton, 1);
        controls.add(turboOnButton, 2);
        controls.add(turboOffButton, 3);
        controls.add(liftBedButton, 4);
        controls.add(lowerBedButton, 5);
        controls.add(addCarButton, 6);
        controls.add(removeCarButton, 7);
        controls.add(carType, 8);

        add(controls);
        add(startButton);
        add(stopButton);

        gasButton.addActionListener(e -> controller.gas((int) gasSpinner.getValue()));
        brakeButton.addActionListener(e -> controller.brake((int) gasSpinner.getValue()));
        turboOnButton.addActionListener(e -> controller.turboOn());
        turboOffButton.addActionListener(e -> controller.turboOff());
        liftBedButton.addActionListener(e -> controller.liftBed());
        lowerBedButton.addActionListener(e -> controller.lowerBed());
        startButton.addActionListener(e -> controller.startAll());
        stopButton.addActionListener(e -> controller.stopAll());
        addCarButton.addActionListener(e -> controller.addCar(carType.getText()));
        removeCarButton.addActionListener(e -> controller.removeCar());
        

        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public DrawPanel getDrawPanel(){
        return drawPanel;
    }
    @Override //När modellen ändras, rita om. 
    public void modelUpdated(){
        repaint();
    }
}