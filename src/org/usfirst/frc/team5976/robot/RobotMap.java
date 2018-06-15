package org.usfirst.frc.team5976.robot;

public class RobotMap {
    //DriveTrain Motor CAN IDs
    //Master motors are the front motor
    public static final int LEFT_MASTER_TALON_ID = 2;
    public static final int LEFT_SLAVE_TALON_ID = 1;
    public static final int RIGHT_MASTER_TALON_ID = 4;
    public static final int RIGHT_SLAVE_TALON_ID = 3;

    //Lift subsystem
    public static final int LIFT_TALON_ID = 5;

    //Grabber subsystem
    public static final int GRABBER_SERVO = 9;
    public static final double RELEASE_TIME_OUT = 1.5;


    public static final double TURN_DIAMETER = 49; //23.5 || 49
}