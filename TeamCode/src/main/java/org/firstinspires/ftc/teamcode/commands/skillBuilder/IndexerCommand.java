package org.firstinspires.ftc.teamcode.commands.skillBuilder;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.skillBuilder.IndexerSubsystem;

public class IndexerCommand extends CommandBase {
    IndexerSubsystem indexerSubsystem;
    private final double power;

    public IndexerCommand (IndexerSubsystem indexerSubsystem, double indexerPower){
        this.indexerSubsystem = indexerSubsystem;
        this.power = indexerPower;
        addRequirements(indexerSubsystem);
    }
    @Override
    public void initialize (){
        indexerSubsystem.setPower(power);
    }
    @Override
    public void end(boolean interrupted){
        indexerSubsystem.stopMotor();
    }
    @Override
    public boolean isFinished() {
        return false; // Roda até ser interrompido
    }
}
