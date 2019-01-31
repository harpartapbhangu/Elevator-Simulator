/**
 * Elevator Door
 */
public class Door {
	private boolean isOpen;
	public Door() {
		isOpen = false;
	}
	
	/**
	 * Returns the state of the door, true (open) or false (closed).
	 * @return
	 */
	public boolean getDoorState()
	{
		return isOpen;
	}
	
	/**
	 * Open or close the door.
	 * @param state True to open door, false to close the door
	 */
	public void setDoorState(boolean state)
	{
		isOpen = state;
	}
}
