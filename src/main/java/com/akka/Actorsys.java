package com.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.akka.Worker;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValueFactory;
import java.io.FileReader;
import java.util.Scanner;

public class Actorsys {

    public static void main(String args[]) {
        System.out.println("hello system");

        ActorSystem system = ActorSystem.create("CalcSystem");
        ActorRef worker = system.actorOf(Props.create(Worker.class), "agent1");
        ActorRef worker2 = system.actorOf(Props.create(Worker2.class), "agent2");

        worker.tell(new String("salut x"), worker);

    }
}
