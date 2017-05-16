/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actors;

import Messages.Calculate;
import Messages.PiApproximation;
import Messages.Result;
import Messages.Work;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.FromConfig;
import akka.routing.RoundRobinRouter;


import java.util.concurrent.TimeUnit;
import scala.concurrent.duration.Duration;

/**
 *
 * @author inb
 */
public class Master extends UntypedActor {

    private final int nrOfMessages;
    private final int nrOfElements;

    private double pi;
    private int nrOfResults;
    private final long start = System.currentTimeMillis();

    private final ActorRef listener;
    private final ActorRef workerRouter;
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    
    //cluster
    Cluster cluster = Cluster.get(getContext().system());

    public Master(final int nrOfWorkers, int nrOfMessages, int nrOfElements, ActorRef listener) {
        this.nrOfMessages = nrOfMessages;
        this.nrOfElements = nrOfElements;
        this.listener = listener;

        workerRouter = this.getContext().actorOf(new Props(Worker.class).withRouter(new RoundRobinRouter(nrOfWorkers)),
                "workerRouter");
        //  workerRouter = this.getContext().actorOf(new Props(Worker.class).withRouter(new FromConfig()), "workerRouter");
    }
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

    public void onReceive(Object message) {
        if (message instanceof Calculate) {
            for (int start = 0; start < nrOfMessages; start++) {
                workerRouter.tell(new Work(start, nrOfElements), getSelf());
            }
        } else if (message instanceof Result) {
            Result result = (Result) message;
            pi += result.getValue();
            nrOfResults += 1;
            if (nrOfResults == nrOfMessages) {
                // Send the result to the listener
                Duration duration = Duration.create(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);
                listener.tell(new PiApproximation(pi, duration), getSelf());
                // Stops this actor and all its supervised children
              //  getContext().stop(getSelf());
              //  log.info("stopping system");
            }
        } else {
            unhandled(message);
        }
    }
}

