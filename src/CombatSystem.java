
public class CombatSystem {

    private int x = 0;
    private static final String[] validCommands = { "Attack", "Parry", "Grab" };

    public CombatSystem() {
        // Constructor
    }

    public boolean isCommand(String aString) {
        for (String c : validCommands) {
            if (c.equals(aString)) {
                return true;
            }
        }
        return false;
    }

    public void Combat(String aString) {
        if (aString.equals("Attack")) {
            x = 1;
            int Y = (int) (Math.random() * 100);
            if (Y <= 75) {
                x = 0;
                System.out.println("Open your eyes");
                System.out.println("You missed");
            }
            
        } 
        
        else if (aString.equals("Parry")) {
            x = 2;
            System.out.println("You parried");
        } else if (aString.equals("Grab")) {
            x = 3;
            System.out.println("You grabbed");
        } else {
            System.out.println("Invalid command");
        }
    }

    public void showAll() {
        for (String c : validCommands) {
            System.out.print(c + "  ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CombatSystem combatSystem = new CombatSystem();
        combatSystem.showAll();
        combatSystem.Combat("Attack");
    }
}
//item.getDPSvalue(); 
//
