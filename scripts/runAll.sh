cd ../prometheus-2.12.0.linux-amd64

./prometheus &

disown

sleep 5s

cd ../grafana-6.3.5/bin/

./grafana-server &

disown

sleep 5s

cd ../../kafka_2.12-2.3.0

export CLASSPATH=$CLASSPATH:/home/ubuntu/kafkaconnector/audience-annotations-0.5.0.jar:/home/ubuntu/kafkaconnector/common-utils-5.3.1.jar:/home/ubuntu/kafkaconnector/jline-0.9.94.jar:/home/ubuntu/kafkaconnector/jsr305-3.0.2.jar:/home/ubuntu/kafkaconnector/jtds-1.3.1.jar:/home/ubuntu/kafkaconnector/kafka-connect-jdbc-5.3.1.jar:/home/ubuntu/kafkaconnector/netty-3.10.6.Final.jar:/home/ubuntu/kafkaconnector/postgresql-9.4.1212.jar:/home/ubuntu/kafkaconnector/slf4j-api-1.7.26.jar:/home/ubuntu/kafkaconnector/spotbugs-annotations-3.1.8.jar:/home/ubuntu/kafkaconnector/sqlite-jdbc-3.25.2.jar:/home/ubuntu/kafkaconnector/zkclient-0.10.jar:/home/ubuntu/kafkaconnector/zkclient-0.10.jar:/home/ubuntu/kafkaconnector/mysql-connector-java-8.0.17.jar

bin/zookeeper-server-start.sh config/zookeeper.properties &

disown

sleep 10s

export KAFKA_OPTS="-javaagent:/home/ubuntu/kafka_2.12-2.3.0/kafka-prometheus/jmx_prometheus_javaagent-0.9.jar=7071:/home/ubuntu/kafka_2.12-2.3.0/kafka-prometheus/kafka-2_0_0.yml"

bin/kafka-server-start.sh config/server.properties &

disown

sleep 20s

bin/connect-standalone.sh connect.properties  connect-mysql-source.properties &