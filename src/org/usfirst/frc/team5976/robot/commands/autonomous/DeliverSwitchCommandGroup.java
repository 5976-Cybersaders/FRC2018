package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.commands.*;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

public class DeliverSwitchCommandGroup extends CommandGroup {
    private DriveTrain driveTrain;
    private GrabberSubsystem grabberSubsystem;
    private LiftSubsystem liftSubsystem;
    private int side;

    public DeliverSwitchCommandGroup(int side, Robot robot){
    	driveTrain = robot.getDriveTrain();
    	grabberSubsystem = robot.getGrabberSubsystem();
    	liftSubsystem = robot.getLiftSubsystem();
    	this.side = side;
    	
        addSequential(new AutonomousInitializationCommandGroup(robot));
        addSequential(new CombinedCommandGroup());
    }

    class CombinedCommandGroup extends CommandGroup {
        public CombinedCommandGroup() {
            addParallel(new MoveLiftCommand(liftSubsystem, 30));
            addParallel(new MainCommandGroup());
        }
    }

    class MainCommandGroup extends CommandGroup {
        public MainCommandGroup() {
            addSequential(new DoNothingCommand(SmartDashboardMap.DELAY));
            addSequential(new EncoderDriveStraightCommand(driveTrain, 140));
            addSequential(new EncoderTurnCommand(driveTrain, 90 * side));
            addSequential(new GrabberCommand(grabberSubsystem, 0, 1));
        }
    }
}
