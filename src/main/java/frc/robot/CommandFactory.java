package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.SubsystemSpeeds;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class CommandFactory {
    private final Intake intake;
    private final Indexer indexer;
    private final Shooter shooter;

    public CommandFactory(Intake intake, Indexer indexer, Shooter shooter) {
        this.intake = intake;
        this.indexer = indexer;
        this.shooter = shooter;
    }

    public Command runIntakeAndIndexer() {
        return Commands.parallel(intake.setSpeed(SubsystemSpeeds.INTAKE_SPEED),
                indexer.setSpeed(SubsystemSpeeds.INTAKE_SPEED));
    }

    public Command shootWhenUpToSpeed(double speed) {
        return Commands.parallel(shooter.setShooterSpeed(speed),
                Commands.either(indexer.setSpeed(SubsystemSpeeds.INDEXER_FEED_SPEED), indexer.setSpeed(0),
                        shooter.isShooterAtSpeed(speed)));
    }

    public Command runningIntakeAndIndexerAtTheSameTime() {
        return Commands.parallel(intake.setSpeed(SubsystemSpeeds.INDEXER_FEED_SPEED), indexer.setSpeed(SubsystemSpeeds.INDEXER_FEED_SPEED));
    }
}
