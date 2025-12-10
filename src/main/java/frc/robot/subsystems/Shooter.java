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
  public final SparkMax m_PivotMotor = new SparkMax(9, MotorType.kBrushless);


  public RelativeEncoder m_leftFlywheelMotorEncoder = m_leftFlywheelMotor.getEncoder();
  public RelativeEncoder m_PivotMotorEncoder = m_PivotMotor.getEncoder();
  private boolean is_Flywheel_running = false;
  private boolean is_pivotMotor_running= false;
  public SparkMaxConfig m_leftPivotMotorConfig;
  public SparkMaxConfig m_leftFlywheelMotorConfig;
  public SparkMaxConfig m_rightFlywheelMotorConfig;
  
  public Shooter() {
    m_FlywheelMotorConfig = new SparkMaxConfig();
    m_rightFlywheelMotorConfig = new SparkMaxConfig();
    m_leftPivotMotorConfig = new SparkMaxConfig();

    m_rightFlywheelMotorConfig.follow(m_leftFlywheelMotor, true);

    m_leftFlywheelMotor.configure(m_leftFlywheelMotorConfig, null, null);
    m_rightFlywheelMotor.configure(m_leftFlywheelMotorConfig, null, null);
    m_PivotMotor.configure(m_leftFlywheelMotorConfig, null, null);
  } 
  // floyd
  public void setPivotSpeed(double speed){
    is_pivotMotor_running = speed!= 0;
    m_PivotMotor.set(speed);
  }

  public void stopPivot(double speed){
    m_PivotMotor.set(0);
  }

  public double getPivotVelocity(){
    return m_PivotMotorEncoder.getPosition();
  }

  public double getPivotPosition(){
    return m_PivotMotorEncoder.getVelocity();
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
