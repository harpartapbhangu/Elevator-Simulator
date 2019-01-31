/**
 * Elevator Motor
 */
public class Motor {
	private boolean isOn;
	
	public Motor()
	{
		isOn = false;
	}
	
	/**
	 * Get the state of the elevator motor.
	 * @return True is motor is on, otherwise will return false
	 */
	public boolean getMotorState()
	{
		return isOn;
	}
	
	/**
	 * Set elevator motor state.
	 * @param state True for on, false for off
	 */
	public void setMotorState(boolean state)
	{
		isOn = state;
	}
}
