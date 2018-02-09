package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;


public class ReleaseCubeCommand extends GrabberCommand {

	public ReleaseCubeCommand(GrabberSubsystem grabber) {
		
		super(grabber);
	}

    @Override
    protected boolean isGrabbing() {
        return false;
    }
}
