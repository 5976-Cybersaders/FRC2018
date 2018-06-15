package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.commands.autonomous.AutonomousTasksStatus;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

public class RaiseLiftCommand extends MoveLiftCommand {
    private double timeOut;

    private RaiseLiftCommand(LiftSubsystem liftSubsystem, int setPoint, int timeOut) {
        super(liftSubsystem, setPoint);
        this.timeOut = timeOut;
    }

    public static RaiseLiftCommand RaiseLiftToSwitch(LiftSubsystem liftSubsystem) {
        return new RaiseLiftCommand(liftSubsystem, 9000, 5);
    }

    public static RaiseLiftCommand RaiseLiftToScale(LiftSubsystem liftSubsystem) {
        return new RaiseLiftCommand(liftSubsystem, 19000, 10);
    }

    private boolean isAtSetPoint() {
        return counter > 50 && Math.abs(talon.getSelectedSensorPosition(0) - setPoint) < SmartDashboardMap.LIFT_ALLOWABLE_ERROR.getDouble();
    }

    @Override
    protected void initialize() {
        super.initialize();
        setTimeout(timeOut);
    }

    @Override
    protected void execute() {
        super.execute();
        if (isAtSetPoint() || isTimedOut()) {
            AutonomousTasksStatus.LIFT_IS_RAISED.set(true);
        }
    }

    @Override
    protected boolean isFinished() {
        return AutonomousTasksStatus.RELEASE_IS_COMPLETED.get();
    }
}
