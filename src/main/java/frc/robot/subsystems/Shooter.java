// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */ 
  public final SparkMax m_FlywheelMotor = new SparkMax(7, MotorType.kBrushless);
  public final SparkMax m_PivotMotor = new SparkMax(8, MotorType.kBrushless);
  public final SparkMax m_intakeMotor = new SparkMax(9, MotorType.kBrushless);

  public RelativeEncoder m_intakeMotorEncoder = m_intakeMotor.getEncoder();
  public RelativeEncoder m_FlywheelMotorEncoder = m_FlywheelMotor.getEncoder();
  public RelativeEncoder m_PivotMotorEncoder = m_PivotMotor.getEncoder();
  public final DigitalInput m_minimumPivotSwitch = new DigitalInput(10);
  public final DigitalInput m_maximumPivotSwitch = new DigitalInput(11);
  private boolean m_isFlywheelRunning = false;
  private boolean m_ispivotMotorRunning = false;
  private boolean m_isintakeMotorRunning = false;
  public SparkMaxConfig m_intakeMotorConfig;
  public SparkMaxConfig m_PivotMotorConfig;
  public SparkMaxConfig m_FlywheelMotorConfig;
  
  public Shooter() {
    m_FlywheelMotorConfig = new SparkMaxConfig();
    m_PivotMotorConfig = new SparkMaxConfig();
    m_intakeMotorConfig = new SparkMaxConfig();

    m_FlywheelMotor.configure(m_FlywheelMotorConfig, null, null);
    m_PivotMotor.configure(m_PivotMotorConfig, null, null);
    m_intakeMotor.configure(m_intakeMotorConfig, null, null);
  } // floyd

  public void setPivotSpeed(double speed){
    m_ispivotMotorRunning = speed!= 0;
    m_PivotMotor.set(speed);
  }

  public void stopPivot(double speed) {
    m_PivotMotor.set(0);
  }

  public void setIntakeMotorSpeed(double speed) {
    m_isFlywheelRunning = speed != 0;
    m_intakeMotor.set(speed);
  }

  public void stopIntakeMotor() {
    m_intakeMotor.set(0);
  }
  public void setFlywheelMotorSpeed(double speed) {
    m_isFlywheelRunning = speed != 0;
    m_FlywheelMotor.set(speed);
  }
  

  public double getPivotVelocity() {
    return m_PivotMotorEncoder.getPosition();
  }

  public double getPivotPosition() {
    return m_PivotMotorEncoder.getVelocity();
  }
  
  public double getFlywheelVelocity() {
    return m_FlywheelMotorEncoder.getVelocity();
  }

  public double getFlywheelPosition() {
    return m_FlywheelMotorEncoder.getPosition();
  }
}

