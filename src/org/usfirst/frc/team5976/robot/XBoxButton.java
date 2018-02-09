package org.usfirst.frc.team5976.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;

public class XBoxButton extends Button implements CMHDigitalInput {
	XboxController xBox;
	int buttonNumber;
	
	public XBoxButton(XboxController xBox, int buttonNumber){
		this.xBox = xBox;
		this.buttonNumber = buttonNumber;
	}
	
	public boolean get() {
		return xBox.getRawButton(buttonNumber);
	}
}
