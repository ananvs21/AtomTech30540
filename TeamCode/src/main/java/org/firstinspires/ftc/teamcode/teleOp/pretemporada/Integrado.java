package org.firstinspires.ftc.teamcode.teleOp.pretemporada;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.skillBuilder.IntakeCommand;
import org.firstinspires.ftc.teamcode.commands.pretemporada.OuttakeCommand;
import org.firstinspires.ftc.teamcode.subsystems.skillBuilder.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.pretemporada.OuttakeSubsystem;

import java.util.function.DoubleSupplier;
@TeleOp(name = "Integrado", group = "TeleOp")

public class Integrado extends CommandOpMode {
    private MecanumDriveSubsystem drive;
    private IntakeSubsystem intake;
    private OuttakeSubsystem outtake;
    private GamepadEx gamepad;

    @Override
    public void initialize() {
        drive = new MecanumDriveSubsystem(hardwareMap);
        intake = new IntakeSubsystem(hardwareMap);
        outtake = new OuttakeSubsystem(hardwareMap);
        CommandScheduler.getInstance().registerSubsystem(drive, intake, outtake);

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
        gamepad.getGamepadButton(GamepadKeys.Button.A)
                .toggleWhenPressed(new IntakeCommand(intake, IntakeSubsystem.coletaPower));
        gamepad.getGamepadButton(GamepadKeys.Button.B)
                .whileHeld(new IntakeCommand(intake, IntakeSubsystem.expelirPower));
        gamepad.getGamepadButton(GamepadKeys.Button.X)
                .toggleWhenPressed(new OuttakeCommand(outtake, 4000, 1));
    }
}
