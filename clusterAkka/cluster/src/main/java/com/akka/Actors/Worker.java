/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actors;

import Messages.Result;
import Messages.Work;
import akka.actor.UntypedActor;
import akka.cluster.Cluster;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.cluster.ClusterEvent;

/**
 *
 * @author inb
 */
public class Worker extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    //cluster
    Cluster cluster = Cluster.get(getContext().system());
    //subscribe to cluster changes

    @Override
    public void preStart() {
       //#subscribe
        cluster.subscribe(getSelf(), ClusterEvent.MemberEvent.class);
        log.info("Initializing  (Step1)..."+getSelf().path());

    }

//re-subscribe when restart
    @Override
    public void postStop() {
        cluster.unsubscribe(getSelf());
    }

    // calculatePiFor ...
    private double calculatePiFor(int start, int nrOfElements) {
        double acc = 0.0;
        for (int i = start * nrOfElements; i <= ((start + 1) * nrOfElements - 1); i++) {
            acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1);
        }
        return acc;
    }

    public void onReceive(Object message) {
        if (message instanceof Work) {
         //   System.out.println("akktor num " + getSelf().toString());
          //  log.info(getSelf().path().name()+"======>"+getSender().toString());
            Work work = (Work) message;
            double result = calculatePiFor(work.getStart(), work.getNrOfElements());
            getSender().tell(new Result(result), getSelf());
        } else {
            unhandled(message);
        }
    }
}

