package message;

import java.util.List;

/**
 * Created by TZhang on 27/11/15.
 */
public final class MapData {
    private final List<WordCount> dataList;

    public MapData(List<WordCount> dataList) {
        this.dataList = dataList;
    }

    public List<WordCount> getDataList() {
        return dataList;
    }
}
