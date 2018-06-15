package org.usfirst.frc.team5976.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5976.robot.SmartValue;

public class DoNothingCommand extends Command {
    private double delaySeconds;
    private SmartValue smartDelay;

    public DoNothingCommand(SmartValue smartDelay) {
        this.smartDelay = smartDelay;
    }

    public DoNothingCommand(double delay) {
        delaySeconds = delay;
    }

    @Override
    protected void initialize() {
        if (smartDelay != null) delaySeconds = smartDelay.getDouble();
        setTimeout(delaySeconds);
        System.out.println("Delay: " + delaySeconds);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
}
