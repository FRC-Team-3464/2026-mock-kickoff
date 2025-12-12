// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalGlitchFilter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class RetractPivot extends Command {
  private final Intake m_subsystem;

  /** Creates a new RetractPivot. */
  public RetractPivot(Intake subsystem) {
    m_subsystem = subsystem;
    addRequirements(m_subsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!m_subsystem.getInnerLimit()) {
      m_subsystem.setPivotSpeed(-0.5);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.stopPivot();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_subsystem.getInnerLimit();
  }
}
