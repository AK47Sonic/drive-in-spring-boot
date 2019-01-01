### Command
mvn -Dmaven.test.skip -U clean package
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005  -jar target\springwebmvc-0.0.1-SNAPSHOT-war-exec.jar


