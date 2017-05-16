/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actors;

import Messages.PiApproximation;
import akka.actor.UntypedActor;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent.MemberEvent;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 *
 * @author inb
 */
public class Listener extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    Cluster cluster = Cluster.get(getContext().system());

    //subscribe to cluster changes
    @Override
    public void preStart() {
        //#subscribe
        cluster.subscribe(getSelf(), MemberEvent.class);
        // Address clusterAddress = Cluster.get(getContext().system()).selfAddress();
        // Cluster.get(getContext().system()).join(clusterAddress);
        //#subscribe
    }

    //re-subscribe when restart
    @Override
    public void postStop() {
        cluster.unsubscribe(getSelf());
    }

    public void onReceive(Object message) {
        if (message instanceof PiApproximation) {
            PiApproximation approximation = (PiApproximation) message;
          //  System.out.println(String.format("\n\tPi approximation: \t\t%s\n\tCalculation time: \t%s",
                   // approximation.getPi(), approximation.getDuration()));
          //  getContext().system().shutdown();
        } else {
            unhandled(message);
        }
    }
}

