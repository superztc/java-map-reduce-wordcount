package message;

/**
 * Created by TZhang on 27/11/15.
 */
public final class WordCount {
    private final String word;
    private final Integer count;

    public WordCount(String word, Integer count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public Integer getCount() {
        return count;
    }
}
