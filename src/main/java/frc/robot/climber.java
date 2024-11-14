package frc.robot;

import java.util.Set;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import com.revrobotics.CANSparkLowLevel.MotorType;
    


 public class climber {
        private  CANSparkMax m_upMotor;


      
      private XboxController controller;
      private DigitalInput climberlimitswitch;

      //Constructor
      public climber(CANSparkMax A, XboxController B, DigitalInput C) {
        m_upMotor = A;
        controller=B;
        climberlimitswitch = C;
        
      }

public void Joystick_up_down() {
  System.out.println(climberlimitswitch.get());
  boolean ybuttonispressed = controller.getRawButton(4);
  boolean bbuttonispressed = controller.getRawButton(2);
    if(ybuttonispressed==true&&bbuttonispressed==false) {
        m_upMotor.set(0.8);  }
    if(ybuttonispressed==false&&bbuttonispressed==true) {
        m_upMotor.set(-0.8);  }
    if(ybuttonispressed==true&&bbuttonispressed==true) {
        m_upMotor.set(0);  }
    if(ybuttonispressed==false&&bbuttonispressed==false) {
        m_upMotor.set(0);  }
    if(climberlimitswitch.get()==true){ 
      m_upMotor.set(0);

    }
  } 
   

/* 
  
 if(m_upMotor.getRawButton(5)==true && left==false){
  m_upMotor.set(0.6);

  } else {
   
    m_upMotor.set(0);
  }
  if(m_leftStick.getRawButton(5)==true){
  m_upMotor.set(-0.6);

  } else {
    m_upMotor.set(0);
  }
  //edrg
  */



}


   