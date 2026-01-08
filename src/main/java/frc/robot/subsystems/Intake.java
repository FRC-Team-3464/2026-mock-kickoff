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

/**
 * Class intake is used to intake the power cells and then transfer to shooter
 * 
 * @author Senuth
 * @author Harsh
 * @author KH
 */
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
    m_pivotRightMotor.configure(m_pivotRightMotorConfig, null, null);
  }

  /** 
   * Sets the pivot motor speed
   * 
   * @param speed the speed to run the pivot motor at (-1.0 to 1.0)
   */
  public void setPivotSpeed(double speed) {
    m_pivotLeftMotor.set(speed);
  }

  /** 
   * Stops the pivot motor
   */
  public void stopPivot() {
    m_pivotLeftMotor.stopMotor();
  }

  /** 
   * Sets the roller motor speed
   * 
   * @param speed the speed to run the roller motor at (-1.0 to 1.0)
   */
  public void setRollerSpeed(double speed) {
    m_rollerMotor.set(speed);
  }

  /** 
   * Stops the roller motor speed
   */
  public void stopRoller() {
    m_rollerMotor.stopMotor();
  }

  /** 
   * Get the pivot motor speed
   * 
   * @return the speed of the pivot motor in RPM
   */
  public double getPivotVelocity() {
    return m_pivotEncoder.getVelocity();
  }

  /** 
   * Get the pivot position
   * 
   * @return the position of the pivot motor in rotations
   */
  public double getPivotPosition() {
    return m_pivotEncoder.getPosition();
  }

 /** 
   * Get the roller velocity
   * 
   * @return the speed of the roller motor in RPM
   */
  public double getRollerVelocity() {
    return m_rollEncoder.getVelocity();
  }

  /** 
   * Get the roller position
   * 
   * @return position of the roller in rotations
   */
  public double getRollerPosition() {
    return m_rollEncoder.getPosition();
  }

 /** 
   * Checks if the intake is fully extended
   * 
   * @return true if the intake is fully extended, false otherwise
   */
  public boolean getExtendedLimit() {
    return m_extendedLimit.get();
  }

  /** 
   * Checks if the intake is fully retracted, false otherwise
   * 
   * @return the inner limit
   */
  public boolean getInnerLimit() {
    return m_innerLimit.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
