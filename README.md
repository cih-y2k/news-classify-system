# Preprocessing text
Using regular expression to replace ignore character
```
\\"|\\r|\\t|\\n -> null
{"type":".+","title":"(.+)","content":" -> $1 + 1 space('$1 ')
","url":".+ -> null
```
Using (UETSegmenter)[https://github.com/phongnt570/UETsegmenter] for word segmentation
```
- Using command line
java -jar uetsegmenter.jar -r seg -m <models_path> -i <input_path> [-ie <input_extension>] -o <output_path> [-oe <output_extension>]

	-m	:	path to the folder of segmenter model (required)
	-i	:	path to the input text (file/folder) (required)
	-ie	:	input extension, only use when input_path is a folder (default: *)
	-o	:	path to the output text (file/folder) (required)
	-oe	:	output extension, only use when output_path is a folder (default: seg)
- Using java code
1. Copy .jar file, dictionary directory, models directory to root of project
2. Add jar file to project library
3. Code
    String modelsPath = "models"; // path to the model folder. This folder must contain two files: model, features
	UETSegmenter segmenter = new UETSegmenter(modelsPath); // construct the segmenter
	String raw_text_1 = "Tốc độ truyền thông tin ngày càng cao.";
	String raw_text_2 = "Tôi yêu Việt Nam!";

	String seg_text_1 = segmenter.segment(raw_text_1); // Tốc_độ truyền thông_tin ngày_càng cao .
	String seg_text_2 = segmenter.segment(raw_text_2); // Tôi yêu Việt_Nam !

	// ... You only need to construct the segmenter one time, then you can segment any number of texts.
```