package org.usfirst.frc.team5976.robot;

import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;

import edu.wpi.first.wpilibj.DriverStation;

public class GameDataAccess {

	public static GameData getGameData(Robot robot) {
		return new GameData(robot, DriverStation.getInstance().getGameSpecificMessage());
	}
}
