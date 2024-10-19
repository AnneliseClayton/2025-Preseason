package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.MotorIdConstants;

public class Indexer extends SubsystemBase {

    private final CANSparkMax indexerMotor;

    public Indexer() {
        indexerMotor = new CANSparkMax(MotorIdConstants.INDEXER_MOTOR_ID, MotorType.kBrushless);

        indexerMotor.restoreFactoryDefaults();
        indexerMotor.setInverted(false);
        indexerMotor.setSmartCurrentLimit(MotorConstants.NEO550_CURRENT_LIMIT);
    }

    public Command setSpeed(double speed) {
        return this.run(() -> indexerMotor.set(speed));
    }
}
