package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

/**
 * Simple Mecanum controller
 *
 * Created September 26, 2017 by Ryan Alameddine
 *
 *
 * @author Ryan Alameddine
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="AutonomousRed", group="Test")
public class AutonomousRed extends LinearOpMode {
    //VuforiaLocalizer localizer;

    @Override
    public void runOpMode() throws InterruptedException {
        Project0 robot = new Project0();

        //region Vuforia
        /*
        //Vuforia Setup
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        params.vuforiaLicenseKey = "AdLSetb/////AAAAGbm2rHS0ck7Fgxwjtp3Cbclm98DyEfx+PLZ+VgF6AjcoFsOoMwgjWair2KgZLmc9MwR74NxG2WqBPqWs4ocmgQ0DyEnDW0tSzgUhH/UgBobUpmHrqSY5htttuRw6OKo9/A+3t39YCQj0+qxjsIj6cg/bStC8lI11ZMYukRnCSKLQQOVGxAbe0CuL7cBQ34gc8hqxOzk1gVXyj+U9XxxjKnJ18qiCcisprtAaRuRB6xzP8MzUQoql0Ajn8ldXW3mZSKjc3tq0LPYDwYmAaKkAxNz/jabhUk3m4Gyti5ApeYtw8yWA0AkKum8Fb8W/VTnc6FckH4BXgXOcDng++FTw9vihPiSJ7a36I2hU1Q8+NnuC";
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK; this.localizer = ClassFactory.createVuforiaLocalizer(params);

        //Load relic data
        VuforiaTrackables relicTrackables = this.localizer.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

        robot.init(hardwareMap);

        //Start searching for relics
        relicTrackables.activate();

        waitForStart();

        //Identify Relic
        RelicRecoveryVuMark vuMark;
        do{
            vuMark = RelicRecoveryVuMark.from(relicTemplate);
        } while(vuMark == RelicRecoveryVuMark.UNKNOWN);

        telemetry.addData("VuMark", vuMark + " Visible");
        telemetry.update();
        */
        //endregion Vuforia

        robot.init(hardwareMap);

        waitForStart();

        robot.colorSensor.enableLed(true);
        robot.JewelArmServo.setPosition(1);
        sleep(2000);

        if(robot.colorSensor.red() > robot.colorSensor.blue()){
            robot.backLeft  .setPower(.2f);
            robot.backRight .setPower(.2f);
            robot.frontLeft .setPower(.2f);
            robot.frontRight.setPower(.2f);
        }else{
            robot.backLeft  .setPower(-.2f);
            robot.backRight .setPower(-.2f);
            robot.frontLeft .setPower(-.2f);
            robot.frontRight.setPower(-.2f);
        }
        sleep(200);
        robot.backLeft  .setPower(0);
        robot.backRight .setPower(0);
        robot.frontLeft .setPower(0);
        robot.frontRight.setPower(0);

        robot.JewelArmServo.setPosition(.5f);

    }
}
