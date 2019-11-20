# Run the stream processors

java -jar streams/target/frequenttxn.jar  &
java -jar streams/target/simultaneoustxn.jar  &
java -jar streams/target/alerthandler.jar  &
sleep 10s
disown


# Run simulator 

cd txn-simulator
gradle  bootRun &
sleep 5s
disown