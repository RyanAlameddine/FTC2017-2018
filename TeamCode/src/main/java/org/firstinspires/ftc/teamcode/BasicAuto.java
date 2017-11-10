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
 * Last Edited October 5, 2017
 *
 * @author Ryan Alameddine
 *
 * @version 1.0
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="BasicAuto", group="Test")
public class BasicAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        ProjectMecanum robot = new ProjectMecanum();

        robot.init(hardwareMap);

        waitForStart();

        robot.frontRight.setPower(-.2);
        robot.frontLeft .setPower(-.2);
        robot.backRight .setPower(-.2);
        robot.backLeft  .setPower(-.2);

        sleep(1500);

        robot.frontRight.setPower(0);
        robot.frontLeft .setPower(0);
        robot.backRight .setPower(0);
        robot.backLeft  .setPower(0);
    }
}
