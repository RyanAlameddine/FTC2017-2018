package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
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
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Autonomous", group="Test")
public class Autonomous extends LinearOpMode {
    VuforiaLocalizer localizer;

    @Override
    public void runOpMode() throws InterruptedException {
        ProjectMecanum robot = new ProjectMecanum();

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

        while(opModeIsActive()){

        }
    }
}
