/**
 * FloorButton
 */
public class FloorButton {
	private boolean upButton;
	private boolean downButton;
	private boolean isUpSelected;
	private boolean isDownSelected;
	
	/**
	 * For floors with both up and down buttons.
	 */
	public FloorButton()
	{
		upButton = true;
		downButton = true;
		isUpSelected = false;
		isDownSelected = false;
	}
	
	/**
	 * For top and bottom floors.
	 * @param upButton True if floor has an up button, otherwise false
	 * @param downButton True if floor has a down button, otherwise false
	 */
	public FloorButton(boolean upButton, boolean downButton)
	{
		this.upButton = upButton;
		this.downButton = downButton;
		isUpSelected = false;
		isDownSelected = false;
	}
	
	/**
	 * Get up button state.
	 * @return True if selected, otherwise false
	 */
	public boolean getUpButtonState()
	{
		return isUpSelected;
	}
	
	/**
	 * Get up button state.
	 * @return True if selected, otherwise false
	 */
	public boolean getDownButtonState()
	{
		return isDownSelected;
	}
	
	/**
	 * Set the state of the down button if it exists.
	 * @param state True if button is selected, otherwise false
	 */
	public void setDownButtonState(boolean state)
	{
		if (downButton) {
			isDownSelected = state;
		}
	}
	
	/**
	 * Set the state of the up button if it exists.
	 * @param state True if button is selected, otherwise false
	 */
	public void setUpButtonState(boolean state)
	{
		if (upButton) {
			isUpSelected = state;
		}
	}
}
