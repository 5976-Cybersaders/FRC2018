package org.usfirst.frc.team5976.robot;

public interface GrabberController {

    boolean isAtLimit(boolean isGrabbing);
    
    void actuate(boolean isGrabbing);

}
