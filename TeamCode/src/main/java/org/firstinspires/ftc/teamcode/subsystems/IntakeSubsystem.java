package org.firstinspires.ftc.teamcode.subsystems;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeSubsystem extends SubsystemBase{
    private final DcMotor motorIntake;

    public IntakeSubsystem (HardwareMap hardwareMap){
        motorIntake = hardwareMap.get(DcMotor.class, "motorIntake");
        motorIntake.setDirection(DcMotorSimple.Direction.FORWARD);
        motorIntake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void setPower(double power){
        motorIntake.setPower(power);
    }
    public void stop (){
        motorIntake.setPower(0);
    }
}
