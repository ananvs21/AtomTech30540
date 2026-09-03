package org.firstinspires.ftc.teamcode.subsystems.skillBuilder;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class IndexerSubsystem extends SubsystemBase {
    private final DcMotorEx indexerMotor;
    public static double indexerPower = 0.8;
    public IndexerSubsystem (HardwareMap hardwareMap){
        indexerMotor = hardwareMap.get(DcMotorEx.class, "indexerMotor");
        indexerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        indexerMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }
    public void setPower(double power) {
        indexerMotor.setPower(power);
    }
    public void stopMotor (){
        indexerMotor.setPower(0);
    }
    public double getMotorPower() {
        return indexerMotor.getPower();
    }
}
