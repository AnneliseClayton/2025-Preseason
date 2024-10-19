package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.MotorIdConstants;

public class Shooter extends SubsystemBase {

    CANSparkMax topMotor;
    CANSparkMax bottomMotor; 

    public Shooter() {
        topMotor = new CANSparkMax(MotorIdConstants.SHOOTER_TOP_MOTOR_ID, MotorType.kBrushless);
        bottomMotor = new CANSparkMax(MotorIdConstants.SHOOTER_BOTTOM_MOTOR_ID, MotorType.kBrushless); 
    
        topMotor.restoreFactoryDefaults();
        topMotor.setInverted(true);
        topMotor.setSmartCurrentLimit(MotorConstants.NEO_CURRENT_LIMIT);
        bottomMotor.restoreFactoryDefaults();
        bottomMotor.setInverted(true);
        bottomMotor.setSmartCurrentLimit(MotorConstants.NEO_CURRENT_LIMIT);
    }

    public Command setShooterSpeed(double speed) {
        return this.run(() -> {
            topMotor.set(speed);
            bottomMotor.set(speed);
    }); }

    public BooleanSupplier isShooterAtSpeed(double speed) {
        return () -> false;
    }
}

