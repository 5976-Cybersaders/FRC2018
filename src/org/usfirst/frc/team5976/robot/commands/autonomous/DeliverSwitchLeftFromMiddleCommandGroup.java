package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.commands.*;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

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
            addParallel(RaiseLiftCommand.RaiseLiftToSwitch(liftSubsystem));
            addParallel(new MainCommandGroup());
        }
    }

    class MainCommandGroup extends CommandGroup {
        public MainCommandGroup() {
            addSequential(new DoNothingCommand(SmartDashboardMap.DELAY));
            addSequential(new EncoderDriveStraightCommand(driveTrain, 51, 3));
            addSequential(new EncoderTurnCommand(driveTrain, -90, 2.5));
            addSequential(new EncoderDriveStraightCommand(driveTrain, 113, 7)); // was originally 135.44
            //148.44 = center of robot to center of switch plate
            addSequential(new EncoderTurnCommand(driveTrain, 90, 2.5));
            addSequential(new EncoderDriveStraightCommand(driveTrain, 51, 3)); //TODO: Check timeout
            addSequential(new WaitForLiftRaised());
            addSequential(new ReleaseCubeCommand(grabberSubsystem, RobotMap.RELEASE_TIME_OUT));
        }
    }
}
