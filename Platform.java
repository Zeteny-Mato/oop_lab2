public interface Platform {
    boolean platformState();
    void raise();
    void lower();
}
