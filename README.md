# k8s_jmeter_demo

## use step 
clone this demo project
code(https://github.com/bruce770405/k8s_jmeter_demo.git)

switch to this project root

run command
`gradlew build jibDockerBuild`

check tw.com.bruce/jmeter_demo image in local docker images
`docker images`

run docker image
`docker run --publish=8080:8080 tw.com.bruce/jmeter_demo:0.0.1-SNAPSHOT`
