package org.usfirst.frc.team5976.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import org.usfirst.frc.team5976.robot.GrabberController;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public abstract class GrabberCommand extends Command {
    protected long t0;
    protected int counter = 0, printInterval = 10;
    protected GrabberController grabberController;
	
	public GrabberCommand(GrabberSubsystem grabber) {
		grabberController = grabber.getGrabberController();
		requires(grabber);
	}

	@Override
    protected void initialize() {
	    System.out.println("Starting " + getClass().getSimpleName());
	    t0 = System.currentTimeMillis();
    }

	protected abstract boolean isGrabbing();

    protected boolean isAtLimit() {
    	System.out.println("is at limit: " + grabberController.isAtLimit(isGrabbing()));
    	System.out.println("is grabbing: " + isGrabbing());
    	return grabberController.isAtLimit(isGrabbing());
    }

	@Override
    protected void execute() {
	    grabberController.actuate(isGrabbing());
	    
	    if (counter++ % printInterval == 0) {
            System.out.println("Running " + getClass().getSimpleName() + "\nIs Grabbing: " + isGrabbing()
                    + "\nSwitch: " + isAtLimit());
            counter = 0;
        }
    }
	
	@Override
	protected boolean isFinished() {
	    return isAtLimit() || isTimedOut();
	}

}
