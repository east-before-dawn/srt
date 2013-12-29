命令行运行，指定待预测的用户数据文件夹，如"./data_json/220956324"

java -Djava.ext.dirs=./lib -jar predictor.jar <user data path>

生成结果再predict_result目录下，如"./predict_result/220956324"
格式为json，如：
{"E":0.49983392753391265,"A":0.7853490758130586,"C":0.72321155879556,"N":0.2577357965357036,"O":0.6402500002662502}

