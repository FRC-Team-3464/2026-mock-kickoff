// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase {

  private final SparkMax m_leftMotor = new SparkMax(2, MotorType.kBrushless);
  private final SparkMax m_rightMotor = new SparkMax(3, MotorType.kBrushless);
  private final RelativeEncoder m_encoder = m_leftMotor.getEncoder();
  private final SparkMaxConfig leftMotorConfig = new SparkMaxConfig();
  private final SparkMaxConfig rightMotorConfig = new SparkMaxConfig();
  

  /** Creates a new Indexer. */
  public Indexer() {
    rightMotorConfig.follow(m_leftMotor, true);
  }

  public void setSpeed(double speed) {
    m_leftMotor.set(speed);
  }

  public void stop() {
    m_leftMotor.set(0);
  }

  public double getVelocity() {
    return m_encoder.getVelocity();
  }

  public double getPosition() {
    return m_encoder.getPosition();
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
