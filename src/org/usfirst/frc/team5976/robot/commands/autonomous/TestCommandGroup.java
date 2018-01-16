package org.usfirst.frc.team5976.robot.commands.autonomous;

import org.usfirst.frc.team5976.robot.commands.PrintCommand1;
import org.usfirst.frc.team5976.robot.commands.PrintCommand2;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestCommandGroup extends CommandGroup{
	public TestCommandGroup(){
		addParallel(new PrintCommand1(5000));
		addParallel(new PrintCommand2(5000));		
		
	}
	

}