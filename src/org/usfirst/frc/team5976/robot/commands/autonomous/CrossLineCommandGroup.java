package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.commands.*;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

public class CrossLineCommandGroup extends CommandGroup {
    private DriveTrain driveTrain;
    private LiftSubsystem liftSubsystem;

    public CrossLineCommandGroup(Robot robot) {
        driveTrain = robot.getDriveTrain();
        liftSubsystem = robot.getLiftSubsystem();

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
            addSequential(new DoNothingCommand(0.5));
            addSequential(new EncoderDriveStraightCommand(driveTrain, 102, 5)); //TODO: Check timeout
        }
    }
}
