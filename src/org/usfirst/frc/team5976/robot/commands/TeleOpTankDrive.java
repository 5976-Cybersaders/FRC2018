package org.usfirst.frc.team5976.robot.commands;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

public class TeleOpTankDrive extends Command {
	private WPI_TalonSRX leftMaster, leftSlave, rightMaster, rightSlave;
	SpeedControllerGroup leftSide, rightSide;
	private XboxController controller;
	
	private static double expoFactor = 0.2;
	
	public TeleOpTankDrive(XboxController controller, DriveTrain driveTrain) {
		this.controller = controller;
		leftMaster = driveTrain.getLeftMaster();
		leftSlave = driveTrain.getLeftSlave();
		rightMaster = driveTrain.getRightMaster();
		rightSlave = driveTrain.getRightSlave();
		leftSide = new SpeedControllerGroup(leftMaster, leftSlave);
		rightSide = new SpeedControllerGroup(rightMaster, rightSlave);
		requires(driveTrain);
	}
	
	@Override
	protected void initialize() {
		leftMaster.setInverted(true);
		leftSlave.setInverted(true);
		initTalon(leftMaster);
		initTalon(rightMaster);
	}
	
	@Override
	protected void execute() {
		drive(-controller.getY(Hand.kLeft), -controller.getY(Hand.kRight));
	}
	
	private void drive(double leftSpeed, double rightSpeed) {
//		leftSide.set(leftSpeed);
//		rightSide.set(rightSpeed);
		leftSide.set(adjustSpeed(leftSpeed));
		rightSide.set(adjustSpeed(rightSpeed));
	}
	
	public double adjustSpeed(double d){
		if (Math.abs(d) < 0.02) return 0;
    	double speed = Math.signum(d) * Math.pow(Math.abs(d), Math.pow(4, expoFactor));
    	//System.out.println("in: " + d + " out: " + speed);
    	//if (speedReduced) return speed * 0.5;
    	return speed;
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
