package org.usfirst.frc.team5976.robot;

import edu.wpi.first.wpilibj.DriverStation;

public class GameDataAccess {
	
	public static GameData getGameData(){
		return new GameData(DriverStation.getInstance().getGameSpecificMessage());
	}
}
