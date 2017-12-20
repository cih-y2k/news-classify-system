package com.classify.dictionary;

public interface IConfig {
    int LABEL_COUNT = 6; // Number of labels
    // TODO: Long - reduce MAX_NUMBER_OF_NEW if OutOfMemory
    int MAX_NUMBER_OF_NEW = 500; // all label must have number of news equal or greater than MAX_NUMBER_OF_NEW
    // Sum of all element in NUMBER_NEWS_EACH_LABEL array
    int TOTAL_NEWS = MAX_NUMBER_OF_NEW*LABEL_COUNT;
    // TODO: Config NUMBER_NEWS_EACH_LABEL
    int[] NUMBER_NEWS_EACH_LABEL = {500, 500, 500, 500, 500, 500};
    // P(evidence) array
    double[] PRIOR_EACH_LABEL = {1/6, 1/6, 1/6, 1/6, 1/6, 1/6};
    // Labels
    String LABELS[] = {"Education", "Science", "Business", "Law", "Health", "Sport"};
    // Train data path
    String TRAIN_PARENT_PATH = "data/train";
    // test
    String[] TEST_PATH = { "data/test/giaoduc.txt", "data/test/khoahoc.txt", "data/test/kinhdoanh.txt",
    "data/test/phapluat.txt", "data/test/suckhoe.txt", "data/test/thethao.txt"};
    // parent directory to save dic
    String DICS_PARENT_PATH = "data/dictionary";
    // Dictionary path
    String DICTIONARY_URL[] = {"data/dictionary/giaoduc.dic", "data/dictionary/khoahoc.dic", "data/dictionary/kinhdoanh.dic",
            "data/dictionary/phapluat.dic", "data/dictionary/suckhoe.dic", "data/dictionary/thethao.dic"};
    String FREQUENCY_PATH = "data/frequency.fre";
}
