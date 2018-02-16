package org.usfirst.frc.team5976.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DriverStation;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

import java.util.concurrent.atomic.AtomicBoolean;

public class MoveLiftCommand extends Command {
	private WPI_TalonSRX talon;
	private double setPoint;
	private AtomicBoolean isAtSetPoint;
	private int counter = 0;

	public MoveLiftCommand(LiftSubsystem liftSubsystem, double distanceToMove, AtomicBoolean isAtSetPoint) {
		talon = liftSubsystem.getLiftTalon();
		setPoint = toTicks(distanceToMove);
		this.isAtSetPoint = isAtSetPoint;
		requires(liftSubsystem);
	}
	
	public MoveLiftCommand(LiftSubsystem liftSubsystem, int setPoint) {
		talon = liftSubsystem.getLiftTalon();
		this.setPoint = setPoint;
		this.isAtSetPoint = new AtomicBoolean(false);
		requires(liftSubsystem);
	}

	@Override
    protected void initialize() {
	    counter = 0;
	    isAtSetPoint.set(false);
		System.out.println("Start " + getClass().getSimpleName() + " with set point: " + setPoint);
    }

    @Override
	protected void execute() {
// 		  talon.set(ControlMode.Position, setPoint);
//        counter++;
//        if (counter > 50 && Math.abs(talon.getSelectedSensorPosition(0) - setPoint) < SmartDashboardMap.LIFT_ALLOWABLE_ERROR.getDouble()){
//            atomicBoolean.set(true);
//        }
        if (counter++ > 200) isAtSetPoint.set(true);
	}

	@Override
	protected boolean isFinished() {
		return DriverStation.getInstance().isOperatorControl();
	}

    protected double toTicks(double inches) {
        return inches / (Math.PI * 2.5) * 4096;
    }
}
