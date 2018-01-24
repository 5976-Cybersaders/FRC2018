package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GrabberCommand extends Command {
    protected long t0;
    protected int counter = 0, printInterval = 10;
	
	public GrabberCommand(GrabberSubsystem grabber) {
		requires(grabber);
	}

	@Override
    protected void initialize() {
	    System.out.println("Starting " + getClass().getSimpleName());
	    t0 = System.currentTimeMillis();
    }

	@Override
    protected void execute() {
	    if (counter++ % printInterval == 0) {
            System.out.println("Running " + getClass().getSimpleName());
            counter = 0;
        }
    }
	
	@Override
	protected boolean isFinished() {
	    boolean isFinished = System.currentTimeMillis() - t0 > 1000;
	    if (isFinished) 
	    	System.out.println("Finished " + getClass().getSimpleName());
	    return isFinished;
	}

}
