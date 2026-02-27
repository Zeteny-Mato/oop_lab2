public interface Engine {
    double getEnginePower();
    double getSpeedFactor();
    void startEngine();
    void stopEngine();
    boolean isRunning();
}
