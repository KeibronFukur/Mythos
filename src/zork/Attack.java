package zork;

public class Attack {
    private String description;
    private int maxDamage;

    public Attack(String description, int maxDamage) {
        this.description = description;
        this.maxDamage = maxDamage;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getMaxDamage() {
        return maxDamage;
    }
    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }
}
