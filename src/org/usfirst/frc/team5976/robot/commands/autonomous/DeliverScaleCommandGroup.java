package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.commands.*;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

public class DeliverScaleCommandGroup extends CommandGroup {
    public DeliverScaleCommandGroup(int side, DriveTrain driveTrain, GrabberSubsystem grabberSubsystem, LiftSubsystem liftSubsystem){
        addSequential(new EncoderInitCommand(driveTrain));
        addSequential(new DoNothingCommand(SmartDashboardMap.DELAY));
        addSequential(new DriveAndRaise(driveTrain, liftSubsystem));
        addSequential(new EncoderTurnCommand(driveTrain, 90 * side));
        addSequential(new EncoderDriveStraightCommand(driveTrain, 5));
        addSequential(new ReleaseCubeCommand(grabberSubsystem));
    }

    class DriveAndRaise extends CommandGroup {
        public DriveAndRaise(DriveTrain driveTrain, LiftSubsystem liftSubsystem) {
            addParallel(new EncoderDriveStraightCommand(driveTrain, 300));
            addParallel(new MoveLiftCommand(liftSubsystem));
        }
    }
}
