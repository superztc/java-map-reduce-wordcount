package message;

import java.util.HashMap;

/**
 * Created by TZhang on 27/11/15.
 */
public final class ReduceData {
    private final HashMap<String, Integer> reduceDataList;

    public ReduceData(HashMap<String, Integer> reduceDataList) {
        this.reduceDataList = reduceDataList;
    }

    public HashMap<String, Integer> getReduceDataList() {
        return reduceDataList;
    }
}
