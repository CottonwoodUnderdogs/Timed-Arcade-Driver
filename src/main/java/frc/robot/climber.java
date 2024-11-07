package frc.robot;

import java.util.Set;
import edu.wpi.first.wpilibj.XboxController;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.Joystick;
import com.revrobotics.CANSparkLowLevel.MotorType;
    


 public class climber {
        private  CANSparkMax m_upMotor;


      
      private XboxController controller;

      //Constructor
      public climber(CANSparkMax A, XboxController B ) {
        m_upMotor = A;
        controller=B;
      }

public void Joystick_up_down() {
    if(controller.getRawButton(4)==true&&controller.getRawButton(2)==false) {
        m_upMotor.set(0.8);  }
    if(controller.getRawButton(4)==false&&controller.getRawButton(2)==true) {
        m_upMotor.set(-0.8);  }
    if(controller.getRawButton(4)==true&&controller.getRawButton(2)==true) {
        m_upMotor.set(0);  }
    if(controller.getRawButton(4)==false&&controller.getRawButton(2)==false) {
        m_upMotor.set(0);  }
    

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

}
*/





  
    








   


    
}
   