package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

public class DeliverScaleRightCommandGroup extends DeliverScaleCommandGroup {
    public DeliverScaleRightCommandGroup(DriveTrain driveTrain, GrabberSubsystem grabberSubsystem, LiftSubsystem liftSubsystem) {
        super(1, driveTrain, grabberSubsystem, liftSubsystem);
    }
}
