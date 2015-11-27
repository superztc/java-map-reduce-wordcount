package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinPool;
import message.MapData;
import message.ReduceData;
import message.Result;

/**
 * Created by TZhang on 27/11/15.
 */
public class MasterActor extends UntypedActor {
    ActorRef mapActor = getContext().actorOf(Props.create(MapActor.class)
            .withRouter(new RoundRobinPool(5)), "map");
    ActorRef reduceActor = getContext().actorOf(Props.create(ReduceActor.class)
            .withRouter(new RoundRobinPool(5)), "reduce");
    ActorRef aggregateActor = getContext().actorOf(Props.create(AggregateActor.class),
            "aggregate");


    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            mapActor.tell(message, getSelf());
        } else if (message instanceof MapData) {
            reduceActor.tell(message, getSelf());
        } else if (message instanceof ReduceData) {
            aggregateActor.tell(message, getSelf());
        } else if (message instanceof Result) {
            aggregateActor.forward(message, getContext());
        } else {
            unhandled(message);
        }
    }
}
