package org.firstinspires.ftc.teamcode;

/**
 * Static Math functions
 *
 * Created September 21, 2017 by Ryan Alameddine
 *
 * Last Edited
 *
 * @author Ryan Alameddine
 *
 * @version 1.0
 */

public class MathF {

    /**
     * Linear interpolation between start and end point by interpolation percentage
     *
     * @param a start value
     * @param b end value
     * @param f interpolation percentage
     */
    public static float Lerp(float a, float b, float f)
    {
        return (a * (1.0f - f)) + (b * f);
    }

}


