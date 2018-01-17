package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GrabberCommand extends Command {

	
	public GrabberCommand(GrabberSubsystem grabber) {
		
		
		requires(grabber);
	}
	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
