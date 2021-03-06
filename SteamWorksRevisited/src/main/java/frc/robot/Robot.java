/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.UsbCamera;
/**
 * This is a demo program showing how to use Mecanum control with the RobotDrive
 * class.
 */
public class Robot extends TimedRobot {
  // Notes: FR is 4, RR is 1, FL is 3, RL = 0, winch=2
  private static final int kFrontLeftChannel = 3;
  private static final int kRearLeftChannel = 0;
  private static final int kFrontRightChannel = 4;
  private static final int kRearRightChannel = 1;

  private static final int kJoystickChannel = 0;

  private MecanumDrive m_robotDrive;
  private SpeedController motor;
  private XboxController m_stick;

  UsbCamera usbcamera = CameraServer.getInstance().startAutomaticCapture();

  @Override
  public void robotInit() {
    PWMVictorSPX frontLeft = new PWMVictorSPX(kFrontLeftChannel);
    PWMVictorSPX rearLeft = new PWMVictorSPX(kRearLeftChannel);
    PWMVictorSPX frontRight = new PWMVictorSPX(kFrontRightChannel);
    PWMVictorSPX rearRight = new PWMVictorSPX(kRearRightChannel);

    // Invert the left side motors.
    // You may need to change or remove this to match your robot.
    /* frontLeft.setInverted(true);
    rearLeft.setInverted(true); */

    m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
    m_stick = new XboxController(kJoystickChannel);
  }

  @Override
  public void teleopPeriodic() {
    // Use the joystick X axis for lateral movement, Y axis for forward
    // movement, and Z axis for rotation.
    m_robotDrive.driveCartesian(m_stick.getX(Hand.kRight), 
                                m_stick.getY(Hand.kRight),
                                m_stick.getX(Hand.kLeft), 
                                0.0);
  }

  @Override
  public void autonomousPeriodic() {
    // Do nothing right
  }
}
