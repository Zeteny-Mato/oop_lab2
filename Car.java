import java.awt.*;  
// Importerar Color-klassen så vi kan använda färger på bilarna.

public abstract class Car<E extends Engine> implements Movable {
    //  Fält (privata för inkapsling) 
    private Position position;
    private E engine;
    private int nrDoors;          // Antal dörrar på bilen
    private double currentSpeed;  // Bilens nuvarande hastighet
    private Color color;          // Bilens färg
    private String modelName;     // Modellnamn

    // Riktning: 0 = Norr, 1 = Öst, 2 = Söder, 3 = Väst
    protected int direction = 1;

    //  Konstruktor (protected → bara subklasser får använda den) 
    protected Car(E engine, Position position, int nrDoors, Color color, String modelName) {
        this.engine = engine;             // Sätter motortyp
        this.position  = position;        // sätter position
        this.nrDoors = nrDoors;           // Sätter antal dörrar
        this.color = color;               // Sätter färgen
        this.modelName = modelName;       // Sätter modellnamnet
        engine.stopEngine();              // Alla bilar startar med avstängd motor
    }

    //  Publika getters (användaren får läsa men inte ändra) 
    public E getEngine() {           // Returnerar motortyp
        return engine;
    }

    public Position getPosition(){        // Returnerar positionen
        return position;
    }

    public int getNrDoors() {
        return nrDoors;                   // Returnerar antal dörrar
    }

    public double getCurrentSpeed() {
        return currentSpeed;              // Returnerar nuvarande hastighet
    }

    public Color getColor() {
        return color;                     // Returnerar bilens färg
    }

    public String getModelName() {
        return modelName;                 // Returnerar modellnamnet
    }

    public int getDirection() {
        return direction;                 // Returnerar bilens riktning
    }

    protected void setColor(Color clr) {
        color = clr;                      // Tillåter användaren att ändra färg
    }

    //  Motorhantering 

    public void startEngine() {
        engine.startEngine();
        if(engine.isRunning()) { currentSpeed = 0.1; }  // Startar motorn med en liten fart
    }

    public void stopEngine() {
        engine.stopEngine();
        if(!engine.isRunning()) { currentSpeed = 0;  }  // Stänger av motorn helt
    }

    //  Gas och broms 
    public void gas(double amount) {
        // Säkerhetskontroll: amount måste vara mellan 0 och 1
        if (currentSpeed == 0) return;
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Amount must be between 0 and 1");
        }
        incrementSpeed(amount);           // Ökar hastigheten
    }

    public void brake(double amount) {
        // Samma säkerhetskontroll som gas
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Amount must be between 0 and 1");
        }
        decrementSpeed(amount);         // Minskar hastigheten
    }


    // Skyddade metoder för hastighetsändring 

    protected void incrementSpeed(double amount) {
        // Ökar hastigheten men aldrig över enginePower
        currentSpeed = Math.min(getCurrentSpeed() + engine.getSpeedFactor() * amount, engine.getEnginePower());
    }

    protected void decrementSpeed(double amount) {
        // Minskar hastigheten men aldrig under 0
        currentSpeed = Math.max(getCurrentSpeed() - engine.getSpeedFactor() * amount, 0);
    }

    @Override
    public void move() {
        switch (direction) {
            case 0 -> position.setY(position.getY() + currentSpeed); // Norr
            case 1 -> position.setX(position.getX() + currentSpeed); // Öst
            case 2 -> position.setY(position.getY() - currentSpeed); // Söder
            case 3 -> position.setX(position.getX() - currentSpeed); // Väst
        }
    }

    @Override
    public void turnLeft() {
        direction = (direction + 3) % 4; // 0→3, 1→0, 2→1, 3→2
    }

    @Override
    public void turnRight() {
        direction = (direction + 1) % 4; // 0→1, 1→2, 2→3, 3→0
        
    }
}
