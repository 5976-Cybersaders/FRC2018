package org.usfirst.frc.team5976.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//DriveTrain Motors CAN IDs
	//Master motors are the front motor
	public static final int LEFT_MASTER = 2;
	public static final int LEFT_SLAVE = 1;
	public static final int RIGHT_MASTER = 4;
	public static final int RIGHT_SLAVE = 3;
	
	//Talon Values
	public static final double PEAK_VOLTAGE = 2.5;
	public static final double PEAK_VOLTAGE_TELEOP = 6.0;
	public static final int RAMP_RATE = 0;
	public static final boolean BRAKE_MODE = false;

	//Ramp subsystem
	public static final int RAMP_SERVO = 1;
	
	//Grabber subsystem
	public static final int LINEAR_ACTUATOR_LEFT_SERVO = 9;
    public static final int LINEAR_ACTUATOR_RIGHT_SERVO = 8;

	
	public static final double TURN_DIAMETER = 23.5;
}