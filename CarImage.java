import java.awt.image.BufferedImage;

public class CarImage {
    private final Car<?> car;
    private final BufferedImage image;

    public CarImage (Car<?> car, BufferedImage image){
        this.car = car;
        this.image = image;
    }
    public Car<?> getCar() {
        return car;
    }
    public BufferedImage getImage(){
        return image;
    }
}