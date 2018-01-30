package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.commands.*;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

public class DeliverSwitchCommandGroup extends CommandGroup {
    public DeliverSwitchCommandGroup(int side, DriveTrain driveTrain, GrabberSubsystem grabberSubsystem){
        addSequential(new EncoderInitCommand(driveTrain));
        addSequential(new DoNothingCommand(SmartDashboardMap.DELAY));
        addSequential(new EncoderDriveStraightCommand(driveTrain, 140));
        addSequential(new EncoderTurnCommand(driveTrain, 90 * side));
        addSequential(new ReleaseCubeCommand(grabberSubsystem));
    }
}
