package org.usfirst.frc.team5976.robot.commands;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Command;

public class CompressorOnCommand extends Command {
	private Compressor compressor;
	private boolean on;
	
	public CompressorOnCommand(Compressor compressor) {
		this.compressor = compressor;
		this.on = true;
	}

	@Override
	public void execute() {
		on = !on;
		System.out.println("Compressor " + on);
		compressor.setClosedLoopControl(on);
		
	}
	@Override
	protected boolean isFinished() {	
		return true;
	}
}
