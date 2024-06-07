package zork;

import java.util.ArrayList;

public class Monster extends Room{
    private ArrayList<Attack> attacks;
    private String name;
    private String description;
    private int health;
   
   

    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Monster(){
        attacks = new ArrayList<Attack>();
    }
    public ArrayList<Attack> getAttacks() {
        return attacks;
    }
    public void setAttacks(ArrayList<Attack> attacks) {
        this.attacks = attacks;
    }
    public void setDescription(String monsterDescription) {
        description = monsterDescription;
    }
    public void setHealth(){
         health = 100;
    }
    public int getHealth(){
        return health;
    }
}
