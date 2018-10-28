package org.usfirst.frc.team7327.robot.commands;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.opencv.core.RotatedRect;
import org.usfirst.frc.team7327.robot.Robot;


//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Servo;

public class SwerveDrive extends Command {
	public SwerveDrive() {
		requires(Robot.drivetrain); 
	}

	
	public static XboxController Player1 = Robot.oi.Controller0; 
	protected void initialize() { 
		
	}

	double setDegree = 0;
	
	protected void execute(){
		
		SmartDashboard.putNumber("Gyro: ", Robot.GyroAngle());
		SmartDashboard.putNumber("Angular Setpoint", Robot.drivetrain.getSteeringSetpoint());
		SmartDashboard.putNumber("Angular Error", Robot.drivetrain.getSteeringError());
		SmartDashboard.putNumber("Angular Position", Robot.drivetrain.getSteeringPosition());
		
		double Lx = Robot.oi.getLeftStickX(Player1); 
		double Ly = Robot.oi.getLeftStickY(Player1); 
		
		double Rx = Robot.oi.getRightStickX(Player1); 
		double Ry = Robot.oi.getRightStickY(Player1);
		
		double LT = Robot.oi.getLeftTrigger(Player1); 
		double RT = Robot.oi.getRightTrigger(Player1); 
		boolean StartButton = Robot.oi.getStartButton(Player1); 
		
		
		double degrees = Math.toDegrees(Math.atan2(Ly,  Lx)) + 90;
		double magnitude = Math.sqrt(Math.pow(Lx, 2) + Math.pow(Ly, 2));
		

		double degreesR = Math.toDegrees(Math.atan2(Ry,  Rx)) + 90;
		double magnitudeR = Math.sqrt(Math.pow(Rx, 2) + Math.pow(Ry, 2));
		
		if(StartButton) Robot.gyro.reset();
		
		 
		
		if(magnitudeR > .5) {
			setDegree = 360-degreesR;
		}
		System.out.println(magnitudeR);
		
		
		Robot.drivetrain.setAllSpeed(Ly-RT+LT);
		Robot.drivetrain.setAllDegrees(setDegree+Robot.GyroAngle());
	}
	
	protected boolean isFinished() {

		return false;
	}

	protected void interrupted() {
		end();
	}
}
