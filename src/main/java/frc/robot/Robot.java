// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  private final PWMSparkMax m_leftMotor = new PWMSparkMax(4);
  private final PWMSparkMax m_pulley1 = new PWMSparkMax(2);
  private final PWMSparkMax m_pulley2 = new PWMSparkMax(3);
  private final PWMSparkMax m_rightMotor = new PWMSparkMax(1);
  private final DifferentialDrive m_robotDrive =
      new DifferentialDrive(m_leftMotor::set, m_rightMotor::set);
  private final Joystick m_stick = new Joystick(0);
  private final Joystick m_stick2 = new Joystick(1);
  public Robot() {
    SendableRegistry.addChild(m_robotDrive, m_leftMotor);
    SendableRegistry.addChild(m_robotDrive, m_rightMotor);
  }

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightMotor.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    m_robotDrive.tankDrive(-m_stick.getY(), -m_stick2.getY());
    if (m_stick.getRawButton(3)) {
      m_pulley1.set(1);
      m_pulley2.set(1);
    } else if (m_stick.getRawButton(2)) {
      m_pulley1.set(-0.5);
      m_pulley2.set(-0.5);
    } else {
      m_pulley1.set(0);
      m_pulley2.set(0);
    }
  }
}