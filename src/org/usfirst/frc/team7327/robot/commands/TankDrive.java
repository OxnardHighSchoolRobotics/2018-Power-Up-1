package org.usfirst.frc.team7327.robot.commands;

import org.usfirst.frc.team7327.robot.Robot;


//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Servo;

public class TankDrive extends Command {
	public TankDrive() {
		requires(Robot.drivetrain); 
	} 
	
	
	boolean isGrabbed = false;
	boolean isPunched = false;
	XboxController Player1 = Robot.oi.Controller0; 
	double throttleL = .25;
	double throttleA = .55;
	protected void initialize() {
	
	}
	
	
	
	protected void execute(){
		DoubleSolenoid.Value kForward = DoubleSolenoid.Value.kForward;
		DoubleSolenoid.Value kReverse = DoubleSolenoid.Value.kReverse;
		DoubleSolenoid.Value kOff = DoubleSolenoid.Value.kOff;
		
		Robot.drivetrain.setRaw((-Robot.oi.getLeftStickY(Player1) - Robot.oi.getRightStickX(Player1))*throttleL, (-Robot.oi.getLeftStickY(Player1) + Robot.oi.getRightStickX(Player1))*throttleL, Robot.oi.getRightStickY(Player1)*throttleA);
		Robot.drivetrain.setRawSpinner(Robot.oi.getLeftStickX(Player1),-Robot.oi.getLeftStickX(Player1) );
		if(!isPunched && Robot.oi.getLeftTrigger(Player1) == 1){
			Robot.drivetrain.setPunchers(kForward);
			isPunched = true;
		}
		else if(isPunched && Robot.oi.getLeftTrigger(Player1) == 1){
			Robot.drivetrain.setPunchers(kReverse);
			isPunched = false;
		}
		if(!isGrabbed && Robot.oi.getRightTrigger(Player1) == 1){
			Robot.drivetrain.setRawGrabber(kForward);
			isGrabbed = true;
		}
		
		else if(isGrabbed && Robot.oi.getRightTrigger(Player1) == 1){
			Robot.drivetrain.setRawGrabber(kReverse);
			isGrabbed = false;
		}		
	}

	
	protected boolean isFinished() {

		return false;
	}

	protected void interrupted() {
		end();
	}
}
