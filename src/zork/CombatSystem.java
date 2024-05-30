package zork;

// write code for encountering an enemy 
public class CombatSystem {

    
public int PlayerHealth = 0; 
    private int x = 0;
    private int y = (int) (Math.random() * 100);
    private int EnemyHealth
    private static final String[] validCommands = { "Attack", "Parry","Stab" };

    /**
     * 
     */
    public void CombatSystem() {
        
    
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
            if (x == 1){
                // EnemyHealth =- Item.getDPSvalue(); 

            }
            
        } 
        
        if (aString.equals("Dodge") &&  Monster.getAttack() = 1 ) {
            x = 2;
            System.out.println("You dodged");
            if (aString.equals("Dodge") && Monster.getAttack() = 2){
               PlayerHealth =- Monster.getAttackValue();
            }
            else {
                System.out.println("Stop Mashing");
            }
        } 
        if(aString.equals("Stab")){
            x = 3;
            System.out.println("Stab");
            if (x == 3 && Monster.getAttack() = 2 ){}           

        }
         else {
            System.out.println("Invalid command try again dude");

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
        combatSystem.Combat("DarkSoulsRoll");
        
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

