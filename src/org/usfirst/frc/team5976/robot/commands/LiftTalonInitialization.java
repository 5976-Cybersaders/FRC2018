package org.usfirst.frc.team5976.robot.commands;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.usfirst.frc.team5976.robot.SmartDashboardMap;

public class LiftTalonInitialization {
    public static void init(WPI_TalonSRX talon) {
        talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
        talon.setSelectedSensorPosition(0, 0, 0);

        talon.config_kP(0, SmartDashboardMap.LIFT_kP.getDouble(), 0);
        talon.config_kI(0, SmartDashboardMap.LIFT_kI.getDouble(), 0);
        talon.config_kD(0, SmartDashboardMap.LIFT_kD.getDouble(), 0);
        talon.configAllowableClosedloopError(0, SmartDashboardMap.LIFT_ALLOWABLE_ERROR.getIntValue(), 0);
        talon.configPeakOutputForward(SmartDashboardMap.LIFT_PEAK_VOLTAGE.getDouble(), 0);
        talon.configPeakOutputReverse(-SmartDashboardMap.LIFT_PEAK_VOLTAGE.getDouble(), 0);
        talon.configNominalOutputForward(SmartDashboardMap.LIFT_NOMINAL_VOLTAGE.getDouble(), 0);
        talon.configNominalOutputReverse(-SmartDashboardMap.LIFT_NOMINAL_VOLTAGE.getDouble(), 0);
    }

}
