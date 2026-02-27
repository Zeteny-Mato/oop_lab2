public class CarFactory {

    public static Car<?> createCar(String type, Position pos){
        return switch(type.toLowerCase()){
            case "volvo240" -> new Volvo240(pos);
            case "saab95" -> new Saab95(pos);
            case "scania" -> new Scania(pos);
            default -> throw new IllegalArgumentException("Not a valid Car type");
        };
    }

}
