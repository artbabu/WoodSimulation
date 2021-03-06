/* 
 * Copyright (c) 2011/13 Martin Prout
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * http://creativecommons.org/licenses/LGPL/2.1/
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package lsystem.util;

/**
 * A very restricted lookup table for fast sine & cosine computations. The table 
 * currently has a fixed precision of 1.0 degrees. Thus should be as accurate as 
 * Math.sin when using integer input. However with a float input, values are 
 * cast to integer, and there will be errors. Note the reduced lookup up table, 
 * is restricted to the first quadrant of sine. Conditional rules are used to 
 * map to other quadrants and cos. Based on ideas from:-
 * http://en.wikipedia.org/wiki/Lookup_table
 * One annoyance of java is the behaviour of % wrt negative values cf python for 
 * example. A kludge is required to return the complement of 360, which would 
 * not otherwise be required.
 */
public final class LUT {

    /**
     * Lookup table for degree cosine/sine, has a fixed precision 1.0 degrees
     * @author Martin Prout <martin_p@lineone.net>
     */
    static final float[] sinLUT = new float[91];
    /**
     * 
     */
    public static final float TO_RADIANS = (float) Math.PI / 180;
    /**
     * 
     */
    private static boolean initialized = false;
    /**
     * Message to display on console processing ide
     */
    public static final String message = "Sine/Cosine lookup tables initialized"
         + " with a fixed\nprecision of 1.0 degrees. NB: degree input. Use\n"
            + "LUT2 for greater precision (of ca. 0.25 degrees)\n";

    /**
     * Initialise sin table with values (first quadrant only)
     */
    public static void initialize() {
        if (initialized == false){
        for (int i = 0; i <= 90; i++) {
            sinLUT[i] = (float) Math.sin(TO_RADIANS * i);
        }
        initialized = true;
        }
    }

    /**
     * Look up sine for the passed angle in degrees.
     * 
     * @param thet degree int
     * @return sine value for theta
     */
    public static float sin(int thet) {
        while (thet < 0) {
            thet += 360; // Needed because negative modulus plays badly in java
        }
        int theta = thet % 360;
        int y = theta % 90;
        float result = (theta < 90) ? sinLUT[y] : (theta < 180)
                ? sinLUT[90 - y] : (theta < 270)
                ? -sinLUT[y] : -sinLUT[90 - y];
        return result;
    }

    /**
     * Look up sin for the passed angle in degrees. NB lacks precision unless
     * float is round number (needed to work with pen and turtle interface)
     * Casting to int rather than rounding is deliberate, use LUT2 instead for
     * greater precision with a decimal float input
     * @param thet degree float
     * @return sin value for theta
     */
    public static float sin(float thet) {
        return LUT.sin((int) thet);
    }

    /**
     * Look up cos for the passed angle in degrees.
     * 
     * @param thet degree int
     * @return sine value for theta
     */
    public static float cos(int thet) {
        while (thet < 0) {
            thet += 360; // Needed because negative modulus plays badly in java
        }
        int theta = thet % 360;
        int y = theta % 90;
        float result = (theta < 90) ? sinLUT[90 - y] : (theta < 180)
                ? -sinLUT[y] : (theta < 270)
                ? -sinLUT[90 - y] : sinLUT[y];
        return result;
    }

    /**
     * Look up cos for the passed angle in degrees. NB lacks precision unless
     * float is round number (needed to work with pen and turtle interface)
     * Casting to int rather than rounding is deliberate, use LUT2 instead for
     * greater precision with a decimal float input
     * @param thet degree float
     * @return sine value for theta
     */
    public static float cos(float thet) {
        return LUT.cos((int) thet);
    }
}
