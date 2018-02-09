package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;

public class GrabCubeCommand extends GrabberCommand {


	public GrabCubeCommand(GrabberSubsystem grabber) {
		super(grabber);
	}

	@Override
	protected boolean isGrabbing() {
		return true;
	}
}
