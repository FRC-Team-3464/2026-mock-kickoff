package frc.robot.subsystems;

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  private final SparkMax m_pivotLeftMotor = new SparkMax(1, MotorType.kBrushless);
  private final SparkMax m_pivotRightMotor = new SparkMax(2, MotorType.kBrushless);
  private final SparkMax m_rollerMotor = new SparkMax(3, MotorType.kBrushless);
  private final SparkMaxConfig m_pivotLeftMotorConfig;
  private final SparkMaxConfig m_pivotRightMotorConfig;
  private final SparkMaxConfig m_rollerMotorConfig;
  private final RelativeEncoder m_pivotEncoder = m_pivotLeftMotor.getEncoder();
  private final RelativeEncoder m_rollEncoder = m_rollerMotor.getEncoder();
  private final DigitalInput m_innerLimit = new DigitalInput(4);
  private final DigitalInput m_extendedLimit = new DigitalInput(5);
  public Intake() {
    m_pivotLeftMotorConfig = new SparkMaxConfig();
    m_pivotRightMotorConfig = new SparkMaxConfig();
    m_rollerMotorConfig = new SparkMaxConfig();
    m_pivotRightMotorConfig.follow(m_pivotLeftMotor, true);
}
public void setPivotSpeed(double speed){
  m_pivotLeftMotor.set(speed);
}
public void stopPivot(){
  m_pivotLeftMotor.stopMotor();
}
public void setRollerSpeed(double speed){
  m_rollerMotor.set(speed);
}
public void stopRoller(){
  m_rollerMotor.stopMotor();
}
public double getPivotVelocity(){
  return getPivotVelocity();
}
public double getPivotPosition(){
return getPivotPosition();
}
public double getRollerVelocity(){
  return getRollerVelocity();
}
public double getRollerPosition(){
  return getRollerPosition();
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
