package org.usfirst.frc.team5976.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;

public class GearRackGrabberController implements GrabberController {

    private Servo servo;
    private DigitalInput grabLimit, releaseLimit;

    public GearRackGrabberController() {
        servo = new Servo(RobotMap.GEAR_RACK_SERVO);
        grabLimit = new DigitalInput(RobotMap.GEAR_RACK_RETRACTION_LIMIT_SWITCH);
        releaseLimit = grabLimit; //new DigitalInput(RobotMap.GEAR_RACK_EXTENSION_LIMIT_SWITCH);
    }

    @Override
    public boolean isAtLimit(boolean isGrabbing) {
        return isGrabbing ? isAtLimit(grabLimit) : isAtLimit(releaseLimit);
    }

    @Override
    public void actuate(boolean isGrabbing) {
    	if (isAtLimit(isGrabbing)) {
	    	servo.setAngle(90); //Placeholder value
	    	return;
	    }
    	servo.setAngle(isGrabbing ? 180 : 0);
    }

    private boolean isAtLimit(DigitalInput digitalInput){
        return !digitalInput.get();
    }
}
