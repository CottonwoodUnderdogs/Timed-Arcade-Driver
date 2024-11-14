// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private final WPI_VictorSPX m_leftMotor = new WPI_VictorSPX(1);
  private final WPI_VictorSPX m_left2Motor = new WPI_VictorSPX(2);
  private final WPI_VictorSPX m_rightMotor = new WPI_VictorSPX(3);
  private final WPI_VictorSPX m_right2Motor = new WPI_VictorSPX(4);
  private final CANSparkMax m_upMotor = new CANSparkMax(5,MotorType.kBrushless);
  private XboxController controller; 
  private climber Climby;
  private Timer timer = new Timer();
  private DigitalInput climberlimitswitch= new DigitalInput(0);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    
    controller = new XboxController(2);

    Climby = new climber(m_upMotor, controller,climberlimitswitch);

  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

    timer.reset();
    timer.start();

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    double time = timer.get();
//go forward for the right mottor wheels and for the left mottor wheels//
 
    if (time < 2.0) {
      m_leftMotor.set(0.5);
      m_left2Motor.set(0.5);
      m_rightMotor.set(0.5);
      m_right2Motor.set(0.5);
//go backward for the right mottor wheels and for the left mottor wheels//
    }
     else if (time < 5.0) {
    m_leftMotor.set(-0.5);
    m_left2Motor.set(-0.5);
    m_rightMotor.set(-0.5);
    m_right2Motor.set(-0.5);
     }
     // both of the wheels going to the right//

/*  but if we have 
else if (time < 3.0) {
    m_leftMotor.set(-0.5);
    m_left2Motor.set(-0.5);
    m_rightMotor.set(0.5);
    m_right2Motor.set(0.5);
    }
both wheels go to the left
*/


    else if (time < 3.0) {
    m_leftMotor.set(0.5);
    m_left2Motor.set(0.5);
    m_rightMotor.set(-0.5);
    m_right2Motor.set(-0.5);
    }
    // the right mottor wheels and left mottor wheels staying in the same position (speed 0), so doing nothing//
    else {
      m_leftMotor.set(0);
      m_left2Motor.set(0);
      m_rightMotor.set(0);
      m_right2Motor.set(0);

    }


    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    
    controller = new XboxController(2);

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    Climby.Joystick_up_down();
    double Y = -controller.getLeftY();
    double X = controller.getLeftX();
    double maximum;
    maximum = Math.max(Math.abs(X), Math.abs(Y));
    double total; 
    total = X+Y;
    double difference; 
    difference = Y-X;

    if (Y >= 0){
      if (X >= 0){
        m_leftMotor.set(maximum);
        m_left2Motor.set(maximum);
        m_rightMotor.set(difference);
        m_right2Motor.set(difference);
        }
        else {
        m_leftMotor.set(total);
        m_left2Motor.set(total);
        m_rightMotor.set(maximum);
        m_right2Motor.set(maximum);

        }
      }
      else {
        if (X >= 0){
        m_leftMotor.set(total);
        m_left2Motor.set(total);
        m_rightMotor.set(-maximum);
        m_right2Motor.set(-maximum);
        }
        else {
        m_leftMotor.set(-maximum);
        m_left2Motor.set(-maximum);
        m_rightMotor.set(difference);
        m_right2Motor.set(difference);
      }
      }

  }

    /** This gets the x and y values which represents the speed and angle for the robot
  
   
    




  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}







  





  }
  

//

