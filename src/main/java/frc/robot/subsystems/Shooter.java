// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */ 
  public final SparkMax m_leftFlywheelMotor = new SparkMax(7, MotorType.kBrushless);
  public final SparkMax m_rightFlywheelMotor = new SparkMax(8, MotorType.kBrushless);
  public final SparkMax m_leftPivotMotor = new SparkMax(9, MotorType.kBrushless);
  public final SparkMax m_rightPivotMotor = new SparkMax(10, MotorType.kBrushless);

  public RelativeEncoder m_leftFlywheelMotorEncoder = m_leftFlywheelMotor.getEncoder();
  public RelativeEncoder m_leftPivotMotorEncoder = m_leftPivotMotor.getEncoder();

  private boolean is_Flywheel_running = false;
  private boolean is_pivotMotor_running = false;
  public SparkMaxConfig m_leftPivotMotorConfig;
  public SparkMaxConfig m_rightPivotMotorConfig;
  public SparkMaxConfig m_leftFlywheelMotorConfig;
  public SparkMaxConfig m_rightFlywheelMotorConfig;
  
  public Shooter() {
    m_leftFlywheelMotorConfig = new SparkMaxConfig();
    m_rightFlywheelMotorConfig = new SparkMaxConfig();
    m_leftPivotMotorConfig = new SparkMaxConfig();
    m_rightPivotMotorConfig = new SparkMaxConfig();

    m_rightPivotMotorConfig.follow(m_rightPivotMotor, true);
    m_rightFlywheelMotorConfig.follow(m_leftFlywheelMotor, true);

    m_leftFlywheelMotor.configure(m_leftFlywheelMotorConfig, null, null);
    m_rightFlywheelMotor.configure(m_rightFlywheelMotorConfig, null, null);
    m_leftPivotMotor.configure(m_leftFlywheelMotorConfig, null, null);
    m_rightPivotMotor.configure(m_rightFlywheelMotorConfig, null, null);
  } // floyd
  public void setPivotSpeed(double speed){
    is_pivotMotor_running = speed!= 0;
    m_leftPivotMotor.set(speed);
    m_leftPivotMotor.set(speed);
  }

  public void stopPivot(double speed){
    m_leftPivotMotor.set(0);
  }

  public double getPivotVelocity(){
    return m_leftPivotMotorEncoder.getPosition();
  }

  public double getPivotPosition(){
    return m_leftPivotMotorEncoder.getVelocity();
  }
  
  public void setFlywheelMotorSpeed(double speed) {
    is_Flywheel_running = speed != 0;
    m_leftFlywheelMotor.set(speed);
    m_rightFlywheelMotor.set(speed);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
