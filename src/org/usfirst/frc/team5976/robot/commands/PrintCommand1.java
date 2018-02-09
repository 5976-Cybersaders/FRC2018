package org.usfirst.frc.team5976.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import org.usfirst.frc.team5976.robot.OI;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class PrintCommand1 extends Command {
	
	private long timeMS;
	private long t0;
	private OI oi;
	private XboxController controller;
	private double raiseLift, lowerLift;

	
	public PrintCommand1(OI oi){
		this.oi = oi;
		controller = oi.getDriveController();
	}
	
	protected void initialize(){
		super.initialize();
		System.out.println("INIT " + getClass().getSimpleName());
	}
	@Override
	protected void execute(){

        raiseLift = controller.getTriggerAxis(GenericHID.Hand.kLeft);
        lowerLift = controller.getTriggerAxis(GenericHID.Hand.kRight);
		System.out.println("Lower : " + lowerLift + "\nRaise: " + raiseLift);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}	
}
