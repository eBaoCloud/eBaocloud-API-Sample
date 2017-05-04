# eBaoCloud API Sample
This project is aimed to provide the demo code to describe
how to call eBaoCloud restful API to integrate to eBaoCloud Thailand.

This project is written by Java with Spring Boot,
if you prefer other programming languages,
please refer to chapter "In Other Programming Languages".

Licensed under the Apache License 2.0.

### API covered

- Login to eBaoCloud
- Compare quotes for Travel
- Compare quotes for Motor

### Login

Before you run the sample, an authorized username & password should be assigned, if you don't right now, please contact at thsupport@ebaocloud.com

Here assuming you already got the authorized username & password, when you run the sample you should always add following 2 parameters:

```
--ebaocould.username=YOUR_USERNAME --ebaocould.password=YOUR_PWD
```

If you are writing your own code to call eBaoCloud API,
**login** API must be called, once you called successfully,
a **token** will be returned from server.
You must make sure **token** is safe and DO NOT share to anyone.
eBaoCloud will use token to do authentication.

For the details, you may refer to source code.

Alternatively, you can put your username & password
to the src/main/resources/application.properties,
if that's the case, the 2 parameters is not required in the commandline anymore.

##### Tips
**login** is not necessary to do every time, when a token
is returned, actually your system could keep it somewhere with safety,
could be memory or database, next time, you can using the token to make other API calls.
By doing this, the performance will be much better.

### Build the sample

Note: we are using **Gradle** to build this project,
if you are not familar with **Gradle**,
you may use **Maven**;
if you need to understand more about **Gradle**,
please refer to [Gradle website](https://gradle.org).

If you are using **Linux**, execute following commands:

```#Bash
PM> cd your_project_folder

PM> ./gradlew clean build

Starting a Gradle Daemon (subsequent builds will be faster)
:clean
:compileJava
Note: Some input files use unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
:processResources
:classes
:jar
:findMainClass
:startScripts
:distTar
:distZip
:bootRepackage
:assemble
:compileTestJava UP-TO-DATE
:processTestResources UP-TO-DATE
:testClasses UP-TO-DATE
:test UP-TO-DATE
:check UP-TO-DATE
:build

BUILD SUCCESSFUL

Total time: 13.246 secs


PM> cd ./build/distributions/
PM> ls -l

-rw-rw-r-- 1 ggg ggg 17029120 May  3 18:13 ebaocloud.tar
-rw-rw-r-- 1 ggg ggg 15054849 May  3 18:13 ebaocloud.zip

```

Congratulations, you built the sample code successfully, then you should get 2 compressed files,
**ebaocloud.tar** and **ebaocloud.zip**.

### Run the sample

Continue from above, execute following commands to decompress the file **ebaocloud.tar**:

```#Bash
PM> tar xvf ebaocloud.tar

ebaocloud/
ebaocloud/lib/
ebaocloud/lib/api-sample-0.1.jar
ebaocloud/lib/spring-boot-starter-1.5.1.RELEASE.jar
ebaocloud/lib/spring-web-4.3.6.RELEASE.jar
ebaocloud/lib/jackson-databind-2.8.6.jar
ebaocloud/lib/commons-lang3-3.5.jar
ebaocloud/lib/commons-io-2.5.jar
ebaocloud/lib/groovy-all-2.4.7-indy.jar
ebaocloud/lib/joda-time-2.0.jar
ebaocloud/lib/spring-boot-1.5.1.RELEASE.jar
ebaocloud/lib/spring-boot-autoconfigure-1.5.1.RELEASE.jar
ebaocloud/lib/spring-boot-starter-logging-1.5.1.RELEASE.jar
ebaocloud/lib/spring-core-4.3.6.RELEASE.jar
ebaocloud/lib/snakeyaml-1.17.jar
ebaocloud/lib/spring-aop-4.3.6.RELEASE.jar
ebaocloud/lib/spring-beans-4.3.6.RELEASE.jar
ebaocloud/lib/spring-context-4.3.6.RELEASE.jar
ebaocloud/lib/jackson-annotations-2.8.0.jar
ebaocloud/lib/jackson-core-2.8.6.jar
ebaocloud/lib/logback-classic-1.1.9.jar
ebaocloud/lib/jcl-over-slf4j-1.7.22.jar
ebaocloud/lib/jul-to-slf4j-1.7.22.jar
ebaocloud/lib/log4j-over-slf4j-1.7.22.jar
ebaocloud/lib/spring-expression-4.3.6.RELEASE.jar
ebaocloud/lib/logback-core-1.1.9.jar
ebaocloud/lib/slf4j-api-1.7.22.jar
ebaocloud/bin/
ebaocloud/bin/api-sample.bat
ebaocloud/bin/api-sample

```

As of now, you already got the executable of eBaoCloud API Sample, to get the usage of sample:


```#Bash
PM> ./ebaocloud/bin/api-sample --help
PM> ./ebaocloud/bin/api-sample --testcase=compare_travel --ebaocould.username=YOUR_USERNAME --ebaocould.password=YOUR_PWD

```

Following chapter will describe what is the test cases you can run.

### Test Cases

- Compare quotes for Travel:

```#Bash
PM> ./ebaocloud/bin/api-sample --testcase=compare_travel --ebaocould.username=YOUR_USERNAME --ebaocould.password=YOUR_PWD

""2017-05-03 19:20:52 - running test case: compare_travel
""2017-05-03 19:20:54 - logged in
""2017-05-03 19:20:54 - Compare Travel: completed
""2017-05-03 19:20:54 - {
    "eligiblePlans": [
        "insurer=DEMO_TH / plan=SAB / product=PTFA / premium=3200.0000 / uwNeeded=false",
        "insurer=DEMO_TH / plan=SABP / product=PTFA / premium=8600.0000 / uwNeeded=false",
        "insurer=DEMO_TH / plan=MAN / product=PTFA / premium=8000.0000 / uwNeeded=false",
        "insurer=DEMO_TH / plan=MANP / product=PTFA / premium=13400.0000 / uwNeeded=false"
    ],
    "success": true,
    "costInMillis": 236,
    "message": null
}

```

- Compare quotes for Motor:

```#Bash
PM> ./ebaocloud/bin/api-sample --testcase=compare_motor --ebaocould.username=YOUR_USERNAME --ebaocould.password=YOUR_PWD

""2017-05-03 19:21:47 - running test case: compare_motor
""2017-05-03 19:21:48 - logged in
""2017-05-03 19:21:48 - Compare Travel: completed
""2017-05-03 19:21:48 - {
    "eligiblePlans": [
        "insurer=DEMO_TH / plan=CLASS3 / product=VMI / premium=4032.72 / uwNeeded=false",
        "insurer=DEMO_TH / plan=CLASS2+ / product=VMI / premium=8000.39 / uwNeeded=false",
        "insurer=DEMO_TH / plan=CLASS2+NODD / product=VMI / premium=8399.50 / uwNeeded=false",
        "insurer=DEMO_TH / plan=CLASS3+ / product=VMI / premium=7300.61 / uwNeeded=false",
        "insurer=DEMO_TH / plan=CLASS3+NODD / product=VMI / premium=7499.63 / uwNeeded=false",
        "insurer=DEMO2_TH / plan=CLASS3 / product=VMI / premium=4032.83 / uwNeeded=false",
        "insurer=DEMO2_TH / plan=CLASS2+ / product=VMI / premium=2400.12 / uwNeeded=false",
        "insurer=DEMO2_TH / plan=CLASS2+NODD / product=VMI / premium=5459.89 / uwNeeded=false",
        "insurer=DEMO2_TH / plan=CLASS3+ / product=VMI / premium=2190.83 / uwNeeded=false",
        "insurer=DEMO2_TH / plan=CLASS3+NODD / product=VMI / premium=5624.72 / uwNeeded=false"
    ],
    "success": true,
    "costInMillis": 350,
    "message": null
}
```

The result of compare quotes printed on screen is not the real format of API response, it's just formatted for you to read easier.
For the real format of response, please refer to eBaoCloud Developer Guide(under construction).

The stored token should be updated with new one regularly, the recommended frequency is one day.

Looking for more samples with details? Please refer to the source code.

### In Other Programming Languages

- PHP: in progress
- Ruby: in progress
- C#: in progress

### Recent Releases

3 May 2017, 0.1 released

Feature - add sample for APIs: Login, Compare Quotes for Travel, Compare Quotes for Motor




