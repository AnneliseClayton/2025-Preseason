package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.MotorIdConstants;

public class Intake extends SubsystemBase {
    private CANSparkMax leftCenteringIntakeMotor;
    private CANSparkMax rightCenteringIntakeMotor;
    private CANSparkMax intakeMotor;
    private RelativeEncoder intakeEncoder;
    private SparkPIDController intakePIDController;

    private static final double INTAKE_FEEDFORWARD = 0.01;
    private static final double INTAKE_PVALUE = 0.01;

    public Intake() {
        leftCenteringIntakeMotor = new CANSparkMax(MotorIdConstants.LEFT_INTAKE_CENTERING_MOTOR_ID,
                MotorType.kBrushless);
        rightCenteringIntakeMotor = new CANSparkMax(MotorIdConstants.RIGHT_INTAKE_CENTERING_MOTOR_ID, MotorType.kBrushless);
        intakeMotor = new CANSparkMax(MotorIdConstants.INTAKE_MOTOR_ID, MotorType.kBrushless);

        leftCenteringIntakeMotor.restoreFactoryDefaults();
        rightCenteringIntakeMotor.restoreFactoryDefaults();
        intakeMotor.restoreFactoryDefaults();

        leftCenteringIntakeMotor.setSmartCurrentLimit(MotorConstants.NEO550_CURRENT_LIMIT);
        rightCenteringIntakeMotor.setSmartCurrentLimit(MotorConstants.NEO550_CURRENT_LIMIT);
        intakeMotor.setSmartCurrentLimit(MotorConstants.NEO550_CURRENT_LIMIT);

        leftCenteringIntakeMotor.setInverted(true);
        rightCenteringIntakeMotor.setInverted(false);
        intakeMotor.setInverted(false);

        intakeEncoder = intakeMotor.getEncoder();
        intakeEncoder.setVelocityConversionFactor(1 / 60.0);

        intakePIDController = intakeMotor.getPIDController();
        intakePIDController.setFeedbackDevice(intakeEncoder);

        intakePIDController.setFF(INTAKE_FEEDFORWARD);
        intakePIDController.setP(INTAKE_PVALUE);
    }

    public Command setSpeed(double speed) {
        return this.run(() -> {
            intakeMotor.set(speed);
            rightCenteringIntakeMotor.set(speed);
            leftCenteringIntakeMotor.set(speed);
            SmartDashboard.putNumber("intake speed",speed);
        });
    }
}
