package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase{
    VictorSP intake5 = new VictorSP(0);
    VictorSP intake3 = new VictorSP(1);
    WPI_VictorSPX intake4 = new WPI_VictorSPX(3);
    WPI_VictorSPX intake1 = new WPI_VictorSPX(2);
    WPI_VictorSPX intake2 = new WPI_VictorSPX(4);
    TalonSRX rightShooter = new TalonSRX(1);
    VictorSP leftShooter = new VictorSP(2);

    int counter = 0;
    boolean shooting = false;
    ArrayList<DigitalInput> inputs = new ArrayList<DigitalInput>(List.of(new DigitalInput(4), new DigitalInput(3), new DigitalInput(2), new DigitalInput(1), new DigitalInput(0)));
    ArrayList<MotorController> motors = new ArrayList<MotorController>(List.of(intake5,intake4,intake3,intake2,intake1));

    public ShooterSubsystem(){
        intake3.setInverted(true);
        leftShooter.setInverted(true);
        for (int i = 0; i < motors.size(); i++){
            motors.get(i).set(Constants.intakeSpeed);
        }
    }
    public boolean querySensors(int sensor){
        return !inputs.get(sensor).get();
    }
    public void stopMotor(int motor){
        motors.get(motor).set(0);
    }
    public void shoot(){
        shooting = true;
        counter = 0;
        leftShooter.set(.50);
        rightShooter.set(ControlMode.PercentOutput, .50);
        Commands.waitSeconds(.75);
        for (int i = 0; i < motors.size(); i++){
            motors.get(i).set(Constants.intakeSpeed);
        }
        
    }
    public void stopShooting(){
        shooting = false;

        leftShooter.set(0);
        rightShooter.set(ControlMode.PercentOutput, 0);

    }

    @Override
    public void periodic() {
        // TODO Auto-generated method stub
        super.periodic();
        if(!shooting){
            if (counter < 5){
                if (querySensors(counter)){
                    stopMotor(counter);
                    counter++;
                }
            }
            
        }
        
    }

    
}
