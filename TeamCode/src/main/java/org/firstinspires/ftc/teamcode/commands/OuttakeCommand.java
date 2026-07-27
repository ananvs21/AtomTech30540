package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.OuttakeSubsystem;

public class OuttakeCommand extends CommandBase {
    private final OuttakeSubsystem outtakeSubsystem;
    private final double targetRPM;
    private final double mediatorPower;
    private final long delay = 1000;
    private long startTime;
    private boolean mediatorStarted = false;

    public OuttakeCommand(OuttakeSubsystem outtakeSubsystem, double targetRPM, double mediatorPower) {
        this.outtakeSubsystem = outtakeSubsystem;
        this.targetRPM = targetRPM;
        this.mediatorPower = mediatorPower;
        addRequirements(outtakeSubsystem);
    }

    @Override
    public void initialize() {
        outtakeSubsystem.setTargetRPM(targetRPM);
        outtakeSubsystem.setPowerMediador(0);
        startTime = System.currentTimeMillis();
        mediatorStarted = false;
    }

    @Override
    public void execute() {
        if (!mediatorStarted && (System.currentTimeMillis() - startTime >= delay)) {
            outtakeSubsystem.setPowerMediador(mediatorPower);
            mediatorStarted = true;
        }
    }

    @Override
    public boolean isFinished() {
        return false; // Comando contínuo — só termina com toggle (2.º clique)
    }

    @Override
    public void end(boolean interrupted) {
        outtakeSubsystem.setPowerMediador(0);
        outtakeSubsystem.stop(); // Para TUDO sempre (mediador + PID + motor)
    }
}