package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.AutoLog;

public interface ArmIO {
   default void updateInputs(ArmIOInputs inputs) {

   }

   @AutoLog
   public class ArmIOInputs {;
      double armPositionRotations = 0.0;
      double velocity = 0.0; // as rpm
   }
   void setMotorSpeed(double speed);
}