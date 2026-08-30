package org.firstinspires.ftc.teamcode.commands.skillBuilder;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.skillBuilder.IntakeSubsystem;

public class IntakeCommand extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;
    private final double power;

    public IntakeCommand (IntakeSubsystem intakeSubsystem,
                          double power){
        this.intakeSubsystem = intakeSubsystem;
        this.power = power;

        addRequirements(intakeSubsystem);
    }
    @Override
    public void execute (){
        intakeSubsystem.setPower(power);
    }
    @Override
    public void end(boolean interrupted){
        intakeSubsystem.stop();
    }
}
