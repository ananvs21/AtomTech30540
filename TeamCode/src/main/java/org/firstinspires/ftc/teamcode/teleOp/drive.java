package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

import java.util.function.DoubleSupplier;
@TeleOp (name = "drive", group = "TeleOp")
public class drive extends CommandOpMode {

    private MecanumDriveSubsystem drive;
    private GamepadEx gamepad;

    @Override
    public void initialize() {
        drive = new MecanumDriveSubsystem(hardwareMap);
        CommandScheduler.getInstance().registerSubsystem(drive);

        gamepad = new GamepadEx(gamepad1);

        drive.setDefaultCommand(new DriveCommand(drive, new DoubleSupplier() {
            @Override
            public double getAsDouble() {
                return -gamepad1.left_stick_y;
            }
        }, new DoubleSupplier() {
            @Override
            public double getAsDouble() {
                return gamepad1.left_stick_x;
            }
        }, new DoubleSupplier() {
            @Override
            public double getAsDouble() {
                return gamepad1.right_stick_x;
            }
        }
        ));
        gamepad.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(new InstantCommand(() -> drive.resetYaw()));
    }
}
