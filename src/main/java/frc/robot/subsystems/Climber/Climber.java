 // Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Climber;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class Climber extends SubsystemBase {
  /** Creates a new Climber. */

  public Trigger isExtended = new Trigger(()->getTopLimit());

  public Command extendClimberCommand() {
    if (!getTopLimit()) {
      return Commands.startEnd(() -> setSpeed(0.5), () -> stop()).until(() -> getTopLimit()).withName("Extend Climber");
    } else{
      return Commands.none();
    }
  }

  public Command retractClimberCommand() {
    if (!getBottomLimit()) {
      return Commands.startEnd(() -> setSpeed(-0.5), () -> stop()).until(() -> getBottomLimit()).withName("Retract Climber");
    } else{
      return Commands.none();
    }
  }

  public Command autoClimbSequence() {
    if(!touchingBar()){
      return extendClimberCommand().andThen(retractClimberCommand()).until(() -> touchingBar()).withName("Automatic Climber");
    }else{
      return Commands.none();
    }
  }

  //this.isExtended
  //.onTrue(this.extendClimberCommand());

  private final SparkMax m_motor = new SparkMax(1, MotorType.kBrushless);

  private final DigitalInput m_topLimit = new DigitalInput(1);
  private final DigitalInput m_bottomLimit = new DigitalInput(2);
  private final DigitalInput m_barLimit = new DigitalInput(100);
  private final RelativeEncoder m_encoder = m_motor.getEncoder();

  private SparkMaxConfig m_leftMotorConfig;
  private boolean m_isRunning = false;

  public Climber() {
  }

  public void stop() {
    m_motor.set(0);
  }

  public void setSpeed(double speed) { // setSpeed Idea
    m_motor.set(speed);
  }

  public boolean getTopLimit() {
    return m_topLimit.get();
  }

  public boolean getBottomLimit() {
    return m_bottomLimit.get();
  }

  public double getVelocity() {
    return m_encoder.getVelocity();
  }

  public double getPosition() {
    return m_encoder.getPosition();
  }

  public boolean touchingBar(){
    return m_barLimit.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
