package org.usfirst.frc.team5976.robot.subsystems;

import org.usfirst.frc.team5976.robot.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GrabberSubsystem extends Subsystem {
	private Servo leftServo, rightServo;

	public GrabberSubsystem() {
		leftServo = new Servo(RobotMap.GRABBER_LEFT_SERVO_ID);
        rightServo = new Servo(RobotMap.GRABBER_RIGHT_SERVO_ID);
	}

	@Override
	protected void initDefaultCommand() {
		
	}

	public Servo getLeftServo() {
		return leftServo;
	}

	public Servo getRightServo() {
		return rightServo;
	}
	
	
}
