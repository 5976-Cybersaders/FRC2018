package org.usfirst.frc.team5976.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

public class TimedDriveStraight extends Command {
    private DriveTrain driveTrain;
    private SpeedControllerGroup leftSide, rightSide;

    double speed, time;

    public TimedDriveStraight(DriveTrain driveTrain, double speed, double time) {
        this.driveTrain = driveTrain;
        this.speed = speed;
        this.time = time;

        leftSide = new SpeedControllerGroup(driveTrain.getLeftMaster(), driveTrain.getLeftSlave());
        rightSide = new SpeedControllerGroup(driveTrain.getRightMaster(), driveTrain.getRightSlave());
    }

    @Override
    protected void initialize() {
        driveTrain.invertMotors();
        initTalon(driveTrain.getLeftMaster());
        initTalon(driveTrain.getRightMaster());
        setTimeout(time);
    }

    private void initTalon(WPI_TalonSRX talon) {
        talon.selectProfileSlot(1, 0);
        talon.configPeakOutputForward(1, 0);
        talon.configPeakOutputReverse(-1, 0);
        talon.configNominalOutputForward(0, 0);
        talon.configNominalOutputReverse(0, 0);

        talon.selectProfileSlot(1, 0);
        talon.configPeakOutputForward(1, 0);
        talon.configPeakOutputReverse(-1, 0);
        talon.configNominalOutputForward(0, 0);
        talon.configNominalOutputReverse(0, 0);
    }

    @Override
    protected void execute() {
        leftSide.set(speed);
        rightSide.set(speed);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
}
