package org.firstinspires.ftc.teamcode.base;

import com.seattlesolvers.solverslib.command.CommandOpMode;

public class elevatorOpMode extends CommandOpMode {
    private RobotElevator robot;

    @Override
    public void initialize(){
        robot = new RobotElevator(hardwareMap, gamepad1);
        telemetry.addLine("Elevador Operando");
        telemetry.update();
    }
}
