// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  
  private DifferentialDrive m_myRobot;
  private Joystick m_leftStick;
  private Joystick m_rightStick;
  private static final int leftDeviceID1 = 3;
  private static final int leftDeviceID2 = 4;  
  private static final int rightDeviceID1 = 5;
  private static final int rightDeviceID2 = 6;
  private CANSparkMax m_leftMotor1;
  private CANSparkMax m_rightMotor1;
  private CANSparkMax m_leftMotor2;
  private CANSparkMax m_rightMotor2;

  @Override
  public void robotInit() {
  /**
   * SPARK MAX controllers are intialized over CAN by constructing a CANSparkMax object
   * 
   * The CAN ID, which can be configured using the SPARK MAX Client, is passed as the
   * first parameter
   * 
   * The motor type is passed as the second parameter. Motor type can either be:
   *  com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless
   *  com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushed
   * 
   * The example below initializes four brushless motors with CAN IDs 1 and 2. Change
   * these parameters to match your setup
   */
    m_leftMotor1 = new CANSparkMax(leftDeviceID1, MotorType.kBrushless);
    m_rightMotor1 = new CANSparkMax(rightDeviceID1, MotorType.kBrushless);
    m_leftMotor2 = new CANSparkMax(leftDeviceID2, MotorType.kBrushless);
    m_rightMotor2 = new CANSparkMax(rightDeviceID2, MotorType.kBrushless);
    MotorControllerGroup m_left = new MotorControllerGroup(m_leftMotor1, m_leftMotor2);
    MotorControllerGroup m_right = new MotorControllerGroup(m_rightMotor1, m_rightMotor2);

    /**
     * The RestoreFactoryDefaults method can be used to reset the configuration parameters
     * in the SPARK MAX to their factory default state. If no argument is passed, these
     * parameters will not persist between power cycles
     */
    m_leftMotor1.restoreFactoryDefaults();
    m_rightMotor1.restoreFactoryDefaults();
    m_leftMotor2.restoreFactoryDefaults();
    m_rightMotor2.restoreFactoryDefaults();

    m_myRobot = new DifferentialDrive(m_left, m_right);

    m_leftStick = new Joystick(0);
  }

  @Override
  public void teleopPeriodic() {
    m_myRobot.tankDrive(-m_leftStick.getRawAxis(1), m_leftStick.getRawAxis(5));
  }
}
