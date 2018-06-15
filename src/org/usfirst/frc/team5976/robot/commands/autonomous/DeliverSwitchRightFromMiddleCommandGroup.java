package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.commands.*;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

public class DeliverSwitchRightFromMiddleCommandGroup extends CommandGroup {
    private DriveTrain driveTrain;
    private LiftSubsystem liftSubsystem;
    private GrabberSubsystem grabberSubsystem;

    public DeliverSwitchRightFromMiddleCommandGroup(Robot robot) {
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
            // 101 may be exact, bumping up to 102 for error margin
            addSequential(new DoNothingCommand(0.5));
            addSequential(new EncoderDriveStraightCommand(driveTrain, 102, 5)); //TODO: Check timeout
            addSequential(new WaitForLiftRaised());
            addSequential(new ReleaseCubeCommand(grabberSubsystem, RobotMap.RELEASE_TIME_OUT));
        }
    }
}
