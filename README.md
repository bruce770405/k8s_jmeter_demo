# k8s_jmeter_demo

## use step 
1. clone this demo project
    https://github.com/bruce770405/k8s_jmeter_demo.git

2. switch to this project root

3. run command
    gradlew build jibDockerBuild

4. check tw.com.bruce/jmeter_demo image in local docker images
    docker images

5. run docker image
    docker run --publish=8080:8080 tw.com.bruce/jmeter_demo:0.0.1-SNAPSHOT
