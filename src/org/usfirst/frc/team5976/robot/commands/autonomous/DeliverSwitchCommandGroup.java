package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team5976.robot.Position;
import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.commands.*;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

import java.util.concurrent.atomic.AtomicBoolean;

public class DeliverSwitchCommandGroup extends CommandGroup {
    private DriveTrain driveTrain;
    private GrabberSubsystem grabberSubsystem;
    private LiftSubsystem liftSubsystem;
    private int position;
    private double inchesToLiftToScale = 30;

    public DeliverSwitchCommandGroup(Position position, Robot robot){
    	driveTrain = robot.getDriveTrain();
    	grabberSubsystem = robot.getGrabberSubsystem();
    	liftSubsystem = robot.getLiftSubsystem();
    	this.position = position.getIntPosition();
    	
        addSequential(new AutonomousInitializationCommandGroup(robot));
        addSequential(new CombinedCommandGroup());
        addSequential(new PostReleaseCommandGroup());
        addSequential(new EncoderTurnCommand(driveTrain, -90 * this.position));
        addSequential(new EncoderDriveStraightCommand(driveTrain, 15));
        addSequential(new EncoderTurnCommand(driveTrain, 90 * this.position));
        addSequential(new EncoderDriveStraightCommand(driveTrain, 15));
    }

    class CombinedCommandGroup extends CommandGroup {
        public CombinedCommandGroup() {
            AtomicBoolean isAtLiftSetPoint = new AtomicBoolean(false);
            addParallel(new MoveLiftCommand(liftSubsystem, inchesToLiftToScale, isAtLiftSetPoint));
            addParallel(new MainCommandGroup(isAtLiftSetPoint));
        }
    }

    class MainCommandGroup extends CommandGroup {
        public MainCommandGroup(AtomicBoolean isAtLiftSetPoint) {
            addSequential(new DoNothingCommand(SmartDashboardMap.DELAY));
            addSequential(new EncoderDriveStraightCommand(driveTrain, 129));
            addSequential(new EncoderTurnCommand(driveTrain, -90 * position));
            addSequential(new WaitForCompletionCommand(isAtLiftSetPoint));
            addSequential(new EncoderDriveStraightCommand(driveTrain, 5));
            addSequential(new GrabberCommand(grabberSubsystem, 0, 1));
        }
    }
    
    class PostReleaseCommandGroup extends CommandGroup {
    	public PostReleaseCommandGroup() {
    		addParallel(new EncoderDriveStraightCommand(driveTrain, -5));
    		addParallel(new MoveLiftCommand(liftSubsystem, 0));
    	}
    }
}
