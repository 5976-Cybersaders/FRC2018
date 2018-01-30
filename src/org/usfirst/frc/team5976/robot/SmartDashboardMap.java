package org.usfirst.frc.team5976.robot;

public class SmartDashboardMap {
	// Encoder
	public static final SmartValue kPFL = new SmartValue("P-Value FL", 0.04);
	public static final SmartValue kIFL = new SmartValue("I-Value FL", 0);
	public static final SmartValue kDFL = new SmartValue("D-Value FL", 0);
	public static final SmartValue kPFR = new SmartValue("P-Value FR", 0.04);
	public static final SmartValue kIFR = new SmartValue("I-Value FR", 0);
	public static final SmartValue kDFR = new SmartValue("D-Value FR", 0);
	public static final SmartValue kPRL = new SmartValue("P-Value RL", 0.04);
	public static final SmartValue kIRL = new SmartValue("I-Value RL", 0);
	public static final SmartValue kDRL = new SmartValue("D-Value RL", 0);
	public static final SmartValue kPRR = new SmartValue("P-Value RR", 0.04);
	public static final SmartValue kIRR = new SmartValue("I-Value RR", 0);
	public static final SmartValue kDRR = new SmartValue("D-Value RR", 0);
	public static final SmartValue ALLOWABLE_ERROR = new SmartValue("Allowable Error", 100);

	// Talon
	public static final SmartValue PEAK_VOLTAGE = new SmartValue("Peak Voltage", 0.5);
	public static final SmartValue NOMINAL_VOLTAGE = new SmartValue("Nominal Voltage", 0.25);
	public static final SmartValue RAMP_RATE = new SmartValue("Ramp Rate", 0);
	// public static final SmartValue RAMP_RATE_R = new SmartValue("Ramp Rate Right");

	public static final SmartValue POSITION = new SmartValue("Position", "RIGHT");
	public static final SmartValue DELAY = new SmartValue("Delay (Seconds)", 0);
	
	public static void reportAll() {
		report(kPFL);
		report(kIFL);
		report(kDFL);
		report(kPFR);
		report(kIFR);
		report(kDFR);
		report(kPRL);
		report(kIRL);
		report(kDRL);
		report(kPRR);
		report(kIRR);
		report(kDRR);
		report(ALLOWABLE_ERROR);
		report(PEAK_VOLTAGE);
		report(NOMINAL_VOLTAGE);
		report(RAMP_RATE);
		report(POSITION);
		report(DELAY);
	}

	private static void report(SmartValue variable) {
		System.out.println(variable.getKey() + ": " + variable.getDouble());
	}
}
