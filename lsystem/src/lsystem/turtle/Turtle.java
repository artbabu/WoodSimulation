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

package lsystem.turtle;



/**
 * A turtle to store position and direction
 * @author Martin Prout
 */
public class Turtle implements Cloneable, TurtleInterface {

    private float x, y, angle;

    /**
     * Copy Constructor
     * @param turtle
     */
    public Turtle(Turtle turtle) {
        this.x = turtle.getX();
        this.y = turtle.getY();
        this.angle = turtle.getTheta();
    }


    /**
     * Constructor
     * @param x
     * @param y
     * @param angle
     */
    public Turtle(float x, float y, float angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
        }
        return null;
    }

    /**
     * X position getter
     * @return x position float
     */
    @Override
    public float getX() {
        return x;
    }

    /**
     * Y position getter
     * @return y position float
     */
    @Override
    public float getY() {
        return y;
    }

    /**
     * Angle getter
     * @return angle float
     */
    @Override
    public float getTheta() {
        return angle;
    }

    /**
     * X position setter
     * @param x position float
     */
    @Override
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Y position setter
     * @param y position float
     */
    @Override
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Angle setter
     * @param theta
     */
    @Override
    public void setTheta(float theta) {
        this.angle = theta;
    }
}
