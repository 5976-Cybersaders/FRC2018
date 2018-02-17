package org.usfirst.frc.team5976.robot.commands.autonomous;

import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.commands.EncoderInitCommand;
import org.usfirst.frc.team5976.robot.commands.GrabCubeCommand;
import org.usfirst.frc.team5976.robot.commands.LiftTalonInitializationCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousInitializationCommandGroup extends CommandGroup {
	public AutonomousInitializationCommandGroup(Robot robot) {
		addParallel(new EncoderInitCommand(robot.getDriveTrain()));
		addParallel(new LiftTalonInitializationCommand(robot.getLiftSubsystem()));
		addParallel(new GrabCubeCommand(robot.getGrabberSubsystem(), 2));
	}
}
