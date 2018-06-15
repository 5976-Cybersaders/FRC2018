package org.usfirst.frc.team5976.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5976.robot.RobotMap;

public class GrabberSubsystem extends Subsystem {
    private Servo servo;

    public GrabberSubsystem() {
        servo = new Servo(RobotMap.GRABBER_SERVO);
    }

    @Override
    protected void initDefaultCommand() {

    }

    public Servo getServo() {
        return servo;
    }
}
