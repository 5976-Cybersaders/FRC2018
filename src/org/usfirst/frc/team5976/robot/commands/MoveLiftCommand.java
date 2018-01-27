package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class MoveLiftCommand extends Command {

	LiftSubsystem lift;
	
	public MoveLiftCommand(LiftSubsystem lift) {
		this.lift = lift;
		
		
		requires(lift);
	}
	
	
	@Override
	protected boolean isFinished() {
		
		return true;
	}
	
	protected void execute() {
		System.out.println("LIFT COMMAND");
		
	}

	
	
}
