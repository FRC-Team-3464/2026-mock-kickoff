// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  public Command LowerPivot() {
    if (!getLowMotor()) {
      return Commands.startEnd(()-> setPivotSpeed(-0.5),() -> stopPivot()).until(() -> getLowMotor()).withName("Lower pivot");
    } else {
      return Commands.none();
    }
  }
  public Command RaisePivot() {
    if (!getHighMotor()) {
      return Commands.startEnd(() -> setPivotSpeed(0.5),() -> stopPivot()).until(() -> getHighMotor()).withName("Raise pivot");
    } else {
      return Commands.none();
    }
  }
  public Command RunFlywheel() {
    return Commands.startEnd(() -> setFlywheelMotorSpeed(0.5), () -> stopFlywheel()).withName("Run Flywheel");
  }
  public Command RunIntake() {
    return Commands.startEnd(() -> setIntakeMotorSpeed(0.5),() -> stopIntakeMotor()).withName("Run Intake");
  }
  /** Creates a new Shooter. */ 
  public final SparkMax m_FlywheelMotor = new SparkMax(7, MotorType.kBrushless);
  public final SparkMax m_PivotMotor = new SparkMax(8, MotorType.kBrushless);
  public final SparkMax m_intakeMotor = new SparkMax(9, MotorType.kBrushless);

  public DigitalInput m_topLimitSwitch = new DigitalInput(10);
  public DigitalInput m_bottomLimitSwitch = new DigitalInput(11);

  public RelativeEncoder m_intakeMotorEncoder = m_intakeMotor.getEncoder();
  public RelativeEncoder m_FlywheelMotorEncoder = m_FlywheelMotor.getEncoder();
  public RelativeEncoder m_PivotMotorEncoder = m_PivotMotor.getEncoder();

  private boolean m_isFlywheelRunning = false;
  private boolean m_isPivotMotorRunning = false;
  private boolean m_isIntakeMotorRunning = false;

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

  public boolean getHighMotor() {
    return m_topLimitSwitch.get();
  }
  public boolean getLowMotor() {
    return m_bottomLimitSwitch.get();
  }
  public void setPivotSpeed(double speed){
    m_isPivotMotorRunning = speed!= 0;
    m_PivotMotor.set(speed);
  }

  public void stopPivot() {
    m_isPivotMotorRunning = false;
    m_PivotMotor.set(0);
  }

  public void setIntakeMotorSpeed(double speed) {
    m_isFlywheelRunning = speed != 0;
    m_intakeMotor.set(speed);
  }

  public void stopIntakeMotor() {
    m_intakeMotor.set(0);
  }
  public void stopFlywheel() {
    m_FlywheelMotor.set(0);
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

