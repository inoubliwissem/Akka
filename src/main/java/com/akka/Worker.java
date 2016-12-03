package com.akka;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Worker extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public void onReceive(Object message) throws Exception {

        if (message instanceof String) {

            System.out.println(message + "from" + getSender().toString());

        } else {
            unhandled(message);
        }
    }
}

