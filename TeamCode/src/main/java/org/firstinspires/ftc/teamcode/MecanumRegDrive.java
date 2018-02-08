package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

/**
 * Simple Mecanum controller
 *
 * Created September 21, 2017 by Ryan Alameddine
 *
 * @author Ryan Alameddine
 */

@TeleOp(name="MecanumRegDrive", group="Mecanum")
public class MecanumRegDrive extends LinearOpMode{
    private Project0 robot = new Project0();

    /* Setting variables */

    //Speed multiplier. The higher it is, the more likely to clip at high speeds because motor max is 1
    private float speedMultiplier = 1;

    //Speed multiplier when slow mode is active
    private float slowModeMultiplier = .4f;

    //Individual motor powers:
    private float frontLeft   = 0;
    private float frontRight  = 0;
    private float backLeft    = 0;
    private float backRight   = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize ProjectMecanum with hardwareMap configuration
        robot.init(hardwareMap);
        waitForStart();

        while(opModeIsActive()) {
            backRight  = gamepad1.right_stick_y;
            frontRight = backRight;
            backLeft   = gamepad1.left_stick_y;
            frontLeft  = backLeft;


            robot.frontLeft .setPower(Float.isNaN(frontLeft)  ? 0 : frontLeft  * (gamepad1.right_trigger > .2 ? 1 : slowModeMultiplier));
            robot.frontRight.setPower(Float.isNaN(frontRight) ? 0 : frontRight * (gamepad1.right_trigger > .2 ? 1 : slowModeMultiplier));
            robot.backLeft  .setPower(Float.isNaN(backLeft)   ? 0 : backLeft   * (gamepad1.right_trigger > .2 ? 1 : slowModeMultiplier));
            robot.backRight .setPower(Float.isNaN(backRight)  ? 0 : backRight  * (gamepad1.right_trigger > .2 ? 1 : slowModeMultiplier));


            robot.claw.setPosition((gamepad1.right_trigger + .4) / 1.3);

            if(gamepad1.y){
                robot.arm.setPower(.1f);
            }else if(gamepad1.a){
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
