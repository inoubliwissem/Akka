/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Messages;

import scala.concurrent.duration.Duration;

/**
 *
 * @author inb
 */
public class PiApproximation {

    private final double pi;
    private final Duration duration;

    public PiApproximation(double pi, Duration duration) {
        this.pi = pi;
        this.duration = duration;
    }

    public double getPi() {
        return pi;
    }

    public Duration getDuration() {
        return duration;
    }
}
