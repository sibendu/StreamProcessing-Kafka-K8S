# Build jars for stream processors 

cd streams

mvn clean install -Dmain.Class=com.sd.examples.FrequentTxnProcessor

cp target/streams.examples-0.1.jar  ../frequenttxn.jar

mvn clean install -Dmain.Class=com.sd.examples.SimultaneousTxnProcessor

cp target/streams.examples-0.1.jar  ../simultaneoustxn.jar

mvn clean install -Dmain.Class=com.sd.examples.AlertHandler

cp target/streams.examples-0.1.jar  ../alerthandler.jar


# All 3 jars for stream processors ready, copy them back to target directory to run

cd ..
cp frequenttxn.jar streams/target/
cp simultaneoustxn.jar streams/target/
cp alerthandler.jar streams/target/


# Build simulator web app 

cd txn-simulator
gradle  clean build  
