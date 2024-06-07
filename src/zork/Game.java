package zork;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Game {

  public static HashMap<String, Room> roomMap = new HashMap<String, Room>();

  private Parser parser;
  private Room currentRoom;
  private Inventory inventory;

  /**
   * Create the game and initialise its internal map.
   */
  public Game() {
    try {
      initRooms("src\\zork\\data\\rooms.json");
      initItems("src\\zork\\data\\rooms.json");
      initMonsters("src\\zork\\data\\monsters.json");

      currentRoom = roomMap.get("Bedroom");
      inventory = new Inventory(12);
    } catch (Exception e) {
      e.printStackTrace();
    }
    parser = new Parser();
  }

  private void initMonsters(String fileName) throws Exception{
    Path path = Path.of(fileName);
    String jsonString = Files.readString(path);
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(jsonString);

    JSONArray jsonMonsters = (JSONArray) json.get("monsters");

    for (Object monsterObj : jsonMonsters) {
      Monster monster = new Monster();
      /*Boolean isHeavy = (Boolean)((JSONObject) monsterObj).get("isHeavy");*/
      String roomId = (String) ((JSONObject) monsterObj).get("room_id");
      String name = (String) ((JSONObject) monsterObj).get("name");
      int health = 100;
      String monsterDescription = (String)((JSONObject) monsterObj).get("MonsterDescription");

      /*monster.setHeavy(isHeavy);*/
      monster.setName(name);
      monster.setHealth();
      monster.setDescription(monsterDescription);

      ArrayList<Attack> attacks = new ArrayList<Attack>();
      JSONArray jsonAttacks = (JSONArray) ((JSONObject) monsterObj).get("attacks");
      for (Object attackObj : jsonAttacks) {
        String description = (String) ((JSONObject) attackObj).get("description");
        int maxDamage = Integer.parseInt((String) ((JSONObject) attackObj).get("maxDamage"));

        Attack attack = new Attack(description, maxDamage);
        attacks.add(attack);
      }
      monster.setAttacks(attacks);
      roomMap.get(roomId).addMonster(monster);;
    }
  }

  private void initItems(String fileName) throws Exception{
    Path path = Path.of(fileName);
    String jsonString = Files.readString(path);
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(jsonString);
    JSONArray jsonItems = (JSONArray) json.get("items");

    for (Object itemObj : jsonItems) {
      //Item item = new Item();
      int weight = Integer.parseInt((String) ((JSONObject) itemObj).get("weight"));
      String itemName = (String) ((JSONObject) itemObj).get("name");
      String itemId = (String) ((JSONObject) itemObj).get("id");
      String roomId = (String) ((JSONObject) itemObj).get("room_id");

      String itemDescription = (String) ((JSONObject) itemObj).get("description");
      boolean isOpenable = Boolean.parseBoolean((String)((JSONObject) itemObj).get("isOpenable"));
      Item item = new Item( weight, itemName, isOpenable );
      item.setDescription(itemDescription);
      item.setItemName(itemName);
      item.setWeight(weight);

      roomMap.get(roomId).getInventory().addItem(item);
    }
  }

  private void initRooms(String fileName) throws Exception {
    Path path = Path.of(fileName);
    String jsonString = Files.readString(path);
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(jsonString);

    JSONArray jsonRooms = (JSONArray) json.get("rooms");

    for (Object roomObj : jsonRooms) {
      Room room = new Room();
      String roomName = (String) ((JSONObject) roomObj).get("name");
      String roomId = (String) ((JSONObject) roomObj).get("id");
      String roomDescription = (String) ((JSONObject) roomObj).get("description");
      String roomLongDescription = (String) ((JSONObject) roomObj).get("longDescription");
      Boolean roomBeen = (Boolean)((JSONObject) roomObj).get("been");
      room.setDescription(roomDescription);
      room.setLongDescription(roomLongDescription);
      room.setRoomName(roomName);

      JSONArray jsonExits = (JSONArray) ((JSONObject) roomObj).get("exits");
      ArrayList<Exit> exits = new ArrayList<Exit>();
      for (Object exitObj : jsonExits) {
        String direction = (String) ((JSONObject) exitObj).get("direction");
        String adjacentRoom = (String) ((JSONObject) exitObj).get("adjacentRoom");
        String keyId = (String) ((JSONObject) exitObj).get("keyId");
        Boolean isLocked = (Boolean) ((JSONObject) exitObj).get("isLocked");
        Boolean isOpen = (Boolean) ((JSONObject) exitObj).get("isOpen");
        Exit exit = new Exit(direction, adjacentRoom, isLocked, keyId, isOpen);
        exits.add(exit);
      }
      room.setExits(exits);
      roomMap.put(roomId, room);
    }
  }

  /**
   * Main play routine. Loops until end of play.
   */
  public void play() {
    printWelcome();

    boolean finished = false;
    while (!finished) {
      Command command;
      try {
        command = parser.getCommand();
        finished = processCommand(command);
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    System.out.println("Thank you for playing my game, Hope you had fun. Bye bye.");
  }

  /**
   * Print out the opening message for the player.
   */
  private void printWelcome() {
    System.out.println();
    System.out.println("Welcome to Zork!");
    System.out.println("Zork is a new, incredibly boring adventure game.");
    System.out.println("Type 'help' if you need help.");
    System.out.println();
    System.out.println(currentRoom.longDescription());
  }

  /**
   * Given a command, process (that is: execute) the command. If this command ends
   * the game, true is returned, otherwise false is returned.
   */
  private boolean processCommand(Command command) {
    if (command.isUnknown()) {
      System.out.println("I don't know what you mean...");
      return false;
    }

    String commandWord = command.getCommandWord();
    if (commandWord.equals("help"))
      printHelp();
    else if (commandWord.equals("go")){
      if (currentRoom.hasMonsters())
        System.out.println("The monster won't let you.");
      else
        goRoom(command);
    }else if (commandWord.equals("quit")) {
      if (command.hasSecondWord())
        System.out.println("Quit what?");
      else
        return true; // signal that we want to quit
    } else if (commandWord.equals("eat")) {
      System.out.println("Do you really think you should be eating at a time like this?");
    } else if (commandWord.equals("look")) {
      System.out.println(currentRoom.longDescription());
      //picks up an item from the room array
    } else if (commandWord.equals("take")){
        takeItem(command);
    }
    else if (commandWord.equals("drop")){
      dropItem(command);
    }
    return false;
  }

  // implementations of user commands:

  /**
   * Print out some help information. Here we print some stupid, cryptic message
   * and a list of the command words.
   */
  private void printHelp() {
    System.out.println("You are alone on an island, a storm cuts you off from main land. You have never seen a storm like this before its almost super natural");
    System.out.println("You find that strang things are happening, such as things lurking just around the cornner of your eyes.");
    System.out.println();
    System.out.println("Your command words are:");
    parser.showCommands();
  }

  /**
   * Try to go to one direction. If there is an exit, enter the new room,
   * otherwise print an error message.
   */
  private void goRoom(Command command) {
    if (!command.hasSecondWord()) {
      // if there is no second word, we don't know where to go...
      System.out.println("Go where?");
      return;
    }

    String direction = command.getSecondWord();
    

    // Try to leave current room.
    Room nextRoom = currentRoom.nextRoom(direction);

    if (nextRoom == null)
      System.out.println("There is no room that way, dummkopf!");
    else 
    {
      currentRoom = nextRoom;

      // see if you have been in the room already
      if (nextRoom.isBeen() ) {
        System.out.println(currentRoom.shortDescription());
      }
      else {
        nextRoom.setBeen(true);
        System.out.println(currentRoom.longDescription());
      }
    }
  }

  private void takeItem(Command command){
    if (!command.hasSecondWord()) {
     // if there is no second word, we don't know where to go...
     System.out.println("Take what?");
     return;
    }

    String itemName = command.getSecondWord();

    Item item = currentRoom.getInventory().removeItem(itemName);

    if (item != null){
      if (inventory.addItem(item)){
        System.out.println("You took the " + itemName);
      }else{
        System.out.println("The " + itemName + " was too heavy to take.");
      }
    }else{
      System.out.println("Are you seeing something I don't...");
    }

  }

  private void dropItem(Command command){
    if (!command.hasSecondWord()) {
     // if there is no second word, we don't know where to go...
     System.out.println("Drop what?");
     return;
    }

    String itemName = command.getSecondWord();

    Item item = currentRoom.getInventory().removeItem(itemName);

    if (item != null){
      if (inventory.addItem(item)){
        System.out.println("You droped the " + itemName + " into " + currentRoom);
    }else{
      System.out.println("Are you holding something I don't see...");
    }

  }
 }
}
