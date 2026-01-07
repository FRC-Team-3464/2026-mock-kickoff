package frc.robot.subsystems.Shooter;

import org.littletonrobotics.junction.AutoLog;

public interface ShooterIO {
   
   @AutoLog
   public class ArmIOInputs {
      double armPositionRotations = 0.0;
      double velocity = 0.0;
   }
   
}
