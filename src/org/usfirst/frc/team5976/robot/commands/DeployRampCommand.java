package org.usfirst.frc.team5976.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5976.robot.subsystems.RampSubsystem;

import java.sql.Driver;

public class DeployRampCommand extends Command {
    private Servo leftServo, rightServo;
    private DriverStation driverStation;

    public DeployRampCommand(RampSubsystem rampSubsystem) {
        leftServo = rampSubsystem.getLeftServo();
        rightServo = rampSubsystem.getRightServo();
        driverStation = DriverStation.getInstance();
    }

    @Override
    protected void execute() {
        if (driverStation.isOperatorControl() && driverStation.getMatchTime() < 30) {
            leftServo.set(90);  //  TODO: find actual servo position value
            rightServo.set(90);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
