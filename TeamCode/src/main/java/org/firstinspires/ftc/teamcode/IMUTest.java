package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

/**
 * Simple Mecanum controller
 *
 * Created November 7, 2017 by Ryan Alameddine
 *
 * @author Ryan Alameddine
 *
 * @version 1.0
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="IMUTest", group="Test")
public class IMUTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        ProjectMecanum robot = new ProjectMecanum();

        robot.init(hardwareMap);

        waitForStart();

        robot.imu.startAccelerationIntegration(new Position(), new Velocity(), 500);

        while(opModeIsActive()){
            Orientation orientation = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            telemetry.addData("Orientation", MathE.formatAngle(orientation.angleUnit, orientation.firstAngle));
            telemetry.update();
        }
    }
}
