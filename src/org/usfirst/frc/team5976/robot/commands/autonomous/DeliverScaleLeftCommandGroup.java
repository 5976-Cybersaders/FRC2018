package org.usfirst.frc.team5976.robot.commands.autonomous;

import org.usfirst.frc.team5976.robot.commands.*;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

public class DeliverScaleLeftCommandGroup extends DeliverScaleCommandGroup {
    public DeliverScaleLeftCommandGroup(DriveTrain driveTrain, GrabberSubsystem grabberSubsystem, LiftSubsystem liftSubsystem) {
    	super(-1, driveTrain, grabberSubsystem, liftSubsystem);
    }
}
