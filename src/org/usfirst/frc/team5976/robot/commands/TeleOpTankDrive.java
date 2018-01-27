package org.usfirst.frc.team5976.robot.commands;

import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class TeleOpTankDrive extends Command {
	DifferentialDrive robotDrive;
	DriveTrain driveTrain;
	XboxController driveController;
	private static double expoFactor = 0.2;
	private PowerDistributionPanel pdp;
	private boolean speedReduced;
	private WPI_TalonSRX leftMaster, rightMaster;
	
	public TeleOpTankDrive(XboxController driveController, DriveTrain driveTrain) {
		this.driveTrain = driveTrain;
		this.driveController = driveController;
		pdp = driveTrain.getPDP();
		speedReduced = false;
		
		leftMaster = driveTrain.getLeftMaster();
		rightMaster = driveTrain.getRightMaster();
		requires(driveTrain);
	}
	
	public double adjustSpeed(double d){
    	double speed = Math.signum(d) * Math.pow(Math.abs(d), Math.pow(4, expoFactor));
    	//System.out.println("in: " + d + " out: " + speed);
    	if (speedReduced) return speed * 0.5;
    	return speed;
    }
	
	@Override
	protected void initialize() {
		leftMaster.setInverted(false);
		leftMaster.setInverted(false);
        initTalon(leftMaster);
        initTalon(rightMaster);
        SpeedControllerGroup leftSide = new SpeedControllerGroup(leftMaster, driveTrain.getLeftSlave());
        SpeedControllerGroup rightSide = new SpeedControllerGroup(rightMaster, driveTrain.getRightSlave());
		robotDrive = new DifferentialDrive(leftSide, rightSide);
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
		//System.out.println("Left Master Encoder Position " + driveTrain.getLeftMaster().getEncPosition() + " Right Master Encoder Position " + driveTrain.getRightMaster().getEncPosition());
		//System.out.println("Current Left: " + ((pdp.getCurrent(2) + pdp.getCurrent(3))) + " Current Right: " + ((pdp.getCurrent(12) + pdp.getCurrent(13))) + "  Rio: "+pdp.getCurrent(8) + " Total Current:  " +pdp.getTotalCurrent());
		//reportCurrent("Left Master Current", RobotMap.LEFT_MASTER_PDP);
		//reportCurrent("Left Slave Current", RobotMap.LEFT_SLAVE_PDP);
		//reportCurrent("Right Master Current", RobotMap.RIGHT_MASTER_PDP);
		//reportCurrent("Right Slave Current", RobotMap.RIGHT_SLAVE_PDP);
		if (driveController.getBumper(Hand.kLeft)) {
			speedReduced = true;
		}
		else speedReduced = false;
		if (driveController.getBumper(Hand.kRight))
			robotDrive.tankDrive(adjustSpeed(driveController.getY(Hand.kLeft)), adjustSpeed(driveController.getY(Hand.kLeft)));
		else robotDrive.tankDrive(adjustSpeed(driveController.getY(Hand.kLeft)), adjustSpeed(driveController.getY(Hand.kRight)));
		//SmartDashboard.putNumber("Closed Loop Ramp Rate:", driveTrain.getLeftMaster().getCloseLoopRampRate());
	}
	
	//private void reportCurrent(String label, int channel) {
	//	SmartDashboard.putNumber(label, pdp.getCurrent(channel));
	//}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		robotDrive.tankDrive(0, 0);
		robotDrive = null;
	}
	
	@Override
	protected void interrupted() {
		end();
	}
}
