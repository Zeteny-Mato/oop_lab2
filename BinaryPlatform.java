public class BinaryPlatform implements Platform {
    private boolean platformState;

    public BinaryPlatform(boolean startState) {
        this.platformState = startState;
    }

    @Override
    public boolean platformState() {
        return this.platformState;
    }

    @Override
    public void raise() {
        this.platformState = true;
    }
    
    @Override
    public void lower()
    {
        this.platformState = false;
    }
}
