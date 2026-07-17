package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;

public class OuttakeSubsystem extends SubsystemBase {
    private final DcMotor motorMediador;
    private final DcMotor motorOuttake;
    public static double kP = 0.01;
    public static double kI = 0.01;
    public static double kD = 0.01;
    double target;
    PIDController pid;
    public OuttakeSubsystem (HardwareMap hardwareMap){
        motorMediador = hardwareMap.get(DcMotor.class, "motorMediador");
        motorOuttake = hardwareMap.get(DcMotor.class, "motorOuttake");

        motorMediador.setDirection(DcMotorSimple.Direction.FORWARD);
        motorOuttake.setDirection(DcMotorSimple.Direction.REVERSE);

        motorMediador.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorOuttake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        pid = new PIDController( kP, kI, kD);

    }
    public void setPowerMediador (double power){
        motorMediador.setPower(power);
    }
    public double getCurrentPosition() {
        return motorOuttake.getCurrentPosition();
    }
    public void stop (){
        motorOuttake.setPower(0);
        motorMediador.setPower(0);
        this.target = motorOuttake.getCurrentPosition();
    }
    @Override
    public void periodic (){
        pid.setPID(kP,kI, kD);
        double power = pid.calculate(getCurrentPosition(), target);
        motorOuttake.setPower(power);
    }
}
