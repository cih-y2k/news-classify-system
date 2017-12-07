package com.classify.dictionary;

public interface IConfig {
    int LABEL_COUNT = 6; // Number of labels
    // TODO: Long - reduce MAX_NUMBER_OF_NEW if OutOfMemory
    int MAX_NUMBER_OF_NEW = 1000; // all label must have number of news equal or greater than MAX_NUMBER_OF_NEW
    // Sum of all element in NUMBER_NEWS_EACH_LABEL array
    int TOTAL_NEWS = MAX_NUMBER_OF_NEW*LABEL_COUNT;
    // TODO: Config NUMBER_NEWS_EACH_LABEL
    int[] NUMBER_NEWS_EACH_LABEL = {1000, 1000, 1000, 1000, 1000, 1000};
    // P(evidence) array
    double[] PRIOR_EACH_LABEL = {1/6, 1/6, 1/6, 1/6, 1/6, 1/6};
    // Labels
    String LABELS[] = {"Giáo Dục", "Khoa Học", "Kinh Doanh", "Pháp Luật", "Sức Khỏe", "Thể Thao"};
    // Train data path
    String TRAIN_PARENT_PATH = "data/train_v2";
    // test
    String[] TEST_PARENT_PATH = { "data/train_v3/giaoduc_v3.dat", "data/train_v3/khoahoc_v3.dat", "data/train_v3/kinhdoanh_v3.dat",
    "data/train_v3/phapluat_v3.dat", "data/train_v3/suckhoe_v3.dat", "data/train_v3/thethao_v3.dat"};
    // parent directory to save dic
    String DICS_PARENT_PATH = "data/dictionary";
    // Dictionary path
    String DICTIONARY_URL[] = {"data/dictionary/giaoduc_v2.dic", "data/dictionary/khoahoc_v2.dic", "data/dictionary/kinhdoanh_v2.dic",
            "data/dictionary/phapluat_v2.dic", "data/dictionary/suckhoe_v2.dic", "data/dictionary/thethao_v2.dic"};
    String FREQUENCY_PATH = "data/frequency.fre";
}
