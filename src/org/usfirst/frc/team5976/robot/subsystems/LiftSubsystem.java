package org.usfirst.frc.team5976.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystem extends Subsystem{

	private WPI_TalonSRX talon;

	public LiftSubsystem(){
	//	this.talon = talon;
	}
	
	@Override
	protected void initDefaultCommand() {
		
		
	}


    public WPI_TalonSRX getLiftTalon() {
        return talon;
    }
}
