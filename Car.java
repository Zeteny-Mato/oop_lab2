import java.awt.*;  
// Importerar Color-klassen så vi kan använda färger på bilarna.

public abstract class Car implements Movable {
    //  Fält (privata för inkapsling) 

    private int nrDoors;          // Antal dörrar på bilen
    private double enginePower;   // Motorns maxeffekt
    private double currentSpeed;  // Bilens nuvarande hastighet
    private Color color;          // Bilens färg
    private String modelName;     // Modellnamn

    // Position
    protected double x = 0;
    protected double y = 0;

    // Riktning: 0 = Norr, 1 = Öst, 2 = Söder, 3 = Väst
    protected int direction = 1;

    //  Konstruktor (protected → bara subklasser får använda den) 
    protected Car(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;           // Sätter antal dörrar
        this.enginePower = enginePower;   // Sätter motoreffekten
        this.color = color;               // Sätter färgen
        this.modelName = modelName;       // Sätter modellnamnet
        stopEngine();                     // Alla bilar startar med avstängd motor
    }

    //  Publika getters (användaren får läsa men inte ändra) 

    public int getNrDoors() {
        return nrDoors;                   // Returnerar antal dörrar
    }

    public double getEnginePower() {
        return enginePower;               // Returnerar motoreffekten
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

    public double getY() {
        return y;                         // Returnerar bilens Y-position
    }

    public double getX() {
        return x;                         // Returnerar bilens X-position
    }

    protected void setColor(Color clr) {
        color = clr;                      // Tillåter användaren att ändra färg
    }

    //  Motorhantering 

    public void startEngine() {
        currentSpeed = 0.1;               // Startar motorn med en liten fart
    }

    public void stopEngine() {
        currentSpeed = 0;                 // Stänger av motorn helt
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

    // Abstrakt metod som subklasser måste implementera 

    protected abstract double speedFactor();
    // Varje biltyp har sin egen acceleration → därför abstract.

    // Skyddade metoder för hastighetsändring 

    protected void incrementSpeed(double amount) {
        // Ökar hastigheten men aldrig över enginePower
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    protected void decrementSpeed(double amount) {
        // Minskar hastigheten men aldrig under 0
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    @Override
    public void move() {
        switch (direction) {
            case 0 -> y += currentSpeed; // Norr
            case 1 -> x += currentSpeed; // Öst
            case 2 -> y -= currentSpeed; // Söder
            case 3 -> x -= currentSpeed; // Väst
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
