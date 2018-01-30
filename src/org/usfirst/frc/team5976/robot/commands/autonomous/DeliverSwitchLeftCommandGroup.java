package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;

public class DeliverSwitchLeftCommandGroup extends DeliverSwitchCommandGroup{
    public DeliverSwitchLeftCommandGroup(DriveTrain driveTrain, GrabberSubsystem grabberSubsystem) {
        super(1, driveTrain, grabberSubsystem);
    }
}
