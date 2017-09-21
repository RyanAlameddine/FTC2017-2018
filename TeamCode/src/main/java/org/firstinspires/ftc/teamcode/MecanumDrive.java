package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;

/**
 * Simple Mecanum controller
 *
 * Created September 21, 2017 by Ryan Alameddine
 *
 * Last Edited
 *
 * @author Ryan Alameddine
 *
 * @version 1.0
 */

@TeleOp(name="MecanumDrive", group="Mecanum")
public class MecanumDrive extends LinearOpMode{
    ProjectMecanum robot = new ProjectMecanum();

    float speed           = 0;
    float angle           = 0;
    float speedMultiplier = 1;
    float direction       = 0;
    VectorF vectorF;

    float frontLeft;
    float frontRight;
    float backLeft;
    float backRight;

    float greatestNum = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize ProjectMecanum with hardwareMap configuration
        robot.init(hardwareMap);
        waitForStart();

        while(opModeIsActive()) {
            //Get the 2 dimensional vector of the direction of left stick and rotation based on right stick
            vectorF    = new VectorF(-gamepad1.left_stick_x, gamepad1.left_stick_y);
            speed      = vectorF.magnitude();
            vectorF    = new VectorF(vectorF.get(0) / speed, vectorF.get(1) / speed);
            angle      = (float) Math.atan2(vectorF.get(0), vectorF.get(1));
            direction  = gamepad1.right_stick_x * -1;

            //Apply mathematical operations to find speeds of each motor
            frontLeft  = (float) (speed * Math.sin(angle + Math.PI / 4) + direction) * speedMultiplier;
            frontRight = (float) (speed * Math.cos(angle + Math.PI / 4) - direction) * speedMultiplier;
            backLeft   = (float) (speed * Math.cos(angle + Math.PI / 4) + direction) * speedMultiplier;
            backRight  = (float) (speed * Math.sin(angle + Math.PI / 4) - direction) * speedMultiplier;

            //Make sure that speed never excedes 1. If so, divide by largest
            greatestNum = Math.abs(frontLeft);
            if (Math.abs(frontRight) > greatestNum) {
                greatestNum = Math.abs(frontRight);
            }
            if (Math.abs(backLeft) > greatestNum) {
                greatestNum = Math.abs(backLeft);
            }
            if (Math.abs(backRight) > greatestNum) {
                greatestNum = Math.abs(backRight);
            }
            if (greatestNum > 1) {
                frontLeft /= greatestNum;
                frontRight /= greatestNum;
                backLeft /= greatestNum;
                backRight /= greatestNum;
            }

            robot.frontLeft.setPower(Float.isNaN(frontLeft) ? 0 : frontLeft);
            robot.frontRight.setPower(Float.isNaN(frontRight) ? 0 : frontRight);
            robot.backLeft.setPower(Float.isNaN(backLeft) ? 0 : backLeft);
            robot.backRight.setPower(Float.isNaN(backRight) ? 0 : backRight);
        }
        //Reset robot motors to stop when game is finished
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);
    }
}