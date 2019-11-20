# sudo yum install java-1.8.0-openjdk

wget https://github.com/prometheus/prometheus/releases/download/v2.12.0/prometheus-2.12.0.linux-amd64.tar.gz

tar -zxvf prometheus-2.12.0.linux-amd64.tar.gz
#cd prometheus-2.12.0.linux-amd64

wget https://dl.grafana.com/oss/release/grafana-6.3.5.linux-amd64.tar.gz

tar -zxvf grafana-6.3.5.linux-amd64.tar.gz
#cd grafana-6.3.5

wget http://apachemirror.wuchna.com/kafka/2.3.0/kafka_2.12-2.3.0.tgz 

tar -xzf kafka_2.12-2.3.0.tgz

cd kafka_2.12-2.3.0

mkdir kafka-prometheus && cd kafka-prometheus
wget https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.9/jmx_prometheus_javaagent-0.9.jar
wget https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.6/jmx_prometheus_javaagent-0.6.jar
wget https://github.com/prometheus/jmx_exporter/blob/master/example_configs/kafka-2_0_0.yml
wget https://github.com/prometheus/jmx_exporter/blob/master/example_configs/kafka-0-8-2.yml

cd ..

wget https://github.com/confluentinc/ksql/archive/master.zip
unzip master.zip && rm master.zip
cd  ksql-master