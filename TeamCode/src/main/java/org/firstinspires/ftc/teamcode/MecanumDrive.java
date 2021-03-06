package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

import static android.R.attr.orientation;
import static android.R.attr.rotationX;
import static android.R.attr.textAppearanceMedium;

/**
 * Simple Mecanum controller
 *
 * Created September 21, 2017 by Ryan Alameddine
 *
 * @author Ryan Alameddine
 */

@TeleOp(name="MecanumDrive", group="Mecanum")
public class MecanumDrive extends LinearOpMode{
    private Project0 robot = new Project0();

    /* Setting variables */

    //Speed multiplier. The higher it is, the more likely to clip at high speeds because motor max is 1
    private float speedMultiplier = 1;

    //Speed multiplier when slow mode is active
    private float slowModeMultiplier = .4f;

    /* Calculation variables DO NOT CHANGE */

    //Robot controls:
    private float speed       = 0;
    private float angle       = 0;
    private float direction   = 0;
    private VectorF vectorF   = null;
    private boolean isTurning = false;
    private float rotation    = 0;

    //Individual motor powers:
    private float frontLeft   = 0;
    private float frontRight  = 0;
    private float backLeft    = 0;
    private float backRight   = 0;

    //Highest motor power: (used for clipping high speeds above 1)
    private float greatestNum = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize ProjectMecanum with hardwareMap configuration
        robot.init(hardwareMap);
        waitForStart();

        while(opModeIsActive()) {
            //region A
            //Flip 180 degrees on A press
            if(gamepad1.a && !isTurning){
                isTurning = true;
                robot.imu.startAccelerationIntegration(new Position(), new Velocity(), 5);
                Orientation orientation = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                rotation = Float.parseFloat(MathE.formatAngle(orientation.angleUnit, orientation.firstAngle));
                if(rotation > 0){
                    rotation = rotation - 180;
                }else if(rotation < 0){
                    rotation = rotation + 180;
                }
            }

            if(isTurning){
                Orientation orientation = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                float Angle = Float.parseFloat(MathE.formatAngle(orientation.angleUnit, orientation.firstAngle));
                // -180 0 180

                telemetry.addData("Angle", Angle);
                telemetry.update();
                if(Math.abs(rotation - Angle) > 30 && Math.abs(rotation - Angle + 360) > 30){
                    robot.backLeft  .setPower(-.6); // b f
                    robot.backRight .setPower(0.6); // b f
                    robot.frontLeft .setPower(-.6);
                    robot.frontRight.setPower(0.6);

                    continue;
                }else{
                    robot.imu.stopAccelerationIntegration();
                    isTurning = false;
                }
            }
            //endregion

            //region Mecanum Drive math and controls
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

            //Make sure that speed never exceeds 1. If so, divide by largest
            greatestNum = Math.abs(frontLeft);
            if (Math.abs(frontRight) > greatestNum) {
                greatestNum = Math.abs(frontRight);
            }
            if (Math.abs(backLeft)   > greatestNum) {
                greatestNum = Math.abs(backLeft);
            }
            if (Math.abs(backRight)  > greatestNum) {
                greatestNum = Math.abs(backRight);
            }

            if (greatestNum > 1) {
                frontLeft  /= greatestNum;
                frontRight /= greatestNum;
                backLeft   /= greatestNum;
                backRight  /= greatestNum;
            }
            //endregion

            robot.frontLeft .setPower(Float.isNaN(frontLeft)  ? 0 : frontLeft  * (gamepad1.left_trigger > .2 ? 1 : slowModeMultiplier));
            robot.frontRight.setPower(Float.isNaN(frontRight) ? 0 : frontRight * (gamepad1.left_trigger > .2 ? 1 : slowModeMultiplier));
            robot.backLeft  .setPower(Float.isNaN(backLeft)   ? 0 : backLeft   * (gamepad1.left_trigger > .2 ? 1 : slowModeMultiplier));
            robot.backRight .setPower(Float.isNaN(backRight)  ? 0 : backRight  * (gamepad1.left_trigger > .2 ? 1 : slowModeMultiplier));


            robot.claw.setPosition((gamepad1.right_trigger + .4) / 1.3);

            if(gamepad1.dpad_up){
                robot.arm.setPower(.1f);
            }else if(gamepad1.dpad_down){
                robot.arm.setPower(-.1f);
            }else{
                robot.arm.setPower(0);
            }
        }

        //Reset robot motors to stop when game is finished
        robot.frontLeft .setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft  .setPower(0);
        robot.backRight .setPower(0);
        robot.arm       .setPower(0);
    }
}
