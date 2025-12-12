package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;

public class Shoot extends Command {
  private Shooter m_subsystem = new Shooter();
  public Shoot(Shooter subsystem) {
    m_subsystem = subsystem;
    addRequirements(m_subsystem);
  }

  // Called when the command is 
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.setIntakeMotorSpeed(0.5);
    m_subsystem.setFlywheelMotorSpeed(0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
