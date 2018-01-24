package org.usfirst.frc.team5976.robot;

import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.DriverStation;

public class GameDataAccess {

	public static GameData getGameData(DriveTrain driveTrain) {
		return new GameData(driveTrain, DriverStation.getInstance().getGameSpecificMessage());
	}
}
