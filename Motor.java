/**
 * Elevator Motor
 */
public class Motor {
	private int state; //0 for stop, 1 for up, 2 for down
	
	public Motor()
	{
		state = 0;
	}
	
	/**
	 * Get the state of the elevator motor.
	 * @return 0 if motor is stopped, 1 if motor is going up, 2 if motor is going down
	 */
	public int getMotorState()
	{
		return state;
	}
	
	/**
	 * Set elevator motor state.
	 * @param state 0 to stop motor, 1 to go up, 2 to go down
	 */
	public void setMotorState(int state)
	{
		this.state = state;
	}
}
