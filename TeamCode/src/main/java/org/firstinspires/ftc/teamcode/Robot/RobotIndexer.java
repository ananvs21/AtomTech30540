package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.Robot;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.commands.skillBuilder.IndexerCommand;
import org.firstinspires.ftc.teamcode.subsystems.skillBuilder.IndexerSubsystem;

public class RobotIndexer extends Robot {
    public IndexerSubsystem indexerSubsystem;
    private final GamepadEx gamepadDriver;

    public RobotIndexer(HardwareMap hardwareMap, Gamepad gamepad1){
        indexerSubsystem = new IndexerSubsystem(hardwareMap);
        gamepadDriver = new GamepadEx(gamepad1);

        configureButtonBinding();
    }
    private void configureButtonBinding(){
        gamepadDriver.getGamepadButton(GamepadKeys.Button.A).
                toggleWhenPressed(new IndexerCommand(indexerSubsystem, IndexerSubsystem.indexerPower));
    }
}
