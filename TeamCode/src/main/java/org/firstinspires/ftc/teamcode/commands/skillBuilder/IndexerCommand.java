package org.firstinspires.ftc.teamcode.commands.skillBuilder;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.skillBuilder.IndexerSubsystem;

public class IndexerCommand extends CommandBase {
    IndexerSubsystem indexerSubsystem;

    public IndexerCommand (IndexerSubsystem indexerSubsystem){
        this.indexerSubsystem = indexerSubsystem;
        addRequirements(indexerSubsystem);
    }
    @Override
    public void initialize (){
        indexerSubsystem.acionamento();
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
