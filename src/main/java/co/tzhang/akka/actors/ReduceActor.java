package co.tzhang.akka.actors;

import java.util.HashMap;
import java.util.List;

import akka.actor.UntypedActor;
import co.tzhang.akka.message.MapData;
import co.tzhang.akka.message.ReduceData;
import co.tzhang.akka.message.WordCount;

/**
 * Created by TZhang on 27/11/15.
 */
public class ReduceActor extends UntypedActor{
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof MapData) {
            MapData mapData = (MapData) message;
            getSender().tell(reduce(mapData.getDataList()), getSelf());
        } else {
            unhandled(message);
        }
    }


    private ReduceData reduce(List<WordCount> dataList) {
        HashMap<String, Integer> reducedMap = new HashMap<String, Integer>();
        dataList.forEach(wc-> {
            if (reducedMap.containsKey(wc.getWord())) {
                int count = reducedMap.get(wc.getWord());
                reducedMap.put(wc.getWord(), ++count);
            } else {
                reducedMap.put(wc.getWord(), 1);
            }
        });
        return new ReduceData(reducedMap);
    }
}
