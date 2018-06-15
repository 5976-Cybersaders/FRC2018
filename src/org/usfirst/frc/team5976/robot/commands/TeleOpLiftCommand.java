package org.usfirst.frc.team5976.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

public class TeleOpLiftCommand extends Command {
    private XboxController xboxController;
    private WPI_TalonSRX talon;

    private double deadBand = 0.15;
    private int maxEncoderPosition = 20000, minEncoderPosition = 0; //TODO: Find max encoder value

    public TeleOpLiftCommand(LiftSubsystem liftSubsystem, XboxController xboxController) {
        this.xboxController = xboxController;
        talon = liftSubsystem.getLiftTalon();
        requires(liftSubsystem);
    }

    @Override
    protected void initialize() {
        LiftTalonInitializationCommand.initTalon(talon);
        talon.selectProfileSlot(1, 0);
    }

    @Override
    protected void execute() {
        // negative is intentional
        double input = -xboxController.getY(GenericHID.Hand.kLeft);
//        System.out.println("Lift sensor position: " + talon.getSelectedSensorPosition(0));
        if (isOperatorControl(input))
            talon.set(ControlMode.PercentOutput, getInput());
        else
            talon.set(ControlMode.PercentOutput, 0);
    }

    private double getInput() {
        double input = -xboxController.getY(GenericHID.Hand.kLeft);
        if (xboxController.getBumper(Hand.kRight)) return input;
        if (isOperatorControl(input)) {
            double encoderPosition = talon.getSelectedSensorPosition(0);
            boolean maxTest = encoderPosition >= maxEncoderPosition && input > 0;
            boolean minTest = encoderPosition <= minEncoderPosition && input < 0;
            if (maxTest || minTest) return 0;
            if (xboxController.getAButton()) input /= 10;
            return input;
        }

        return 0;
    }

    private boolean isOperatorControl(double input) {
        return Math.abs(input) > deadBand;
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
