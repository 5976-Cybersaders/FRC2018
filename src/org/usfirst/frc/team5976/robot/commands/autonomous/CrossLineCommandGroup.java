package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5976.robot.commands.EncoderDriveStraightCommand;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

public class CrossLineCommandGroup extends CommandGroup {

    public CrossLineCommandGroup(DriveTrain driveTrain){
        addSequential(new EncoderDriveStraightCommand(driveTrain, 90));
    }

}
