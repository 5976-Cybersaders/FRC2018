package org.usfirst.frc.team5976.robot.subsystems;

import org.usfirst.frc.team5976.robot.commands.PneumaticsOff;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PneumaticsTester extends Subsystem{
	private DoubleSolenoid doubleSolenoid;
	private Compressor compressor;
	
	public PneumaticsTester(){
		compressor = new Compressor();
		compressor.setClosedLoopControl(true);
		doubleSolenoid = new DoubleSolenoid(0,1);
	}
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new PneumaticsOff(this));
		
	}
	
	public DoubleSolenoid getDoubleSolenoid(){
		return doubleSolenoid;
	}
	
	public Compressor getCompressor(){
		return compressor;
	}
}
