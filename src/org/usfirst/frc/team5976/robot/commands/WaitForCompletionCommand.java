package org.usfirst.frc.team5976.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import java.util.concurrent.atomic.AtomicBoolean;

public class WaitForCompletionCommand extends Command {

    private AtomicBoolean atomicBoolean;

    public WaitForCompletionCommand(AtomicBoolean atomicBoolean) {
        this.atomicBoolean = atomicBoolean;
    }

    @Override
    protected boolean isFinished() {
        return atomicBoolean.get();
    }
}
