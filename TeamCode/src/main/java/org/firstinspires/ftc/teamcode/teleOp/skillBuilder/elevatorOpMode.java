package org.firstinspires.ftc.teamcode.teleOp.skillBuilder;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Robot.RobotElevator;
import org.firstinspires.ftc.teamcode.commands.skillBuilder.ElevatorCommand;
import org.firstinspires.ftc.teamcode.subsystems.skillBuilder.ElevatorSubsystem;

public class elevatorOpMode extends CommandOpMode {
    private RobotElevator robot;

    @Override
    public void initialize(){
        robot = new RobotElevator(hardwareMap, gamepad1);
        telemetry.addLine("Elevador Operando");
        telemetry.update();
    }
}
