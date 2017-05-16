/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akka;

import Actors.Listener;
import Actors.Master;
import Messages.Calculate;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;
import akka.event.LoggingAdapter;
import java.util.logging.Logger;

/**
 *
 * @author inb
 */

public  class Main {
   
    public void calculate(final int nrOfWorkers, final int nrOfElements, final int nrOfMessages) {
    // Create an Akka system
    ActorSystem system = ActorSystem.create("PiSystem");
 
    // create the result listener, which will print the result and shutdown the system
    final ActorRef listener = system.actorOf(new Props(Listener.class), "listener");
 
    // create the master
    ActorRef master = system.actorOf(new Props(new UntypedActorFactory() {
      public UntypedActor create() {
        return new Master(nrOfWorkers, nrOfMessages, nrOfElements, listener);
      }
    }), "master");
 
    // start the calculation
    master.tell(new Calculate());
 
  }
    public static void main(String[] args) {
//    Main pi = new Main();
//    pi.calculate(4, 10000, 10000);
  // Create an Akka system
    ActorSystem system = ActorSystem.create("PiSystem");
 
    // create the result listener, which will print the result and shutdown the system
    final ActorRef listener = system.actorOf(new Props(Listener.class), "listener");
        // create the master
    ActorRef master = system.actorOf(new Props(new UntypedActorFactory() {
      public UntypedActor create() {
        return new Master(4, 10, 10, listener);
      }
    }), "master");
    
        // start the calculation
    master.tell(new Calculate());
 
  }
}

