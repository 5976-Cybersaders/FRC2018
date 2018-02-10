package org.usfirst.frc.team5976.robot.commands.autonomous;

import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.commands.DoNothingCommand;
import org.usfirst.frc.team5976.robot.commands.EncoderDriveStraightCommand;
import org.usfirst.frc.team5976.robot.commands.EncoderTurnCommand;
import org.usfirst.frc.team5976.robot.commands.GrabberCommand;
import org.usfirst.frc.team5976.robot.commands.MoveLiftCommand;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DeliverScaleCommandGroup extends CommandGroup {
    private DriveTrain driveTrain;
    private GrabberSubsystem grabberSubsystem;
    private LiftSubsystem liftSubsystem;
    private int side;

    public DeliverScaleCommandGroup(int side, Robot robot){
        driveTrain = robot.getDriveTrain();
        grabberSubsystem = robot.getGrabberSubsystem();
        liftSubsystem = robot.getLiftSubsystem();
        this.side = side;

        addSequential(new AutonomousInitializationCommandGroup(robot));
        addSequential(new CombinedCommandGroup());
    }

    class CombinedCommandGroup extends CommandGroup {
        public CombinedCommandGroup() {
            addParallel(new MoveLiftCommand(liftSubsystem, 60));
            addParallel(new MainCommmandGroup());
        }
    }

    class MainCommmandGroup extends CommandGroup {
        public MainCommmandGroup() {
            addSequential(new DoNothingCommand(SmartDashboardMap.DELAY));
            addSequential(new EncoderDriveStraightCommand(driveTrain, 300));
            addSequential(new EncoderTurnCommand(driveTrain, 90 * side));
            addSequential(new EncoderDriveStraightCommand(driveTrain, 5));
            addSequential(new GrabberCommand(grabberSubsystem, 0, 1));
        }
    }
}
