package zork;


import java.util.ArrayList;



public class CombatSystem {

    


    public int PlayerHealth = 40; 
    private int x = 0;
    private int y = (int) (Math.random() * 100);
    private int EnemyHealth = 100;
    private Object Monster ;
    private static final String[] validCommands = { "Attack", "Parry","Stab" };

    /**
     * 
     */
    public boolean isCommand(String aString) {
        for (String c : validCommands) {
            if (c.equals(aString)) {
                return true;
            }
        }
        return false;
    }
    public void CombatSystem() {
        
    
    


    public void Combat(String aString) {
        if (aString.equals("Attack")) {
            x = 1;

            if (y >= 75) {
                x = 0;
                System.out.println("Open your eyes"); 
                System.out.println("You missed");
            }
            if (x == 1 && this.Monster.getAttacks() == 2){
                EnemyHealth =- Item.getDPSvalue(); 
                 System.out.println("It took damage");

            }
            if(x == 1 && this.Monster.getAttacks() == 1){

                System.out.println("Clashed");
            }
            if (x == 1 && this.Monster.getAttacks() == 3){
                PlayerHealth =- this.Monster.getAttacks();
                System.out.println("You took damage");
            }
        } 
        
        if (aString.equals("Dodge") &&  this.Monster.getAttacks() == 1 ) {
            x = 2;
            System.out.println("You dodged");
            if (aString.equals("Dodge") && this.Monster.getAttacks() == 2){
               PlayerHealth =- this.Attack.getMaxDamage();
            }
            else {
                System.out.println();
            }
        } 
        if(aString.equals("Stab")){
            x = 3;
          if (y >= 25){
            x = 0;
            System.out.println("You missed and took recoil");
            PlayerHealth =-30;
          }
            if (x == 3 && this.Monster.getAttacks() == 1 || 2 || 3){
                System.out.println("Stab"); 
                EnemyHealth =- Item.getDPSvalue()*2;
            }           

        }
         else {
            System.out.println("Invalid command try again dude");

        }
        
    }
    


    }

}




public void main(String[] args) {
    CombatSystem combatSystem = new CombatSystem();

    combatSystem.Combat("Attack");
    combatSystem.Combat("Grab");
    combatSystem.Combat("Stab");
    
    if(this.Room.hasMonsters() == true){
        System.out.println("Use commands Attack,Parry and Stab")
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

