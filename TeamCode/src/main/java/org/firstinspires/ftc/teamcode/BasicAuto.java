package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Simple Mecanum controller
 *
 * Created September 26, 2017 by Ryan Alameddine
 *
 * @author Ryan Alameddine
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="BasicAuto", group="Test")
public class BasicAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        ProjectMecanum robot = new ProjectMecanum();

        robot.init(hardwareMap);
        
        waitForStart();

        robot.claw.setPosition(1);

        sleep(500);

        robot.frontRight.setPower(-.2);
        robot.frontLeft .setPower(-.2);
        robot.backRight .setPower(-.2);
        robot.backLeft  .setPower(-.2);

        sleep(1750);

        robot.claw.setPosition(0);

        robot.frontRight.setPower(-.3);
        robot.frontLeft .setPower(-.3);
        robot.backRight .setPower(-.3);
        robot.backLeft  .setPower(-.3);

        sleep(1000);

        robot.frontRight.setPower( .3);
        robot.frontLeft .setPower( .3);
        robot.backRight .setPower( .3);
        robot.backLeft  .setPower( .3);

        sleep(500);

        robot.frontRight.setPower(0);
        robot.frontLeft .setPower(0);
        robot.backRight .setPower(0);
        robot.backLeft  .setPower(0);
    }
}
