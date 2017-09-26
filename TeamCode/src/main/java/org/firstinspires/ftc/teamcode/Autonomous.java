package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Simple Mecanum controller
 *
 * Created September 26, 2017 by Ryan Alameddine
 *
 * Last Edited
 *
 * @author Ryan Alameddine
 *
 * @version 1.0
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Autonomous", group="Test")
public class Autonomous extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ProjectMecanum robot = new ProjectMecanum();
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        params.vuforiaLicenseKey = "AdLSetb/////AAAAGbm2rHS0ck7Fgxwjtp3Cbclm98DyEfx+PLZ+VgF6AjcoFsOoMwgjWair2KgZLmc9MwR74NxG2WqBPqWs4ocmgQ0DyEnDW0tSzgUhH/UgBobUpmHrqSY5htttuRw6OKo9/A+3t39YCQj0+qxjsIj6cg/bStC8lI11ZMYukRnCSKLQQOVGxAbe0CuL7cBQ34gc8hqxOzk1gVXyj+U9XxxjKnJ18qiCcisprtAaRuRB6xzP8MzUQoql0Ajn8ldXW3mZSKjc3tq0LPYDwYmAaKkAxNz/jabhUk3m4Gyti5ApeYtw8yWA0AkKum8Fb8W/VTnc6FckH4BXgXOcDng++FTw9vihPiSJ7a36I2hU1Q8+NnuC";
        params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;
        VuforiaLocalizer localizer = ClassFactory.createVuforiaLocalizer(params);
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 1);
        VuforiaTrackables beacons = localizer.loadTrackablesFromAsset("FTC_2016-17");

        beacons.get(0).setName("Left");
        beacons.get(1).setName("Middle");
        beacons.get(2).setName("Right");
        beacons.activate();
        robot.init(hardwareMap);
        waitForStart();

        while(opModeIsActive()){
            for(VuforiaTrackable beac: beacons) {
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) beac.getListener()).getPose();
                if(pose != null){
                    VectorF translation = pose.getTranslation();

                    telemetry.addData(beac.getName() + "-Translation", translation);

                }
            }
            telemetry.update();
        }
    }
}
