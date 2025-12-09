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
  public Shooter() {} // floyd

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
