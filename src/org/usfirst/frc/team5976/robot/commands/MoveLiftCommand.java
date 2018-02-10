package org.usfirst.frc.team5976.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class MoveLiftCommand extends Command {
	private WPI_TalonSRX talon;
	private double setPoint;

	public MoveLiftCommand(LiftSubsystem liftSubsystem, double distanceToMove) {
		talon = liftSubsystem.getLiftTalon();
		setPoint = toTicks(distanceToMove);
		requires(liftSubsystem);
	}

	@Override
    protected void initialize() {
		LiftTalonInitialization.init(talon);
		System.out.println("Start " + getClass().getSimpleName() + " with set point: " + setPoint);
    }

    @Override
	protected void execute() {
		talon.set(ControlMode.Position, setPoint);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

    protected double toTicks(double inches) {
        return inches / (Math.PI * 2.5) * 4096;
    }
}
