package org.usfirst.frc.team5976.robot;

import org.usfirst.frc.team5976.robot.commands.autonomous.CrossLineCommandGroup;
import org.usfirst.frc.team5976.robot.commands.autonomous.DeliverScaleLeftCommandGroup;
import org.usfirst.frc.team5976.robot.commands.autonomous.DeliverScaleRightCommandGroup;
import org.usfirst.frc.team5976.robot.commands.autonomous.DeliverSwitchLeftCommandGroup;
import org.usfirst.frc.team5976.robot.commands.autonomous.DeliverSwitchRightCommandGroup;

import edu.wpi.first.wpilibj.command.Command;

public class GameData {
	private String data;
	private SmartValue position;

	public GameData(String data) {
		this.data = data;
		position = SmartDashboardMap.POSITION;
	}

	public boolean isAllianceSwitchLeft() {
		return data.charAt(0) == 'L';
	}
	
	public boolean isAllianceSwitchRight() {
		return !isAllianceSwitchLeft();
	}

	public boolean isScaleLeft() {
		return data.charAt(1) == 'L';
	}
	
	public boolean isScaleRight() {
		return !isScaleLeft();
	}

	public boolean isOpponentSwitchLeft() {
		return data.charAt(2) == 'L';
	}
	
	public boolean isOpponentSwitchRight() {
		return !isOpponentSwitchLeft();
	}

	public Command getCommand() {
		Command command;
		switch (position.getIntValue()) {
			case Positions.LEFT:
				command = getCommandLeft();
				break;
			case Positions.MIDDLE:
				command = getCommandMiddle();
				break;
			case Positions.RIGHT:
				command = getCommandRight();
				break;
			default:
				command = new CrossLineCommandGroup();
				break;
		}
		System.out.println("Running " + command.getClass().getSimpleName());
		return command;
	}

	private Command getCommandLeft() {
		if(isAllianceSwitchLeft()){
			return new DeliverSwitchLeftCommandGroup();
		}
		if(isScaleLeft()){
			return new DeliverScaleLeftCommandGroup();
		}
		return new CrossLineCommandGroup();
	}

	private Command getCommandMiddle() {
		return new CrossLineCommandGroup();
	}

	private Command getCommandRight() {
		if(isAllianceSwitchRight()){
			return new DeliverSwitchRightCommandGroup();
		}
		if(isScaleRight()){
			return new DeliverScaleRightCommandGroup();
		}
		return new CrossLineCommandGroup();
	}
	
	private static class Positions {
		static final int LEFT = 0;
		static final int MIDDLE = 1;
		static final int RIGHT = 2;
	}
}