package org.usfirst.frc.team5976.robot.subsystems;

import org.usfirst.frc.team5976.robot.GearRackGrabberController;
import org.usfirst.frc.team5976.robot.GrabberController;
import org.usfirst.frc.team5976.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GrabberSubsystem extends Subsystem {
	private GrabberController grabberController;

	public GrabberSubsystem() {
		grabberController = new GearRackGrabberController();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public GrabberController getGrabberController() {
		return grabberController;
	}
}
