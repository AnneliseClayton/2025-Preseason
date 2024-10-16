package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.SubsystemSpeeds;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

public class CommandFactory {
    private final Intake intake;
    private final Indexer indexer;

    public CommandFactory(Intake intake, Indexer indexer) {
        this.intake = intake;
        this.indexer = indexer;
    }

    public Command indexAndIntakeTogether(){
        return Commands.parallel(intake.setSpeed(SubsystemSpeeds.INTAKE_SPEED),indexer.setSpeed(SubsystemSpeeds.INTAKE_SPEED));
    }
}
