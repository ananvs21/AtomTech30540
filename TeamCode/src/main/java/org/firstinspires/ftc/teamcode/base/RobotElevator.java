package org.firstinspires.ftc.teamcode.base;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.Robot;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

public class RobotElevator extends Robot {
    public ElevatorSubsystem elevatorSubsystem;
    private final GamepadEx gamepadDriver;

    public RobotElevator(HardwareMap hardwareMap,Gamepad gamepad1) {
        this.elevatorSubsystem = new ElevatorSubsystem(hardwareMap);
        gamepadDriver = new GamepadEx(gamepad1);

        configureButtonBindings();
    }
        private void configureButtonBindings(){
        gamepadDriver.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new InstantCommand(() -> elevatorSubsystem.setTargetIndex(1), elevatorSubsystem));
        gamepadDriver.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(new InstantCommand(() -> elevatorSubsystem.setTargetIndex(2), elevatorSubsystem));
        gamepadDriver.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(new InstantCommand(() -> elevatorSubsystem.setTargetIndex(3), elevatorSubsystem));
        gamepadDriver.getGamepadButton(GamepadKeys.Button.CIRCLE)
                .whenPressed(new InstantCommand(() -> elevatorSubsystem.setTargetIndex(4), elevatorSubsystem));
        gamepadDriver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new InstantCommand(() -> elevatorSubsystem.setTargetIndex(0), elevatorSubsystem));
    }
}

