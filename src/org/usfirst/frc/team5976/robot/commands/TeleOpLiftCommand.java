package org.usfirst.frc.team5976.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

public class TeleOpLiftCommand extends Command {
    private XboxController xboxController;
    private WPI_TalonSRX talon;
    private double setPoint;
    private boolean wasConrolled;

    private double deadBand = 0.15;

    public TeleOpLiftCommand(XboxController xboxController, LiftSubsystem liftSubsystem) {
        this.xboxController = xboxController;
        talon = liftSubsystem.getLiftTalon();
    }

    @Override
    protected void initialize() {
        LiftTalonInitialization.init(talon);
    }

    @Override
    protected void execute() {
        double input = -xboxController.getY(GenericHID.Hand.kLeft);
        if (isOperatorControl(input)) {
            talon.set(ControlMode.PercentOutput, input);
            wasConrolled = true;
        }
        else {
            if (wasConrolled) {
                wasConrolled = false;
                setPoint = talon.getSelectedSensorPosition(0);
            }
            talon.set(ControlMode.Position, setPoint);
        }
    }

    protected boolean isOperatorControl(double input) {
        return Math.abs(input) > deadBand;
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
