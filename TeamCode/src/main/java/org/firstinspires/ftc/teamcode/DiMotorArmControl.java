package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Simple Mecanum drive configuration
 *
 * Created December 1, 2017 by Ryan Alameddine
 *
 *
 * @author Ryan Alameddine and hackerofthesystems
 */

public class DiMotorArmControl {
    public DcMotor Left;
    public DcMotor Right;

    public DiMotorArmControl(DcMotor left, DcMotor right){
        Left  = left;
        Right = right;
    }

    public void setPower(double power){
        Left .setPower(power);
        Right.setPower(power);
    }
}
