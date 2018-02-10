package org.usfirst.frc.team5976.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.XboxController;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GrabberCommand extends Command {
    private int counter = 0, printInterval = 10;
    private Servo leftServo, rightServo;
    private int action;
    private double timeOutSeconds = -1;
    private XboxController xboxController;
	
	public GrabberCommand(GrabberSubsystem grabber, int action, XboxController xboxController) {
		leftServo = grabber.getLeftServo();
        rightServo = grabber.getRightServo();
        this.action = action;
        this.xboxController = xboxController;
		requires(grabber);
	}

	public GrabberCommand(GrabberSubsystem grabberSubsystem, int action, double timeOutSeconds){
	    this(grabberSubsystem, action, null);
        System.out.println("Starting " + getClass().getSimpleName() + "\nTimeout: " + timeOutSeconds + " seconds");
	    this.timeOutSeconds = timeOutSeconds;
    }

	@Override
    protected void initialize() {
	    System.out.println("Initializing " + getClass().getSimpleName() + "with action # " + action);
        if(timeOutSeconds > 0) setTimeout(timeOutSeconds);
    }

	@Override
    protected void execute() {
	    double setPoint = getSetPoint();
	    leftServo.setPosition(setPoint);
        rightServo.setPosition(setPoint);
	    
	    if (counter++ % printInterval == 0) {
            System.out.println("Running " + getClass().getSimpleName() + " with action # " + action + " and set point " + setPoint);
            counter = 0;
        }
    }
	
	@Override
	protected boolean isFinished() {
	    if (timeOutSeconds > 0) return isTimedOut();
	    return false;
	}
	
	@Override
	protected void end() {
		super.end();
		System.out.println("END " + getClass().getSimpleName());
	}

	private double getSetPoint(){
	    double baseSetPoint, adjustment = 0;
	    switch(action){
            case 1:
                baseSetPoint = SmartDashboardMap.GRABBER_POSITION_1.getDouble();
                break;
            case 2:
                baseSetPoint = SmartDashboardMap.GRABBER_POSITION_2.getDouble();
                break;
            default:
                baseSetPoint = .2;
                break;
        }
        if(action == 0)adjustment = 0;
	    else if (xboxController != null){
	        adjustment = xboxController.getTriggerAxis(GenericHID.Hand.kRight) - xboxController.getTriggerAxis(GenericHID.Hand.kLeft);
	        adjustment *= SmartDashboardMap.GRABBER_RANGE.getDouble();
        }
	    System.out.println("Grabbing to " + baseSetPoint + " with adjustment " + adjustment + " = " + (baseSetPoint + adjustment));
        return baseSetPoint + adjustment;
    }

}
