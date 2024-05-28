package zork;

// write code for encountering an enemy
public class CombatSystem {

    private int x = 0;
    private static final String[] validCommands = { "Attack", "Parry", "Grab","DarkSoulsRoll" };

    public CombatSystem() {
        
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
            if (Y >= 75) {
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
        if(aString.equals("DarkSoulsRoll")){
            x = 4;
            System.out.println("Dark Souls");
            x = 4;

        }
        } else {
            System.out.println("Invalid command try again idiot");
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
        combatSystem.Combat("Grab");
        combatSystem.Combat("Parry");
        }

}




// if (EA = AttackFiller && x = 1 && Y >= 75)
// System.out.println("You clashed")
//if (EA = 1 && x = 2){
//System.out.println("You parried")
//
//int Z = item.getDPSvalue(); 
// EnemyHealth =- Z; 
//}
//if (EA = 1 && x = 3){
//int PH = 1; 
//
//
//
//}
//
//
//