public class CombustionEngine implements Engine{
    private final double trimFactor = 1.25;
    private double enginePower;
    private boolean running = false;
    
    public CombustionEngine(double enginePower) {
        this.enginePower = enginePower;
    }

    @Override
    public double getEnginePower() {
        return enginePower;
    }

    @Override
    public double getSpeedFactor() {
        return enginePower * 0.01 * trimFactor;
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
}
