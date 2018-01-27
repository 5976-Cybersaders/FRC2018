package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5976.robot.commands.EncoderDriveStraightCommand;
import org.usfirst.frc.team5976.robot.commands.ReleaseCubeCommand;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;

public class DeliverSwitchMiddleCommandGroup extends CommandGroup {
    public DeliverSwitchMiddleCommandGroup(DriveTrain driveTrain, GrabberSubsystem grabberSubsystem){
        addSequential(new EncoderDriveStraightCommand(driveTrain, 110));
        addSequential(new ReleaseCubeCommand(grabberSubsystem));
    }
}
