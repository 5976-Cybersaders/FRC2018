package org.usfirst.frc.team5976.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class PrintCommand2 extends Command{

	private long timeMS;
	private long t0;
	
	public PrintCommand2(long timeMS){
		this.timeMS = timeMS;
	}
	
	protected void initialize(){
		super.initialize();
		t0 = System.currentTimeMillis();
		System.out.println("INIT " + getClass().getSimpleName());
	}
	
	protected void execute(){
		System.out.println("Running Command 2");
	}
	
	@Override
	protected boolean isFinished() {
		return System.currentTimeMillis() - t0 >= timeMS;
	}	
}
