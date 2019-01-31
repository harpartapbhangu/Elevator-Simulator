/**
 * Elevator Button
 */
public class ElevatorButton {
	private boolean isSelected;
	
	public ElevatorButton()
	{
		isSelected = false;
	}
	
	/**
	 * Get button state.
	 * @return True if selected, otherwise false.
	 */
	public boolean getButtonState()
	{
		return isSelected;
	}
	
	/**
	 * Set button state.
	 * @param state True to select, otherwise false
	 * @return
	 */
	public void setButtonState(boolean state)
	{
		isSelected = state;
	}
}
