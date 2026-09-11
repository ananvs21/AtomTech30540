package org.firstinspires.ftc.teamcode.base;

import com.seattlesolvers.solverslib.command.CommandBase;


public class ElevatorCommand extends CommandBase {
    private final ElevatorSubsystem elevatorSubsystem;
    private final int stage;

    public ElevatorCommand(ElevatorSubsystem elevatorSubsystem, int stage) {
        this.elevatorSubsystem = elevatorSubsystem;
        this.stage = stage;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        elevatorSubsystem.setTargetIndex(stage);
    }

//    @Override
//    public boolean isFinished() {
//
//    }
    @Override
    public void end(boolean interrupted){
        elevatorSubsystem.stopMotor();
    }
}
