package org.firstinspires.ftc.teamcode.teleOp.skillBuilder;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.Robot.RobotIntake;
@TeleOp ()
public class intakeOpMode extends CommandOpMode {
    private RobotIntake robot;

    @Override
    public void initialize(){
        robot = new RobotIntake(hardwareMap, gamepad1, gamepad2);
        telemetry.addLine("Intake Operando");
        telemetry.update();
    }
}
