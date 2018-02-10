package org.usfirst.frc.team5976.robot.commands.autonomous;

import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.commands.EncoderInitCommand;
import org.usfirst.frc.team5976.robot.commands.GrabberCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousInitializationCommandGroup extends CommandGroup {
	public AutonomousInitializationCommandGroup(Robot robot) {
		addParallel(new EncoderInitCommand(robot.getDriveTrain()));
		addParallel(new GrabberCommand(robot.getGrabberSubsystem(), 1, 2));
	}
}
