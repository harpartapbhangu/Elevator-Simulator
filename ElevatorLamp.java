/**
 * Elevator Lamp
 */
public class ElevatorLamp {
	private boolean isOn;
	
	public ElevatorLamp()
	{
		isOn = false;
	}
	
	/**
	 * Get lamp state.
	 * @return True if lamp is on, otherwise false
	 */
	public boolean getLampState()
	{
		return isOn;
	}
	
	/**
	 * Set lamp state.
	 * @param state True to turn lamp on, false to turn it off
	 */
	public void setLampState(boolean state)
	{
		isOn = state;
	}	
}
