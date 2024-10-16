// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.SubsystemSpeeds;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private final CommandXboxController driverController = new CommandXboxController(
      DriveConstants.DRIVER_CONTROLLER_PORT);
  private final CommandXboxController operatorController = new CommandXboxController(
      DriveConstants.OPERATOR_CONTROLLER_PORT);

  private final Intake intake = new Intake();
  private final Indexer indexer = new Indexer();
  private final CommandFactory commandFactory = new CommandFactory(intake, indexer);

  public RobotContainer() {
    configureDriverBindings();
    configureOperatorBindings();
    configureDefaultCommands();
  }

  private void configureDefaultCommands() {
    intake.setDefaultCommand(intake.setSpeed(0));
    indexer.setDefaultCommand(indexer.setSpeed(0));
  }

  private void configureOperatorBindings() {
    operatorController.rightBumper().whileTrue(intake.setSpeed(SubsystemSpeeds.INTAKE_SPEED));
    operatorController.leftBumper().whileTrue(intake.setSpeed(-SubsystemSpeeds.INTAKE_SPEED));
    operatorController.rightTrigger().whileTrue(commandFactory.indexAndIntakeTogether());
  }

  private void configureDriverBindings() {

  }
}
