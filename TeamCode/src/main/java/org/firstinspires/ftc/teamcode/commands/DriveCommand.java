package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {
    private final MecanumDriveSubsystem driveSubsystem;
    private final DoubleSupplier strafeSpeed;
    private final DoubleSupplier forwardSpeed;
    private final DoubleSupplier turnSpeed;

    public DriveCommand(MecanumDriveSubsystem driveSubsystem,
                        DoubleSupplier forwardSpeed,
                        DoubleSupplier strafeSpeed,
                        DoubleSupplier turnSpeed) {
        this.driveSubsystem = driveSubsystem;
        this.strafeSpeed = strafeSpeed;
        this.forwardSpeed = forwardSpeed;
        this.turnSpeed = turnSpeed;

        addRequirements(driveSubsystem);
    }
    @Override
    public void execute(){
        driveSubsystem.drive(forwardSpeed.getAsDouble(), strafeSpeed.getAsDouble(), turnSpeed.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stop();
    }
}
