package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5976.robot.commands.EncoderDriveStraightCommand;
import org.usfirst.frc.team5976.robot.commands.EncoderTurnCommand;
import org.usfirst.frc.team5976.robot.commands.GrabCubeCommand;
import org.usfirst.frc.team5976.robot.commands.ReleaseCubeCommand;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;

public class DeliverScaleLeftCommandGroup extends CommandGroup {
    public DeliverScaleLeftCommandGroup(DriveTrain driveTrain, GrabberSubsystem grabberSubsystem) {
        addSequential(new DriveAndRaise(driveTrain, grabberSubsystem));
        addSequential(new EncoderTurnCommand(driveTrain, 90));
        addSequential(new EncoderDriveStraightCommand(driveTrain, 5));
        addSequential(new ReleaseCubeCommand(grabberSubsystem));
    }
    
    class DriveAndRaise extends CommandGroup {
    	public DriveAndRaise(DriveTrain driveTrain, GrabberSubsystem grabberSubsystem) {
    		addParallel(new EncoderDriveStraightCommand(driveTrain, 50));
            addParallel(new GrabCubeCommand(grabberSubsystem));
    	}
    }
}
