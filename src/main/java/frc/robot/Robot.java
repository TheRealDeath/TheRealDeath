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
  private Joystick leftStick;
  private static final int leftDeviceID1 = 3;
  private static final int leftDeviceID2 = 4;  
  private static final int rightDeviceID1 = 5;
  private static final int rightDeviceID2 = 6;
  private static final int armDeviceID1 = 7;
  private static final int intakeDeviceId1 = 8;
  private CANSparkMax m_leftMotor1;
  private CANSparkMax m_rightMotor1;
  private CANSparkMax m_leftMotor2;
  private CANSparkMax m_rightMotor2;
  private CANSparkMax m_armMotor1;
  private CANSparkMax m_intakeMotor1;
  private int previousPOV;
  private double previousThrottle;

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
    m_armMotor1 = new CANSparkMax(armDeviceID1,MotorType.kBrushless);
    m_intakeMotor1 = new CANSparkMax(intakeDeviceId1,MotorType.kBrushless);
    m_intakeMotor1.setSmartCurrentLimit(30);
    MotorControllerGroup m_left = new MotorControllerGroup(m_leftMotor1, m_leftMotor2);
    MotorControllerGroup m_right = new MotorControllerGroup(m_rightMotor1, m_rightMotor2);

    m_leftMotor1.restoreFactoryDefaults();
    m_rightMotor1.restoreFactoryDefaults();
    m_leftMotor2.restoreFactoryDefaults();
    m_rightMotor2.restoreFactoryDefaults();
    m_armMotor1.restoreFactoryDefaults();
    m_intakeMotor1.restoreFactoryDefaults();

    m_myRobot = new DifferentialDrive(m_left, m_right);

    leftStick = new Joystick(0);
    leftStick.toString();
  }

  @Override
  public void teleopPeriodic() {
    //m_myRobot.tankDrive(-leftStick.getRawAxis(1), leftStick.getRawAxis(5));
    m_myRobot.arcadeDrive(leftStick.getRawAxis(1), leftStick.getRawAxis(5));
    int i = 0;
    if(leftStick.getPOV() != previousPOV)
    {
      System.out.println(Math.abs(leftStick.getPOV()-previousPOV));
    }
    if(leftStick.getRawButton(3))
    {
      while(true)
      {
        System.out.println("going up");
        if(i++ == 20)
          break;
      }
    }
    if(leftStick.getRawAxis(1) != previousThrottle)
    {
      System.out.println(leftStick.getRawAxis(1));
    }
    previousThrottle = leftStick.getRawAxis(1);
    previousPOV = leftStick.getPOV();
  }
}