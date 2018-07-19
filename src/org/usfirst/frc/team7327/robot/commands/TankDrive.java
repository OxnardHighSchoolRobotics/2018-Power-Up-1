package org.usfirst.frc.team7327.robot.commands;

import org.usfirst.frc.team7327.robot.Robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Servo;

public class TankDrive extends Command {

	DigitalInput limitswitch = new DigitalInput(1);

	XboxController Player1 = Robot.oi.Controller0;
	double throttleL = 0.25;
	double throttleX = 0.55;
	DoubleSolenoid.Value Grabbers = DoubleSolenoid.Value.kOff;
	DoubleSolenoid.Value Punchers = DoubleSolenoid.Value.kOff;

	public TankDrive() {
		requires(Robot.drivetrain);
	}

	protected void initialize() {
	}

protected void execute()
{
		System.out.println(limitswitch.get());
		
		Robot.drivetrain.setRaw(Robot.oi.getLeftTrigger(Player1)*+throttleL,Robot.oi.getRightTrigger(Player1)*+throttleL,(Robot.oi.getRightStickY(Player1)*+throttleL));
		
		
		//This is to set the mode for the Arm and the Wheels
		//Start
		
		boolean flag = true;
		
		if(Robot.oi.getXButton(Player1))
		{
			if(flag)
			{
				flag = false;
			}
			else
			{
				flag = true;
			}
		}
	
		//End
		
		
		//Based on the mode set the joy sticks to the correct part of the robot (wheels or arm)
		//Start
		
		if(flag)
		{
			//Robot.drivetrain.setRaw(Robot.oi.getRightstick
		}
		
		
		
		//End
		
		
		
		
		//This is for the grabbers
		
		if(Robot.oi.getRightBumper(Player1)) 
		{
			Grabbers = DoubleSolenoid.Value.kForward;
			Robot.drivetrain.setRawGrabber(Grabbers);
		}
		if(Robot.oi.getLeftBumper(Player1)) 
		{
			Grabbers = DoubleSolenoid.Value.kReverse;
			Robot.drivetrain.setRawGrabber(Grabbers);
		}
		
		
		//This is for the punchers
		if(Robot.oi.getYButton(Player1)) 
		{
			Punchers = DoubleSolenoid.Value.kForward;
			Robot.drivetrain.setPunchers(Punchers);
		}
		if(Robot.oi.getAButton(Player1)) 
		{
			Punchers = DoubleSolenoid.Value.kReverse;
			Robot.drivetrain.setPunchers(Punchers);
		}
		
		
		//This is for the spinners
		
		if( (Robot.oi.Dpad(Player1) >= 0 && Robot.oi.Dpad(Player1) <=45) || (Robot.oi.Dpad(Player1) <=360 && Robot.oi.Dpad(Player1) >=315))
		{
			Robot.drivetrain.setRawSpinner(.3, .3);
		}
		else if(Robot.oi.Dpad(Player1) > 45 && Robot.oi.Dpad(Player1) <= 135)
		{
			Robot.drivetrain.setRawSpinner(-.3, -.3 );
			
		}
		else if(Robot.oi.Dpad(Player1) > 135 && Robot.oi.Dpad(Player1) <= 225)
		{
			Robot.drivetrain.setRawSpinner(.3, -.3 );
		}
		else if(Robot.oi.Dpad(Player1) > 225 && Robot.oi.Dpad(Player1) <= 315)
		{
			Robot.drivetrain.setRawSpinner(-.3, .3 );
		}
		else 
		{
			Robot.drivetrain.setRawSpinner(0, 0);
		}
		
		
	}

	protected boolean isFinished() {
		return false;
	}

	protected void interrupted() {
		end();
	}
}