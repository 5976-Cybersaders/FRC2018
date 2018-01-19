package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Command;

public class EncoderInit extends Command {
	private WPI_TalonSRX leftMaster, rightMaster, leftSlave, rightSlave;
	private boolean inversion = true;

	public EncoderInit(DriveTrain driveTrain) {
		leftMaster = driveTrain.getLeftMaster();
		leftSlave = driveTrain.getLeftSlave();
		rightMaster = driveTrain.getRightMaster();
		rightSlave = driveTrain.getRightSlave();
		requires(driveTrain);
	}

	protected void initialize() {
		initMaster(leftMaster, -1);
		initMaster(rightMaster, 1);
		// Sets output
		leftMaster.setSensorPhase(inversion);
		// !Sets talon colors
		// Sets something
		leftMaster.setInverted(inversion);
	}

	protected void initMaster(WPI_TalonSRX talon, int side) {
		// talon.setControlMode(ControlMode.Position);
		// talon.set(0);
		talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		// talon.setEncPosition(0);

		// Set values here
		// Profile 1 for Both

		talon.selectProfileSlot(1, 0);
		talon.configClosedloopRamp(SmartDashboardMap.RAMP_RATE.getDouble(), 0);
		if (side == -1) {// Left
			talon.config_kP(0, SmartDashboardMap.kPRL.getDouble(), 0);
			talon.config_kI(0, SmartDashboardMap.kIRL.getDouble(), 0);
			talon.config_kD(0, SmartDashboardMap.kDRL.getDouble(), 0);

			talon.selectProfileSlot(1, 0);
			talon.config_kP(0, SmartDashboardMap.kPFL.getDouble(), 0);
			talon.config_kI(0, SmartDashboardMap.kIFL.getDouble(), 0);
			talon.config_kD(0, SmartDashboardMap.kDFL.getDouble(), 0);

		} else {
			talon.config_kP(0, SmartDashboardMap.kPRR.getDouble(), 0);
			talon.config_kI(0, SmartDashboardMap.kIRR.getDouble(), 0);
			talon.config_kD(0, SmartDashboardMap.kDRR.getDouble(), 0);

			talon.selectProfileSlot(0, 0);
			talon.config_kP(0, SmartDashboardMap.kPFR.getDouble(), 0);
			talon.config_kI(0, SmartDashboardMap.kIFR.getDouble(), 0);
			talon.config_kD(0, SmartDashboardMap.kDFR.getDouble(), 0);

		}
		// Profile 0 For Both
		talon.configPeakOutputForward(SmartDashboardMap.PEAK_VOLTAGE.getDouble(), 0);
		talon.configPeakOutputReverse(-SmartDashboardMap.PEAK_VOLTAGE.getDouble(), 0);
		System.out.println("Setting allowable error to " + SmartDashboardMap.ALLOWABLE_ERROR.getDouble());
		talon.configAllowableClosedloopError(0, SmartDashboardMap.ALLOWABLE_ERROR.getIntValue(), 0);

		talon.configNominalOutputForward(SmartDashboardMap.NOMINAL_VOLTAGE.getDouble(), 0);
		talon.configNominalOutputReverse(-SmartDashboardMap.NOMINAL_VOLTAGE.getDouble(), 0);

	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
