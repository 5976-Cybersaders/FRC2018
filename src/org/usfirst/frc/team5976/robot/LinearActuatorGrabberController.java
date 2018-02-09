package org.usfirst.frc.team5976.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Servo;

public class LinearActuatorGrabberController implements GrabberController {

    private final double MIN_LIMIT = 0.25, MAX_LIMIT = 0.75; //4095 is max 12-bit value
    private AnalogInput leftInput, rightInput;
    private Servo leftServo, rightServo;

    public LinearActuatorGrabberController() {
        leftInput = new AnalogInput(RobotMap.LINEAR_ACTUATOR_LEFT_ANALOG_INPUT);
        rightInput = new AnalogInput(RobotMap.LINEAR_ACTUATOR_RIGHT_ANALOG_INPUT);
        leftServo = new Servo(RobotMap.LINEAR_ACTUATOR_LEFT_SERVO);
        rightServo = new Servo(RobotMap.LINEAR_ACTUATOR_RIGHT_SERVO);
    }

    @Override
    public boolean isAtLimit(boolean isGrabbing) {
        if (isGrabbing)
            return Math.min(leftInput.getValue(), rightInput.getValue()) <= MIN_LIMIT;
        else
            return Math.max(leftInput.getValue(), rightInput.getValue()) >= MAX_LIMIT;
    }
    
    public void actuate(boolean isGrabbing) {
    	if (isAtLimit(isGrabbing)) {
	    	leftServo.setPosition(0.5);
	    	rightServo.setPosition(0.5);
	    	return;
	    }
	    double position = isGrabbing ? MAX_LIMIT : MIN_LIMIT;
	    leftServo.setPosition(position);
	    rightServo.setPosition(position);
    }
}
