public class CarFactory {

    //TODO: make work with Cartypes which have their own constructor parameters, like startAngle for CarTransport
    //Alternatively remove StartAngle from CarTransport and set the start Angle to always be a certain angle <- a lot easier
    public static Car<?> createCar(String type, Position pos){
        return switch(type.toLowerCase()){
            case "volvo240" -> new Volvo240(pos);
            case "saab95" -> new Saab95(pos);
            case "scania" -> new Scania(pos);
            default -> throw new IllegalArgumentException("Not a valid Car type");
        };
    }

}
