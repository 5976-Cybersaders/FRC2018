package org.usfirst.frc.team5976.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class MoveLiftCommand extends Command {

	private LiftSubsystem lift;
	private WPI_TalonSRX talon;
	
	public MoveLiftCommand(LiftSubsystem lift) {
		this.lift = lift;
		talon = lift.getLiftTalon();
		//initialize(talon);
		requires(lift);
	}


    protected void initialize(WPI_TalonSRX talon) {
        talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
        talon.setSelectedSensorPosition(0, 0, 0);
        talon.set(ControlMode.Position, 0);

//        double peakOutput = SmartDashboardMap.PEAK_VOLTAGE.getDouble();
//        double nominalOutput = SmartDashboardMap.NOMINAL_VOLTAGE.getDouble();

        //talon.selectProfileSlot(1, 0);
//        talon.configPeakOutputForward(peakOutput, 0);  //  TODO establish constant for lifter talon speed
//        talon.configPeakOutputReverse(-peakOutput, 0);
//        talon.configNominalOutputForward(nominalOutput, 0);
//        talon.configNominalOutputReverse(-nominalOutput, 0);
//        talon.configClosedloopRamp(SmartDashboardMap.RAMP_RATE.getDouble(), 0);


        // Profile 0 For Both
        //TODO establish constant for allowable error for lift talon
//        System.out.println("Setting allowable error to " + SmartDashboardMap.ALLOWABLE_ERROR.getDouble());
//        talon.configAllowableClosedloopError(0, SmartDashboardMap.ALLOWABLE_ERROR.getIntValue(), 0);

//        talon.configNominalOutputForward(nominalOutput, 0);
//        talon.configNominalOutputReverse(-nominalOutput, 0);

    }

	@Override
	protected boolean isFinished() {
		
		return true;
	}
	
	protected void execute() {

		
	}

	
	
}
