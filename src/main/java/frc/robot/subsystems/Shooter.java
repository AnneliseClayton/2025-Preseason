package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.MotorIdConstants;

public class Shooter extends SubsystemBase {

    CANSparkMax topMotor;
    CANSparkMax bottomMotor;
    SparkPIDController shooterHighController;
    SparkPIDController shooterLowController;
    RelativeEncoder topMotorEncoder;
    RelativeEncoder bottomMotorEncoder;

    public Shooter() {
        topMotor = new CANSparkMax(MotorIdConstants.SHOOTER_TOP_MOTOR_ID, MotorType.kBrushless);
        bottomMotor = new CANSparkMax(MotorIdConstants.SHOOTER_BOTTOM_MOTOR_ID, MotorType.kBrushless);
        topMotor.restoreFactoryDefaults();
        bottomMotor.restoreFactoryDefaults();
        shooterHighController = topMotor.getPIDController();
        shooterLowController = bottomMotor.getPIDController();
        topMotorEncoder = topMotor.getEncoder();
        bottomMotorEncoder = bottomMotor.getEncoder();
        topMotorEncoder.setVelocityConversionFactor(2*Math.PI/60.0);
        bottomMotorEncoder.setVelocityConversionFactor(2*Math.PI/60.0);

        
        topMotor.setInverted(true);
        topMotor.setSmartCurrentLimit(MotorConstants.NEO_CURRENT_LIMIT);
        bottomMotor.setInverted(true);
        bottomMotor.setSmartCurrentLimit(MotorConstants.NEO_CURRENT_LIMIT);
        shooterHighController.setP(0.0008);
        shooterHighController.setI(0);
        shooterHighController.setD(0);
        shooterLowController.setP(0.0008);
        shooterLowController.setI(0);
        shooterLowController.setD(0);
        shooterHighController.setFF(0.0018);
        shooterLowController.setFF(0.0018);
    }

    public Command setShooterSpeed(double speed) {
        return this.run(() -> {
            topMotor.set(speed);
            bottomMotor.set(speed);
            SmartDashboard.putNumber("shooter speed",speed);
        });
    }

    public Command setShooterSpeedPID(double speed){
        return this.run(()->{
            shooterHighController.setReference(speed*MotorConstants.NEO_FREE_SPEED_RADIANS_PER_SECOND,ControlType.kVelocity);
            shooterLowController.setReference(speed*MotorConstants.NEO_FREE_SPEED_RADIANS_PER_SECOND,ControlType.kVelocity);
            SmartDashboard.putNumber("top shooter reference speed",speed*MotorConstants.NEO_FREE_SPEED_RADIANS_PER_SECOND);
            SmartDashboard.putNumber("bottom shooter reference speed",speed*MotorConstants.NEO_FREE_SPEED_RADIANS_PER_SECOND);
        });
    }

    public BooleanSupplier isShooterAtSpeed(double speed) {
        return () -> false;
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("top shooter actual speed",topMotorEncoder.getVelocity());
        SmartDashboard.putNumber("bottom shooter actual speed",bottomMotorEncoder.getVelocity());
    }
}
