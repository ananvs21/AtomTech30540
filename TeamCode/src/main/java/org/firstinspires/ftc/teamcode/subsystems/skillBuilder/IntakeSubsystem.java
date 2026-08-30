package org.firstinspires.ftc.teamcode.subsystems.skillBuilder;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeSubsystem extends SubsystemBase{
    private final DcMotor motorIntake;
    public static final double coletaPower = 1.0;
    public static final double expelirPower = -0.8;
    public IntakeSubsystem (HardwareMap hardwareMap){
        motorIntake = hardwareMap.get(DcMotor.class, "motorIntake");
        motorIntake.setDirection(DcMotorSimple.Direction.FORWARD);
        motorIntake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void setPower (double power){
        motorIntake.setPower(power);
    }
    public void coleta(double coletaPower){
        motorIntake.setPower(coletaPower);
    }
    public void expelir(double expelirPower ){
        motorIntake.setPower(expelirPower);
    }
    public void stop (){
        motorIntake.setPower(0);
    }
}
