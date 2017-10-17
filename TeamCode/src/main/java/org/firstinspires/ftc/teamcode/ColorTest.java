package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


/**
 * Test for color sensor
 *
 * Created October 10, 2017 by Ryan Alameddine
 *
 * Last Edited
 *
 * @author Ryan Alameddine
 *
 * @version 1.0
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Autonomous", group="Test")
public class ColorTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        ProjectMecanum robot = new ProjectMecanum();

        robot.init(hardwareMap);

        waitForStart();

        while(opModeIsActive()){
            robot.colorSensor.enableLed(true);
            telemetry.addData("ARGB", robot.colorSensor.argb());
        }
    }
}
