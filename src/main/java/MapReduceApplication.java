import java.time.Duration;
import java.util.concurrent.TimeUnit;

import actors.MasterActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import message.Result;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

/**
 * Created by TZhang on 27/11/15.
 */
public class MapReduceApplication {
    public static void main(String[] args) throws Exception {
        Timeout timeout = new Timeout(FiniteDuration.create(5, TimeUnit.SECONDS));
        ActorSystem system = ActorSystem.create("MapReduceWordCount");
        ActorRef master = system.actorOf(Props.create(MasterActor.class), "master");
        master.tell("The quick brown fox tried to jump over the lazy dog and fell on the dog", master);
        master.tell("Dog is man's best friend", master);
        master.tell("Dog and Fox belong to the same family", master);

        Thread.sleep(1000);

        Future<Object> future = Patterns.ask(master, new Result(), timeout);
        String result = (String) Await.result(future, timeout.duration());
        System.out.println(result);
        system.shutdown();

    }
}
