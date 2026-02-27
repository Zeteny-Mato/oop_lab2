public abstract class Trucks<E extends Engine, Pl extends Platform> extends Car<E> {
    private Pl platform;

    public Trucks(E engine, Position position, Pl platform, int nrDoors, java.awt.Color color, String modelName) {
        super(engine,position,nrDoors, color, modelName);
        this.platform = platform;
    }

    public Pl getPlatform(){
        return platform;
    }

    private void speedCheck() {
        if (getCurrentSpeed() != 0 )
            throw new IllegalStateException("Speed must be 0 to raise the platform");
    }

    public void raisePlatform() {
        speedCheck();
        platform.raise();      
    }

    public void lowerPlatform(){
        speedCheck();
        platform.lower();
    }


    @Override
    public void gas(double amount) {
        if (platform.platformState() == false) {
            throw new IllegalStateException("Cannot accelerate with platform down");
        }
        super.gas(amount);
    }

    @Override 
    public void startEngine()
    {
        if (platform.platformState() == false) {
            throw new IllegalStateException("Cannot start with platform down");
        }
        super.startEngine();
    }    
}