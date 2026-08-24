package org.firstinspires.ftc.teamcode.subsystems.pretemporada;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;

public class OuttakeSubsystem extends SubsystemBase {
    private final DcMotorEx motorMediador;
    private final DcMotorEx motorOuttake;

    public static double kP = 0.005;
    public static double kI = 0.0002;
    public static double kD = 0.0;

    double targetVelocity;      // em ticks/s (interno)
    PIDController pid;

    private static final double TICKS_PER_REVOLUTION = 28.0;

    public OuttakeSubsystem(HardwareMap hardwareMap) {
        motorMediador = hardwareMap.get(DcMotorEx.class, "motorMediador");
        motorOuttake = hardwareMap.get(DcMotorEx.class, "motorOuttake");

        motorMediador.setDirection(DcMotorSimple.Direction.FORWARD);
        motorOuttake.setDirection(DcMotorSimple.Direction.REVERSE);

        motorMediador.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorOuttake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorOuttake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        pid = new PIDController(kP, kI, kD);
        targetVelocity = 0.0;
    }

    public void setPowerMediador(double power) {
        motorMediador.setPower(power);
    }

    public double getCurrentVelocity() {
        return motorOuttake.getVelocity();   // ticks/s
    }

    // ÚNICA conversão necessária: RPM → ticks/s
    private double rpmToTicks(double rpm) {
        return (rpm * TICKS_PER_REVOLUTION) / 60.0;
    }

    public void setTargetRPM(double rpm) {
        this.targetVelocity = rpmToTicks(rpm);
    }

    public void stop() {
        motorMediador.setPower(0);
        targetVelocity = 0.0;
        motorOuttake.setPower(0);
    }

    @Override
    public void periodic() {
        pid.setPID(kP, kI, kD);
        double power = pid.calculate(getCurrentVelocity(), targetVelocity);
        motorOuttake.setPower(power);
    }
}