package org.usfirst.frc.team5976.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.usfirst.frc.team5976.robot.commands.autonomous.TestCommandGroup;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.LiftSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    private OI oi;
    private DriveTrain driveTrain;
    private LiftSubsystem liftSubsystem;
    private GrabberSubsystem grabber;
    private Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        SmartDashboardMap.reportAll();
        grabber = new GrabberSubsystem();
        oi = new OI(this);
        driveTrain = new DriveTrain(oi);
        liftSubsystem = new LiftSubsystem(oi);
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = GameDataAccess.getGameData(this).getCommand();
        if (autonomousCommand != null)
            autonomousCommand.start();
    }


    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        driveTrain.updateDefaultCommandForTeleOp();
        liftSubsystem.initDefaultCommandForTeleOp();
        if (autonomousCommand != null)
            autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
        //LiveWindow.run();
    }

    public DriveTrain getDriveTrain() {
        return driveTrain;
    }

    public LiftSubsystem getLiftSubsystem() {
        return liftSubsystem;
    }

    public GrabberSubsystem getGrabberSubsystem() {
        return grabber;
    }
}
