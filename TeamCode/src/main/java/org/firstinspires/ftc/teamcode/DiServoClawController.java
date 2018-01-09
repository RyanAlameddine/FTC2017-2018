package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * Simple Mecanum drive configuration
 *
 * Created December 1, 2017 by Ryan Alameddine
 *
 *
 * @author Ryan Alameddine
 */

public class DiServoClawController {
    public Servo Left;
    public Servo Right;

    public DiServoClawController(Servo left, Servo right){
        Left  = left;
        Right = right;
    }

    public void setPosition(double position){
        Left .setPosition(  position);
        Right.setPosition(1-position);
    }
}
