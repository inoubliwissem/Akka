package com.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Actorsys{

public static void main(String args[] )
{
System.out.println("hello system");
ActorSystem system = ActorSystem.create("CalcSystem");
//ActorRef worker = system.actorOf(Props.create(Worker.class), "master");
//ActorRef worker = system.actorOf(new Props(Worker.class), "master");
//final ActorRef worker = system.actorOf(Props.create(Worker.class), "worker");
}

}