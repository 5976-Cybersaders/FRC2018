package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.SmartValue;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EncoderDriveStraight extends AbstractEncoderDriveCommand {

	private double inches;
	private SmartValue smartValue;

	public EncoderDriveStraight(DriveTrain driveTrain, double inches) {
		super(driveTrain);
		this.inches = inches;
	}

	public EncoderDriveStraight(DriveTrain driveTrain, SmartValue smartValue) {
		super(driveTrain);
		this.smartValue = smartValue;
	}

	protected void initialize() {
		super.initialize();
		if (smartValue != null)
			inches = smartValue.getDouble();
		revolutions = toRevolutions(inches);
		allowableError = (int)SmartDashboardMap.ALLOWABLE_ERROR.getDouble();
		if (revolutions > 0) {
			leftMaster.selectProfileSlot(0, 0);
			rightMaster.selectProfileSlot(0, 0);
		}
		else {
			leftMaster.selectProfileSlot(1, 0);
			rightMaster.selectProfileSlot(1, 0);
		}
		System.out.println("Starting command drive straight inches " + inches + " revolutions " + revolutions);
		leftMaster.set(ControlMode.Position, 0);
		rightMaster.set(ControlMode.Position, 0);
		report(leftMaster);
		report(rightMaster);
		report(leftSlave);
		report(rightSlave);

	}

	protected void execute() {
		SmartDashboard.putNumber("Left Revolutions", leftMaster.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Right Revolutions", rightMaster.getSelectedSensorPosition(0));
		leftMaster.set(-revolutions);
		leftSlave.set(leftMaster.getDeviceID());
		rightMaster.set(revolutions);
		rightSlave.set(rightMaster.getDeviceID());

		if (printCounter == printInterval) {
			reportExecute(leftMaster, "Left Master", RobotMap.LEFT_MASTER_PDP);
			// reportExecute(leftSlave, "Left Slave", RobotMap.LEFT_SLAVE_PDP);
			reportExecute(rightMaster, "Right Master", RobotMap.RIGHT_MASTER_PDP);
			// reportExecute(rightSlave, "Right Slave", RobotMap.RIGHT_SLAVE_PDP);
			System.out.println();
			printCounter = 0;
		} else {
			printCounter++;
		}

	}

}
