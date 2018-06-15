package org.usfirst.frc.team5976.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

public class EncoderTurnCommand extends AbstractEncoderDriveCommand {
    private double angle;
    private double timeOut = -1;

    public EncoderTurnCommand(DriveTrain driveTrain, double angle) {
        super(driveTrain);
        this.angle = angle;

    }

    public EncoderTurnCommand(DriveTrain driveTrain, double inches, double timeOut) {
        this(driveTrain, inches);
        this.timeOut = timeOut;
    }

    protected void initialize() {
        super.initialize();
        if (timeOut > 0 ) setTimeout(timeOut);
        ticks = toTicks(angle);
        allowableError = (int) SmartDashboardMap.ALLOWABLE_ERROR.getDouble();
        leftMaster.selectProfileSlot(1, 0);
        rightMaster.selectProfileSlot(1, 0);

        System.out.println("Starting command drive turn angle " + angle + " ticks " + ticks);
        leftMaster.setSelectedSensorPosition(0, 0, 0);
        rightMaster.setSelectedSensorPosition(0, 0, 0);

        driveTrain.invertMotors();
        System.out.println("**********Right Master Inverted: " + rightMaster.getInverted());
        System.out.println("**********Right Slave Inverted: " + rightSlave.getInverted());
        report(leftMaster, "Left Master");
        report(rightMaster, "Right Master");
        report(leftSlave, "Left Slave");
        report(rightSlave, "Right Slave");

    }

    protected void execute() {
        SmartDashboard.putNumber("Left Revolutions", leftMaster.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("Right Revolutions", rightMaster.getSelectedSensorPosition(0));
        leftMaster.set(ControlMode.Position, ticks);
        leftSlave.follow(leftMaster);
        rightMaster.set(ControlMode.Position, -ticks);
        rightSlave.follow(rightMaster);

        if (printCounter == printInterval) {
            reportExecute(leftMaster, "Left Master");
            reportExecute(rightMaster, "Right Master");
            System.out.println();
            printCounter = 0;
        } else {
            printCounter++;
        }

    }

    protected double toTicks(double angle) {
        return super.toTicks((angle / 360) * (RobotMap.TURN_DIAMETER * Math.PI));
    }

    @Override
    protected boolean isFinished() {
        return super.isFinished() || (timeOut > 0 && isTimedOut());
    }
}
