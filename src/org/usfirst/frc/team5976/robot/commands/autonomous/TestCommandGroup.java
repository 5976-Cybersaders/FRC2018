package org.usfirst.frc.team5976.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5976.robot.Robot;
import org.usfirst.frc.team5976.robot.commands.EncoderInitCommand;
import org.usfirst.frc.team5976.robot.commands.EncoderTurnCommand;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

public class TestCommandGroup extends CommandGroup {
    public TestCommandGroup(Robot robot) {
        addSequential(new AutonomousInitializationCommandGroup(robot));
    }


}
