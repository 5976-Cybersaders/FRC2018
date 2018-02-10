package org.usfirst.frc.team5976.robot.commands.autonomous;

import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.commands.DoNothingCommand;
import org.usfirst.frc.team5976.robot.commands.EncoderDriveStraightCommand;
import org.usfirst.frc.team5976.robot.commands.EncoderTurnCommand;
import org.usfirst.frc.team5976.robot.commands.GrabberCommand;
import org.usfirst.frc.team5976.robot.commands.MoveLiftCommand;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DeliverScaleCommandGroup extends CommandGroup {
    public DeliverScaleCommandGroup(int side, Robot robot){
    	DriveTrain driveTrain = robot.getDriveTrain();
    	GrabberSubsystem grabberSubsystem = robot.getGrabberSubsystem();
    	LiftSubsystem liftSubsystem = robot.getLiftSubsystem();
    	
    	addSequential(new AutonomousInitializationCommandGroup(robot));
        addSequential(new DoNothingCommand(SmartDashboardMap.DELAY));
        addSequential(new DriveAndRaise(driveTrain, liftSubsystem));
        addSequential(new EncoderTurnCommand(driveTrain, 90 * side));
        addSequential(new EncoderDriveStraightCommand(driveTrain, 5));
        addSequential(new GrabberCommand(grabberSubsystem, 0, 1));
    }
    
    class DriveAndRaise extends CommandGroup {
        public DriveAndRaise(DriveTrain driveTrain, LiftSubsystem liftSubsystem) {
            addParallel(new EncoderDriveStraightCommand(driveTrain, 300));
            addParallel(new MoveLiftCommand(liftSubsystem));
        }
    }
}
