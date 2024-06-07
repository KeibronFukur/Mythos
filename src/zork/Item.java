package zork;

public class Item extends OpenableObject {
  private int weight;
  private String itemName;
  private String description;
  private boolean isOpenable;
  private Inventory inventory;

  public Item(int weight, String name, boolean isOpenable, int DPSvalue) {
    this.weight = weight;
    this.itemName = name;
    this.isOpenable = isOpenable;
    this.inventory = new Inventory(Integer.MAX_VALUE);
  }

  public void open() {
    if (!isOpenable)
      System.out.println("The " + itemName + " cannot be opened.");

  }

  public String shortDescription() {
    return "Room: " + itemName + "\n\n" + description;
  }

  public Inventory getInventory(){
    return inventory;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public boolean isOpenable() {
    return isOpenable;
  }

  public void setOpenable(boolean isOpenable) {
    this.isOpenable = isOpenable;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
