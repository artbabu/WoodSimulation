    /* 
 * Copyright (c) 2011/13 Martin Prout
 * 
 * This demo & library is free software; you can redistribute it and/or
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
package lsystem.turtle;

/**
 *
 * @author Martin Prout <martin_p@lineone.net>
 */
public interface Turtle3D {

    /**
     * 
     */
    public final String VERSION = "1.0.0";

    /**
     * Any implementing classes should move forward by distance without drawing
     * @param distance 
     */
    void forward(float distance);
    
    
    /**
     *
     * @param distance
     * @param r1
     * @param r2
     * @param level
     */
    void drawRod(float distance, float r1, float r2, int level);
    /**
     * Any implementing classes should draw something eg. line rod box etc
     * @param distance 
     * @param level int level of detail for cone/cylinder sphere
     */
    void draw(float distance, int level);

    /**
     * Any implementing classes should draw something eg. line rod box etc
     * @param distance 
     */
    void draw(float distance);

    /**
     * Up and Down to non aeronautical
     * @param angle degrees/radians int/float
     */
    void pitch(int angle);

    /**
     * 
     * @param angle
     */
    void pitch(float angle);

    /**
     * Rotate about the axis (of the direction we are moving in)
     * @param angle degrees/radians int/float
     */
    void roll(int angle);

    /**
     * 
     * @param angle
     */
    void roll(float angle);

    /**
     * Left and Right turn to non aeronautical
     * @param angle degrees/radians int/float
     */
    void yaw(int angle);

    /**
     * 
     * @param angle
     */
    void yaw(float angle);
}
