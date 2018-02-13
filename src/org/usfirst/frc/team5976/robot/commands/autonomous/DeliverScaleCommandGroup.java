package org.usfirst.frc.team5976.robot.commands.autonomous;

import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.commands.*;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

import java.util.concurrent.atomic.AtomicBoolean;

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
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            addParallel(new MoveLiftCommand(liftSubsystem, 60, atomicBoolean ));
            addParallel(new MainCommandGroup(atomicBoolean));
        }
    }

    class MainCommandGroup extends CommandGroup {
        public MainCommandGroup(AtomicBoolean atomicBoolean) {
            addSequential(new DoNothingCommand(SmartDashboardMap.DELAY));
            addSequential(new EncoderDriveStraightCommand(driveTrain, 300));
            addSequential(new EncoderTurnCommand(driveTrain, 90 * side));
            addSequential(new EncoderDriveStraightCommand(driveTrain, 5));
            addSequential(new WaitForCompletionCommand(atomicBoolean));
            addSequential(new GrabberCommand(grabberSubsystem, 0, 1));
        }
    }
}
