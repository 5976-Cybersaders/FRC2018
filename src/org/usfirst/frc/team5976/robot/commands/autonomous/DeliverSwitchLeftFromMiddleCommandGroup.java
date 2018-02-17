package org.usfirst.frc.team5976.robot.commands.autonomous;

import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.commands.EncoderDriveStraightCommand;
import org.usfirst.frc.team5976.robot.commands.EncoderTurnCommand;
import org.usfirst.frc.team5976.robot.commands.RaiseLiftCommand;
import org.usfirst.frc.team5976.robot.commands.ReleaseCubeCommand;
import org.usfirst.frc.team5976.robot.commands.WaitForLiftRaised;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DeliverSwitchLeftFromMiddleCommandGroup extends CommandGroup {
	private DriveTrain driveTrain;
	private LiftSubsystem liftSubsystem;
	private GrabberSubsystem grabberSubsystem;
	
    public DeliverSwitchLeftFromMiddleCommandGroup(Robot robot) {
    	driveTrain = robot.getDriveTrain();
    	liftSubsystem = robot.getLiftSubsystem();
    	grabberSubsystem = robot.getGrabberSubsystem();
    	
    	addSequential(new AutonomousInitializationCommandGroup(robot));
        addSequential(new CombinedCommandGroup());
    }
    
    class CombinedCommandGroup extends CommandGroup {
    	public CombinedCommandGroup() {
    		addParallel(RaiseLiftCommand.RaiseLiftToScale(liftSubsystem));
    		addParallel(new MainCommandGroup());
    	}
    }
    
    class MainCommandGroup extends CommandGroup {
    	public MainCommandGroup() {
    		addSequential(new EncoderDriveStraightCommand(driveTrain, 54));
    		addSequential(new EncoderTurnCommand(driveTrain, -90));
    		addSequential(new EncoderDriveStraightCommand(driveTrain, 100));
    		addSequential(new EncoderTurnCommand(driveTrain, 90));
    		addSequential(new EncoderDriveStraightCommand(driveTrain, 54));
    		addSequential(new WaitForLiftRaised());
    		addSequential(new ReleaseCubeCommand(grabberSubsystem, 1));
    	}
    }
}
