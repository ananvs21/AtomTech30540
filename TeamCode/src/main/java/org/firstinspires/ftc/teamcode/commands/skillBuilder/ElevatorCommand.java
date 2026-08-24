package org.firstinspires.ftc.teamcode.commands.skillBuilder;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.skillBuilder.ElevatorSubsystem;


public class ElevatorCommand extends CommandBase {
    private final ElevatorSubsystem elevatorSubsystem;
    private final int stage;

    public ElevatorCommand (ElevatorSubsystem elevatorSubsystem, int stage){
        this.elevatorSubsystem = elevatorSubsystem;
        this.stage = stage;
        addRequirements(elevatorSubsystem);
    }
    @Override
    public void initialize(){
        elevatorSubsystem.setTargetIndex(stage);
    }
}
