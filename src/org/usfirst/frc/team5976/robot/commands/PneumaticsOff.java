package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.PneumaticsTester;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class PneumaticsOff extends Command{
	PneumaticsTester pneumatic;
	
	public PneumaticsOff(PneumaticsTester pneumatic){
		this.pneumatic = pneumatic;
		requires(pneumatic);
		
	}
	
	@Override
	protected void execute(){
		pneumatic.getDoubleSolenoid().set(DoubleSolenoid.Value.kOff);
		
	}
	@Override
	protected boolean isFinished() {
		return false;
	}
}
