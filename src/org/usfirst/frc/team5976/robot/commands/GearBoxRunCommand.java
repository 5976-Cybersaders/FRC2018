package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Command;

public class GearBoxRunCommand extends Command{
	private DriveTrain driveTrain;
	private WPI_TalonSRX leftMaster, leftSlave, rightMaster, rightSlave;
	private long t0;
	private long timeMS;
	private double speed;
	
	public GearBoxRunCommand(DriveTrain driveTrain, long timeMS, double speed){
		this.driveTrain = driveTrain;
		this.timeMS = timeMS;
		this.speed = speed;
		
		leftMaster = driveTrain.getLeftMaster();
		leftSlave = driveTrain.getLeftSlave();
		rightMaster = driveTrain.getRightMaster();
		rightSlave = driveTrain.getRightSlave();
	}
	
	public GearBoxRunCommand(long timeMS, double speed){
		this.timeMS = timeMS;
		this.speed = speed;
	}

	protected void initialize(){
		super.initialize();
		t0 = System.currentTimeMillis();
		System.out.println("INIT " + getClass().getSimpleName());
	}
	
	protected void execute(){
		//driveTrain.getRobotDrive().tankDrive(speed, speed);
		//driveTrain.getLeftMaster().getBusVoltage();
		leftMaster.set(speed);
		leftSlave.set(speed);
		leftMaster.getBusVoltage();
		leftSlave.getBusVoltage();
		System.out.println(System.currentTimeMillis() - t0);
	}
	
	@Override
	protected boolean isFinished() {   
		leftMaster.getBusVoltage();
		leftSlave.getBusVoltage();
		if (System.currentTimeMillis() - t0 >= timeMS){
			leftMaster.set(0);
			leftSlave.set(0);
			return true;
		}
		return false;
	}

}
