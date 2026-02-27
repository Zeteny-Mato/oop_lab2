public class Position {
    private double x;
    private double y;

    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() { return x;}
    public double getY() { return y;}

    public void setX(double x) { this.x = x;}
    public void setY(double y) { this.y = y;}
    
    //Calculate  distance between two Positions
    public double distanceTo(Position other) {
        double dx = this.getX() - other.getX(); //Beräknar avståndet i x-led
        double dy = this.getY() - other.getY(); //Beräknar avståndet i y-led
        return Math.sqrt(dx * dx + dy * dy);
    }

    //Clamps an object into the borders specified
    public void clamp(double maxX, double minX, double maxY, double minY) {
        x = Math.max(minX,Math.min(maxX,x));
        y = Math.max(minY,Math.min(maxY,y));
    }
}
