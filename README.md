# VietNam Text Classify Using TF-IDF and Naive Bayes
### Build with
- Java
- IntelIJ IDEA 2017
### Preprocessing
- Using regular expression to replace ignore character
```
\\"|\\r|\\t|\\n -> null
{"type":".+","title":"(.+)","content":" -> $1 + 1 space('$1 ')
","url":".+ -> null
```
- Using [UETSegmenter](https://github.com/phongnt570/UETsegmenter) for word segmentation

### Directory structure
```
NewsClassifySystem(root)
--- data
    --- data/dictionary
    --- data/pre1
    --- data/pre2
    --- data/test
--- dictionary
--- models
--- src
    --- src/main/java/com/classify/crawler
    --- src/main/java/com/classify/dictionary
    --- src/main/java/com/classify/preprocess
--- uetsegmenter.jar
--- vnstopword.txt
```
### How to run test
- Setup java environment
- Clone or download source code
- Edit config in IConfig.java (com.classify.dictionary) - MAX_NUMBER_OF_NEW
- Run com.classify.dictionary.Runner to train the model
- Run com.classify.test.Checker to test the model
### Prepare data

### Bugs

### Features
 - all_news list have size < TOTAL_NEWS : some news when split have no useful words

