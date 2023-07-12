package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase{
    TalonSRX backRight;
    TalonSRX backLeft;
    TalonSRX frontRight;
    TalonSRX frontLeft;
    public DriveSubsystem(){
        backLeft = new TalonSRX(2);
        backRight = new TalonSRX(5);
        frontLeft = new TalonSRX(0);
        frontRight = new TalonSRX(3);
    }
    public void drive(double leftSide, double rightSide){
        backLeft.set(ControlMode.PercentOutput, leftSide);
        frontLeft.set(ControlMode.PercentOutput, leftSide);
        backRight.set(ControlMode.PercentOutput, rightSide);
        frontRight.set(ControlMode.PercentOutput, rightSide);
    }
    
}
