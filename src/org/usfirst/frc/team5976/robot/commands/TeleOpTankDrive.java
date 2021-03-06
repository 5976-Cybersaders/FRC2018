package org.usfirst.frc.team5976.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

public class TeleOpTankDrive extends Command {
    private static double expoFactor = 0.2;
    private DriveTrain driveTrain;
    private WPI_TalonSRX leftMaster;
    private WPI_TalonSRX rightMaster;
    private SpeedControllerGroup leftSide, rightSide;
    private XboxController controller;
    private int count, interval;

    public TeleOpTankDrive(XboxController controller, DriveTrain driveTrain) {
        this.controller = controller;
        this.driveTrain = driveTrain;
        leftMaster = driveTrain.getLeftMaster();
        WPI_TalonSRX leftSlave = driveTrain.getLeftSlave();
        rightMaster = driveTrain.getRightMaster();
        WPI_TalonSRX rightSlave = driveTrain.getRightSlave();
        leftSide = new SpeedControllerGroup(leftMaster, leftSlave);
        rightSide = new SpeedControllerGroup(rightMaster, rightSlave);
        requires(driveTrain);
        count = interval = 50;
    }

    @Override
    protected void initialize() {
        driveTrain.invertMotors();
        initTalon(leftMaster);
        initTalon(rightMaster);
    }

    @Override
    protected void execute() {
        // Negative below is intentional to reverse direction of joystick input.
        drive(-controller.getY(Hand.kLeft), -controller.getY(Hand.kRight));
        reportExecute();
    }

    private void reportExecute() {
        if (count++ >= interval) {
            ReportHelper.reportTeleOp(leftMaster, "Left Master");
            ReportHelper.reportTeleOp(rightMaster, "Right Master");
            System.out.println();
            count = 0;
        }
    }

    private void drive(double leftSpeed, double rightSpeed) {
        leftSide.set(adjustSpeed(leftSpeed));
        rightSide.set(adjustSpeed(rightSpeed));
    }

    private double adjustSpeed(double d) {
        if (Math.abs(d) < 0.03) return 0;
        return Math.signum(d) * Math.pow(Math.abs(d), Math.pow(4, expoFactor));
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
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void interrupted() {
        end();
    }


}
