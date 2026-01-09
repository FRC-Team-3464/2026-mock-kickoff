// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Shooter;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class Shooter extends SubsystemBase {

   /**
    * this command tells the pivot to snap to a degree theta
    * @param theta the degree measure to snap to
    * @return the command to snap the pivot
    */
  public Command Snap(double theta) {
    return Commands.startEnd(() -> setPivotSpeed(0.5), () -> stopPivot()).until((theta) -> checkTheta(theta))
  }
   /**
    * this command lowers
    * @return 
    */
   public Command LowerPivot() {
      if (!getLowMotor()) {
         return Commands.startEnd(() -> setPivotSpeed(-0.5), () -> stopPivot()).until(() -> getLowMotor())
               .withName("Lower pivot");
      } else {
         return Commands.none();
      }
   }
   /**
    * raisees the pivot until it stops
    * @return command to raise pivot
    */
   public Command RaisePivot() {
      if (!getHighMotor()) {
         return Commands.startEnd(() -> setPivotSpeed(0.5), () -> stopPivot()).until(() -> getHighMotor())
               .withName("Raise pivot");
      } else {
         return Commands.none();
      }
   }
   
   public Command RunFlywheel() {
      return Commands.startEnd(() -> setFlywheelMotorSpeed(0.5), () -> stopFlywheel()).withName("Run Flywheel");
   }

   public Command RunIntake() {
      return Commands.startEnd(() -> setIntakeMotorSpeed(0.5), () -> stopIntakeMotor()).withName("Run Intake");
   }

   /** Creates a new Shooter. */
   private final SparkMax m_FlywheelMotor = new SparkMax(7, MotorType.kBrushless);
   private final SparkMax m_PivotMotor = new SparkMax(8, MotorType.kBrushless);
   private final SparkMax m_intakeMotor = new SparkMax(9, MotorType.kBrushless);

   private DigitalInput m_topLimitSwitch = new DigitalInput(10);
   private DigitalInput m_bottomLimitSwitch = new DigitalInput(11);
   private DigitalInput m_intakeLimitSwitch = new DigitalInput(67);

   private RelativeEncoder m_intakeMotorEncoder = m_intakeMotor.getEncoder();
   private RelativeEncoder m_FlywheelMotorEncoder = m_FlywheelMotor.getEncoder();
   private RelativeEncoder m_PivotMotorEncoder = m_PivotMotor.getEncoder();

   private boolean m_isFlywheelRunning = false;
   private boolean m_isPivotMotorRunning = false;
   private boolean m_isIntakeMotorRunning = false;

   private SparkMaxConfig m_intakeMotorConfig;
   private SparkMaxConfig m_PivotMotorConfig;
   private SparkMaxConfig m_FlywheelMotorConfig;

   /**
    * this is the constructor for the class
    */
   public Shooter() {
      m_FlywheelMotorConfig = new SparkMaxConfig();
      m_PivotMotorConfig = new SparkMaxConfig();
      m_intakeMotorConfig = new SparkMaxConfig();

      m_FlywheelMotor.configure(m_FlywheelMotorConfig, null, null);
      m_PivotMotor.configure(m_PivotMotorConfig, null, null);
      m_intakeMotor.configure(m_intakeMotorConfig, null, null);
   } // floyd

   /**
    * checks if the current position is equal to a pre-determined "zero value"
    * @return the boolean
    */
   public boolean checkTheta(double theta) {
      double zero = getPivotPosition();
      return (getPivotPosition() == zero);
   }

   public boolean getHighMotor() {
      return m_topLimitSwitch.get();
   }

   public boolean getLowMotor() {
      return m_bottomLimitSwitch.get();
   }
   
   public boolean getIntakeSwitch() {
      return m_intakeLimitSwitch.get();
   }

   public void setPivotSpeed(double speed) {
      m_isPivotMotorRunning = speed != 0;
      m_PivotMotor.set(speed);
   }

   public Trigger canIntake = new Trigger(() -> { return getIntakeSwitch(); });

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
