package com.akka;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
 
public class Worker extends UntypedActor {
  LoggingAdapter log = Logging.getLogger(getContext().system(), this);
 
  public void onReceive(Object message) throws Exception {
    if (message instanceof String) {
     // log.info("Received String message: {}", message);
     // getSender().tell(message, getSelf());
      System.out.println(message);
    } else
      unhandled(message);
  }
}
