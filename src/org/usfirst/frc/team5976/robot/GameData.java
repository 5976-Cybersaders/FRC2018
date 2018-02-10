package org.usfirst.frc.team5976.robot;

import org.usfirst.frc.team5976.robot.commands.autonomous.CrossLineCommandGroup;
import org.usfirst.frc.team5976.robot.commands.autonomous.DeliverScaleLeftCommandGroup;
import org.usfirst.frc.team5976.robot.commands.autonomous.DeliverScaleRightCommandGroup;
import org.usfirst.frc.team5976.robot.commands.autonomous.DeliverSwitchLeftCommandGroup;
import org.usfirst.frc.team5976.robot.commands.autonomous.DeliverSwitchRightCommandGroup;
import org.usfirst.frc.team5976.robot.commands.autonomous.TestCommandGroup;

import edu.wpi.first.wpilibj.command.Command;

public class GameData {
	private String data;
	private SmartValue position;
	private Robot robot;

	public GameData(Robot robot, String data) {
		this.data = data;
		this.robot = robot;
		position = SmartDashboardMap.POSITION;
	}

	private boolean isAllianceSwitchLeft() {
		return data.charAt(0) == 'L';
	}

	private boolean isAllianceSwitchRight() {
		return !isAllianceSwitchLeft();
	}

	private boolean isScaleLeft() {
		return data.charAt(1) == 'L';
	}

	private boolean isScaleRight() {
		return !isScaleLeft();
	}

	public Command getCommand() {
		Command command;
		int position = getPosition();
		System.out.println("Position Value:" + position);
		switch (position) {
			case Positions.LEFT:
				command = getCommandLeft();
				break;
			case Positions.MIDDLE:
				command = getCommandMiddle();
				break;
			case Positions.RIGHT:
				command = getCommandRight();
				break;
			case Positions.TEST:
				command = new TestCommandGroup(robot);
				break;
			default:
				command = new CrossLineCommandGroup(robot);
				break;
		}
		System.out.println("Running " + command.getClass().getSimpleName());
		return command;
	}

	private Command getCommandLeft() {
		if(isAllianceSwitchLeft()){
			return new DeliverSwitchLeftCommandGroup(robot);
		}
		if(isScaleLeft()){
			return new DeliverScaleLeftCommandGroup(robot);
		}
		return new CrossLineCommandGroup(robot);
	}

	private Command getCommandMiddle() {
		return new CrossLineCommandGroup(robot);
	}

	private Command getCommandRight() {
		if(isAllianceSwitchRight()){
			return new DeliverSwitchRightCommandGroup(robot);
		}
		if(isScaleRight()){
			return new DeliverScaleRightCommandGroup(robot);
		}
		return new CrossLineCommandGroup(robot);
	}
	
	private int getPosition() {
		String stringPosition = position.getString().trim().toUpperCase();
		if (stringPosition.equals("LEFT") || stringPosition.equals("" + Positions.LEFT)) return Positions.LEFT;
		if (stringPosition.equals("RIGHT") || stringPosition.equals("" + Positions.RIGHT)) return Positions.RIGHT;
		if (stringPosition.equals("TEST") || stringPosition.equals("" + Positions.TEST)) return Positions.TEST;
		return Positions.MIDDLE;
	}
	
	private static class Positions {
		static final int LEFT = -1;
		static final int MIDDLE = 0;
		static final int RIGHT = 1;
		static final int TEST = 3;
	}
}
