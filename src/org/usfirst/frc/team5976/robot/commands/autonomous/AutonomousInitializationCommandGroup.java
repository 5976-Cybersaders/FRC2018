package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.commands.EncoderInitCommand;
import org.usfirst.frc.team5976.robot.commands.GrabCubeCommand;
import org.usfirst.frc.team5976.robot.commands.LiftTalonInitializationCommand;
import org.usfirst.frc.team5976.robot.commands.TimedDriveStraight;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

public class AutonomousInitializationCommandGroup extends CommandGroup {
    public AutonomousInitializationCommandGroup(Robot robot) {
        addSequential(new GrabCubeCommand(robot.getGrabberSubsystem(), 0.5));
        addSequential(new TimedDriveStraight(robot.getDriveTrain(), 0.5, 0.25));
        addSequential(new TimedDriveStraight(robot.getDriveTrain(), -0.25, 0.6));
        addSequential(new EncoderInitCommand(robot.getDriveTrain()));
        addSequential(new LiftTalonInitializationCommand(robot.getLiftSubsystem()));
    }
}