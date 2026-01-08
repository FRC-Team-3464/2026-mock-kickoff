// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.





package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
/**
 * This subsystem controls the indexer for the robot.
 * It counts the number of balls taken in,
 * and can set the speed and find the position and speed of the motor.
 * It does this so that the balls taken in can be counted and then
 * stored until they are shot.
 * 
 * @author Matthew McGrath
 * @author Ryan Hefferon
 * @author Owen Biamonte
 */
public class Indexer extends SubsystemBase {

  public double rotations;

  private final SparkMax m_leftMotor = new SparkMax(2, MotorType.kBrushless);
  private final SparkMax m_rightMotor = new SparkMax(3, MotorType.kBrushless);
  private final RelativeEncoder m_encoder = m_leftMotor.getEncoder();
  private final SparkMaxConfig leftMotorConfig = new SparkMaxConfig();
  private final SparkMaxConfig rightMotorConfig = new SparkMaxConfig();

  /** Creates a new Indexer. */
  public Indexer() {
    rightMotorConfig.follow(m_leftMotor, true);
    m_rightMotor.configure(rightMotorConfig, null, null);
  }

  /**checks to see how much the indexer has rotated to determine if there are 5 balls*/
  Trigger full = new Trigger(m_encoder.getPosition()%1 == 5);
  /**checks to see how much the indexer has rotated to determine if there are 4 balls*/
  Trigger fourFull = new Trigger(m_encoder.getPosition()%1 == 4);
  /**checks to see how much the indexer has rotated to determine if there are 3 balls*/
  Trigger threeFull = new Trigger(m_encoder.getPosition()%1 == 3);
  /**checks to see how much the indexer has rotated to determine if there are 2 balls*/
  Trigger twoFull = new Trigger(m_encoder.getPosition()%1 == 2);
  /**checks to see how much the indexer has rotated to determine if there are 1 balls*/
  Trigger oneFull = new Trigger(m_encoder.getPosition()%1 == 1);
  /**checks to see how much the indexer has rotated to determine if there are 0 balls*/
  Trigger empty = new Trigger(m_encoder.getPosition()%1 == 0);

  /**sets the speed of the indexer motor
   * @param speed the speed to run the motor at (-1.0 to 1.0)
  */
  public void setSpeed(double speed) {
    m_leftMotor.set(speed);
  }
  /**stops the speed*/
  public void stop() {
    m_leftMotor.set(0);
  }
  /**finds the speed of the indexer motor
   * @return motor velocity in RPM
  */
  public double getVelocity() {
    return m_encoder.getVelocity();
  }
  /**finds the number of position of the indexer motor
   * @return motor rotations
   */
  public double getPosition() {
    return m_encoder.getPosition();
  }

  public Command spin(double speed) {
    return Commands.runEnd(
        () -> setSpeed(speed),
        () -> stop()).withName("Spin Indexer");
  }
  
  public Command rotate() {
    double rotations = getPosition();
    // Use .until() after the spin until the difference in position is at least 72
    return spin(0.5)
    .until(rotations >= 72);
    withName("Rotate Indexer");
  }

  public void JoystickButton() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
