package org.firstinspires.ftc.teamcode.subsystems.skillBuilder;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.controller.PIDFController;

public class ElevatorSubsystem extends SubsystemBase {
    private final DcMotorEx elevatorMotor;
    private final TouchSensor touchSensor;
    public static double kP = 0.005;
    public static double kI = 0.0002;
    public static double kD = 0.0;
    public static double kF = 0.0;

    private final double[] targets = {0.0, 300.0, 600.0, 1200.0};

    private int currentTargetIndex = 0;
    PIDFController pidf;

    private double targetPosition = 0.0;

    public ElevatorSubsystem(HardwareMap hardwareMap) {
        elevatorMotor = hardwareMap.get(DcMotorEx.class, "elevatorMotor");
        elevatorMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        elevatorMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        elevatorMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevatorMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        pidf = new PIDFController(kP, kI, kD, kF);
        targetPosition = 0.0;

        touchSensor = hardwareMap.get(TouchSensor.class, "touchSensor");
    }
    public void limite (){
        if (touchSensor.isPressed()){
            elevatorMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            elevatorMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            currentTargetIndex = 0;
            targetPosition = 0.0;

            stopMotor();
        }
    }
    public void stopMotor (){
        elevatorMotor.setPower(0);
    }
    public double getCurrentPosition() {
        return elevatorMotor.getCurrentPosition();
    }

    public void setTargetPosition(double position) {
        this.targetPosition = position;
    }

    public void setTargetIndex(int index) {
        if (index >= 0 && index < targets.length) {
            this.currentTargetIndex = index;
            this.targetPosition = targets[index];
        }
    }

    public void moveToNextPosition() {
        if (currentTargetIndex < targets.length - 1) {
            setTargetIndex(currentTargetIndex + 1);
        }
    }

    public void moveToPreviousPosition() {
        if (currentTargetIndex > 0) {
            setTargetIndex(currentTargetIndex - 1);
        }
    }

      public double getTargetPosition() {
        return targetPosition;
    }

    public int getCurrentTargetIndex() {
        return currentTargetIndex;
    }

    @Override
    public void periodic() {
        limite();

        pidf.setPIDF(kP, kI, kD, kF);
        double power = pidf.calculate(getCurrentPosition(), targetPosition);
        elevatorMotor.setPower(power);
    }
}

