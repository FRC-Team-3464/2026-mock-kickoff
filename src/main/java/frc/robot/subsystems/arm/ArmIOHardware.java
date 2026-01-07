package frc.robot.subsystems.arm;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class ArmIOHardware implements ArmIO {
   private final SparkMax m_left = new SparkMax(6, MotorType.kBrushless);
   private final SparkMax m_right = new SparkMax(7, MotorType.kBrushless);
   @Override
   public void setMotorSpeed(double speed) {
      throw new UnsupportedOperationException("Unimplemented method motorspeed");
   }
   @Override
   public void updateInputs(ArmIOInputs inputs) {
      
   }
}
