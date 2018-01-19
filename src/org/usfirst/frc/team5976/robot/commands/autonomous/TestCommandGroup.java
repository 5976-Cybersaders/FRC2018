package org.usfirst.frc.team5976.robot.commands.autonomous;

import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.commands.EncoderDriveStraight;
import org.usfirst.frc.team5976.robot.commands.EncoderInit;
import org.usfirst.frc.team5976.robot.commands.PrintCommand1;
import org.usfirst.frc.team5976.robot.commands.PrintCommand2;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestCommandGroup extends CommandGroup{
	public TestCommandGroup(DriveTrain driveTrain){
		//addParallel(new PrintCommand1(5000));
		//addParallel(new PrintCommand2(5000));		
		addSequential(new EncoderInit(driveTrain));
		addSequential(new EncoderDriveStraight(driveTrain, SmartDashboardMap.DRIVE_DISTANCE_1));
		
	}
	

}
