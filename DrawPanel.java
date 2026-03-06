
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.net.URL;
import javax.imageio.ImageIO;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    private final CarModel model;

    // Bilder för biltyper 
    private BufferedImage volvoImage;
    private BufferedImage saabImage;
    private BufferedImage scaniaImage;
    private BufferedImage bgImage;
    // To keep track of a single car's position
    private BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300, 300);

    // Initializes the panel and reads the images
    public DrawPanel(CarModel model, int width, int height) {
        this.model = model;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(width, height));
        //this.setBackground(Color.green);
        try {
            URL url = new URL("https://st.depositphotos.com/1005951/1847/i/950/depositphotos_18472597-stock-photo-kebab-shop.jpg");
            bgImage = ImageIO.read(url);}
            catch(IOException e){
                e.printStackTrace();
            }
        



        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    private BufferedImage imageFor(Car<?> car){
        if (car instanceof Volvo240) return volvoImage;
        if(car instanceof Saab95) return saabImage;
        if(car instanceof Scania) return scaniaImage;
        throw new IllegalArgumentException("Unknow car type");
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
         if (bgImage != null){
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);
        }
        if (volvoWorkshopImage != null){
            g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
        }
        for (Car<?> car : model.getCars()){
            BufferedImage carImage = imageFor(car);
            int x = (int) car.getPosition().getX();
            int y = (int) car.getPosition().getY();
            g.drawImage(carImage, x, y, null);
        }
    }
}
