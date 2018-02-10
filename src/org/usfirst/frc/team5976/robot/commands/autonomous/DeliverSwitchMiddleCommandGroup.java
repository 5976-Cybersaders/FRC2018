package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.commands.EncoderDriveStraightCommand;
import org.usfirst.frc.team5976.robot.commands.GrabberCommand;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;

public class DeliverSwitchMiddleCommandGroup extends CommandGroup {
    public DeliverSwitchMiddleCommandGroup(Robot robot) {
    	DriveTrain driveTrain = robot.getDriveTrain();
    	GrabberSubsystem grabberSubsystem = robot.getGrabberSubsystem();
        addSequential(new EncoderDriveStraightCommand(driveTrain, 110));
        addSequential(new GrabberCommand(grabberSubsystem, 0, 1));
    }
}
