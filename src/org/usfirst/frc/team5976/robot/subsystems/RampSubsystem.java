package org.usfirst.frc.team5976.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.commands.DeployRampCommand;

public class RampSubsystem extends Subsystem{

    private Servo leftServo, rightServo;

    public RampSubsystem() {
        leftServo = new Servo(RobotMap.LEFT_RAMP_SERVO);
        rightServo = new Servo(RobotMap.RIGHT_RAMP_SERVO);

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
