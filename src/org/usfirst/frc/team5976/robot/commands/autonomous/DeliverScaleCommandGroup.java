package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5976.robot.Position;
import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.commands.*;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

public class DeliverScaleCommandGroup extends CommandGroup {
    private DriveTrain driveTrain;
    private GrabberSubsystem grabberSubsystem;
    private LiftSubsystem liftSubsystem;
    private int position;

    public DeliverScaleCommandGroup(Position position, Robot robot) {
        driveTrain = robot.getDriveTrain();
        grabberSubsystem = robot.getGrabberSubsystem();
        liftSubsystem = robot.getLiftSubsystem();
        this.position = position.getIntPosition();

        addSequential(new AutonomousInitializationCommandGroup(robot));
        addSequential(new CombinedCommandGroup());
        addSequential(new WaitForReleaseCompleted());
        addSequential(new EncoderDriveStraightCommand(driveTrain, -10));
        addSequential(new EncoderTurnCommand(driveTrain, -90 * this.position));
        addSequential(new CombinedPostCommandGroup());
    }

    class CombinedCommandGroup extends CommandGroup {
        public CombinedCommandGroup() {
            addParallel(RaiseLiftCommand.RaiseLiftToScale(liftSubsystem));
            addParallel(new MainCommandGroup());
        }
    }

    class MainCommandGroup extends CommandGroup {
        public MainCommandGroup() {
            addSequential(new DoNothingCommand(SmartDashboardMap.DELAY));
            addSequential(new EncoderDriveStraightCommand(driveTrain, 305, 10));
            addSequential(new EncoderTurnCommand(driveTrain, -90 * position, 2.5));
            //WAS ORIGINALLY 25.88
            addSequential(new EncoderDriveStraightCommand(driveTrain, 6.5, 2)); //TODO: Check timeout
            //22.88 + 3 = 25.88 since there is no fence around scale
            //3 is from the front bumper
            addSequential(new WaitForLiftRaised());
            addSequential(new ReleaseCubeCommand(grabberSubsystem, RobotMap.RELEASE_TIME_OUT));
        }
    }

    class PostReleaseCommandGroup extends CommandGroup {
        public PostReleaseCommandGroup() {
            addSequential(new EncoderDriveStraightCommand(driveTrain, 120)); //165
            //The front of the robot should end up at the front edge of the switch

//            addSequential(new EncoderTurnCommand(driveTrain, 90 * position));
//            addSequential(new EncoderDriveStraightCommand(driveTrain, 50));
        }
    }

    class CombinedPostCommandGroup extends CommandGroup {
        public CombinedPostCommandGroup() {
            addParallel(new ReturnLiftToStartPointCommand(liftSubsystem));
            addParallel(new PostReleaseCommandGroup());
        }
    }
}
