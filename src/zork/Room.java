package zork;

import java.util.ArrayList;

public class Room {

  private String roomName;
  private String longDescription;
  private String description;
  private boolean been;
  private ArrayList<Exit> exits;
  private ArrayList<Monster> monsters;
  //private ArrayList<Item> roomItems;
  private Inventory inventory;

  public ArrayList<Exit> getExits() {
    return exits;
  }

  public void setExits(ArrayList<Exit> exits) {
    this.exits = exits;
  }

  public Inventory getInventory(){
    return inventory;
  }

  /**
   * Create a room described "description". Initially, it has no exits.
   * "description" is something like "a kitchen" or "an open court yard".
   */
  public Room(String description, String longDescription, boolean been) {
    this.description = description;
    this.longDescription = longDescription;
    this.been = been;
   // this.longDescription = longDescription;
    exits = new ArrayList<Exit>();
    monsters = new ArrayList<Monster>();
    inventory = new Inventory(Integer.MAX_VALUE);
  }

  public Room() {
    roomName = "DEFAULT ROOM";
    description = "DEFAULT DESCRIPTION";
    longDescription = "DEFAULT DESCRIPTION?";
    exits = new ArrayList<Exit>();
    monsters = new ArrayList<Monster>();
  }

  public void addMonster(Monster m){
    monsters.add(m);
    inventory = new Inventory(Integer.MAX_VALUE);

  }
  public void Remove(Monster m){
    monsters.remove(m);
  }

  public void addExit(Exit exit) throws Exception {
    exits.add(exit);
  }

  /**
   * Return the description of the room (the one that was defined in the
   * constructor).
   */
  public String shortDescription() {
    return "Room: " + roomName + "\n\n" + description;
  }

  public boolean hasMonsters(){
    return monsters.size() > 0;
  }

  /**
   * Return a long description of this room, on the form: You are in the kitchen.
   * Exits: north west
   */
  public String longDescription() {

    return "Room: " + roomName + "\n\n" + longDescription + "\n" + exitString();
  }
  public String monsterDescription(){
    return "Monster: " + monsters;
  }
  /**
   * Return a string describing the room's exits, for example "Exits: north west
   * ".
   */
  private String exitString() {
    String returnString = "Exits: ";
    for (Exit exit : exits) {
      returnString += exit.getDirection() + " ";
    }

    return returnString;
  }

  /**
   * Return the room that is reached if we go from this room in direction
   * "direction". If there is no room in that direction, return null.
   */
  public Room nextRoom(String direction) {
    try {
      for (Exit exit : exits) {
 
        if (exit.getDirection().equalsIgnoreCase(direction)) {
          if (exit.isOpen()) {
          String adjacentRoom = exit.getAdjacentRoom();
          return Game.roomMap.get(adjacentRoom);
        } else {
          System.out.println("The door is closed. You need to open it first.");
          return null;
        }
      }
      }
    } catch (IllegalArgumentException ex) {
      System.out.println(direction + " is not a valid direction.");
      return null;
    }
 
    System.out.println(direction + " is not a valid direction.");
    return null;
  }
  // 
  /*
   * private int getDirectionIndex(String direction) { int dirIndex = 0; for
   * (String dir : directions) { if (dir.equals(direction)) return dirIndex; else
   * dirIndex++; }
   * 
   * throw new IllegalArgumentException("Invalid Direction"); }
   */
  public String getRoomName() {
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  //sets and gets the long description
  public void setLongDescription(String longDescription) {
    this.longDescription = longDescription;
  }

  public String getLongDescription() {
    return longDescription;
  }

  //sets and gets been
  public void setBeen(boolean been){
    this.been = been;
  }  

  public boolean isBeen()
  {
    return been;
  }
}
