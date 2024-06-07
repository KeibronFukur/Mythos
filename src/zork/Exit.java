package zork;

/**
 * Exit
 */
public class Exit extends OpenableObject {
  private String direction;
  private String adjacentRoom;

  public Exit(String direction, String adjacentRoom, boolean isLocked, String keyId) {
    super(isLocked, keyId);
    this.direction = direction;
    this.adjacentRoom = adjacentRoom;
  }

  public Exit(String direction, String adjacentRoom, boolean isLocked, String keyId, Boolean isOpen) {
    super(isLocked, keyId, isOpen);
    this.direction = direction;
    this.adjacentRoom = adjacentRoom;
  }

  public Exit(String direction, String adjacentRoom) {
    this.direction = direction;
    this.adjacentRoom = adjacentRoom;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public String getAdjacentRoom() {
    return adjacentRoom;
  }

  public void setAdjacentRoom(String adjacentRoom) {
    this.adjacentRoom = adjacentRoom;
  }

}
// Method to toggle the state of the door (open or closed)

// Method to toggle the state of the door (open or closed)
 public void toggleDoor() {
  if (isLocked()) {
      System.out.println("The direction is locked.");
  } else {
      setOpen(!isOpen()); // Toggle the state of the door
      if (isOpen()) {
          System.out.println("The door is now open.");
      } else {
          System.out.println("The door is now closed.");
      }
  }
}