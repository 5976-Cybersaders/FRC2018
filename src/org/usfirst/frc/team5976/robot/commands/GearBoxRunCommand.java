package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class GearBoxRunCommand extends Command{
	private DriveTrain driveTrain;
	private long t0;
	private long timeMS;
	private double speed;
	
	public GearBoxRunCommand(DriveTrain driveTrain, long timeMS, double speed){
		this.driveTrain = driveTrain;
		this.timeMS = timeMS;
		this.speed = speed;
		
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
		driveTrain.leftMaster.set(speed);
		driveTrain.leftSlave.set(speed);
		driveTrain.leftMaster.getBusVoltage();
		driveTrain.leftSlave.getBusVoltage();
		System.out.println(System.currentTimeMillis() - t0);
	}
	
	@Override
	protected boolean isFinished() {   
		driveTrain.leftMaster.getBusVoltage();
		driveTrain.leftSlave.getBusVoltage();
		if (System.currentTimeMillis() - t0 >= timeMS){
			driveTrain.leftMaster.set(0);
			driveTrain.leftSlave.set(0);
			return true;
		}
		return false;
	}

}
