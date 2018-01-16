package org.usfirst.frc.team5976.robot;

import org.usfirst.frc.team5976.robot.commands.CompressorOnCommand;
import org.usfirst.frc.team5976.robot.commands.TeleOpMoveSolenoidCommand;
import org.usfirst.frc.team5976.robot.subsystems.PneumaticsTester;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	private XBoxButton solenoidForward, solenoidReverse, compressorOn;
	
	private final XboxController DRIVE_CONTROLLER = new XboxController(0);
	
	public OI(PneumaticsTester pneumatic){
		solenoidForward = new XBoxButton(DRIVE_CONTROLLER, 5);
		solenoidReverse = new XBoxButton(DRIVE_CONTROLLER, 6);
		compressorOn = new XBoxButton(DRIVE_CONTROLLER, 4);
		
		solenoidForward.whenPressed(new TeleOpMoveSolenoidCommand(pneumatic, DoubleSolenoid.Value.kForward));
		solenoidReverse.whenPressed(new TeleOpMoveSolenoidCommand(pneumatic, DoubleSolenoid.Value.kReverse));	
		compressorOn.whenPressed(new CompressorOnCommand(pneumatic.getCompressor()));
	}
	
	public OI(){
		
	}
	
	public XboxController getDriveController() {
		return DRIVE_CONTROLLER;
	}
}
