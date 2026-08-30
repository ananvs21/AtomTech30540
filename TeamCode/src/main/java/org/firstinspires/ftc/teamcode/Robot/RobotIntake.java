package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.Robot;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.commands.skillBuilder.IntakeCommand;
import org.firstinspires.ftc.teamcode.subsystems.skillBuilder.IntakeSubsystem;

public class RobotIntake extends Robot {
    public final IntakeSubsystem intakeSubsystem;

    private final GamepadEx gamepadDriver;
    private final GamepadEx gamepadCoDriver;

    public RobotIntake(HardwareMap hardwareMap, Gamepad gamepad1, Gamepad gamepad2) {
        this.intakeSubsystem = new IntakeSubsystem(hardwareMap);
        gamepadDriver = new GamepadEx(gamepad1);
        gamepadCoDriver = new GamepadEx(gamepad2);

        configureButtonBindings();
    }
        private void configureButtonBindings() {
            gamepadDriver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                    .whileHeld(new IntakeCommand(intakeSubsystem, IntakeSubsystem.coletaPower));

            gamepadCoDriver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                    .whileHeld(new IntakeCommand(intakeSubsystem, IntakeSubsystem.expelirPower));
        }
    }

