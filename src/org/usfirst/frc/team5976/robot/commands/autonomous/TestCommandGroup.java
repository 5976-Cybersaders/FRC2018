package org.usfirst.frc.team5976.robot.commands.autonomous;

import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.commands.EncoderDriveStraightCommand;
import org.usfirst.frc.team5976.robot.commands.EncoderInitCommand;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestCommandGroup extends CommandGroup {
	public TestCommandGroup(Robot robot) {
		DriveTrain driveTrain = robot.getDriveTrain();
		addSequential(new EncoderInitCommand(driveTrain));
		addSequential(new EncoderDriveStraightCommand(driveTrain, 18.8495*3));
		//addSequential(new EncoderTurnCommand(driveTrain, 90));
	}
	

}
