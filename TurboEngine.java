public class TurboEngine implements Engine{

    private final double enginePower;
    private boolean running = false;
    private boolean turboOn = false;
    private double turbo; 
    
    public TurboEngine(double enginePower) {
        this.enginePower = enginePower;
    }

    @Override
    public double getEnginePower() {
        return enginePower;
    }

    @Override
    public double getSpeedFactor() {
        turbo = turboOn ? 1.3 : 1.0;
        return enginePower * turbo *0.01;
    }

    @Override
    public void startEngine() {
        running = true;  
    }

    @Override
    public void stopEngine() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    public void setTurbo(boolean state) {
        this.turboOn = state;
    }
}
