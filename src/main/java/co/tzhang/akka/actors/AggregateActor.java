package co.tzhang.akka.actors;

import java.util.HashMap;
import java.util.Map;

import akka.actor.UntypedActor;
import co.tzhang.akka.message.ReduceData;
import co.tzhang.akka.message.Result;

/**
 * Created by TZhang on 27/11/15.
 */
public class AggregateActor extends UntypedActor {

    private Map<String, Integer> finalReduceMap = new HashMap<String, Integer>();

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof ReduceData) {
            aggregateInMemoryReduce(((ReduceData) message).getReduceDataList());
        } else if (message instanceof Result) {
            getSender().tell(finalReduceMap.toString(), getSelf());
        } else {
            unhandled(message);
        }
    }

    private void aggregateInMemoryReduce(Map<String, Integer> reducedList) {
        reducedList.forEach((word, count) -> {
            if (finalReduceMap.containsKey(word)) {
                finalReduceMap.put(word, count + finalReduceMap.get(word));
            } else {
                finalReduceMap.put(word, count);
            }
        });
    }
}
