# Scanning Rules from xPaaS


This application goal is execute rules from a remote BRMS 6.x installation. A sample `settings.xml` can be found at this directory and here are the steps to reproduce:

* Configure `settings.xml` to refer to a remote BRMS server;
* Create a project in the remote BRMS server using the following GAV: `com.redhat.gss.brms:my-rules-xpaas:1.0`;
* In this project, create a simple rule, for example:

~~~
package com.redhat.gss.kie
rule "Hello World Rule"
    when
        eval(true)
    then
        System.out.println("Hello World! 1.0");
end
~~~
* Save, build and deploy your project; 
* Run `mvn exec:java -Dexec.mainClass="com.redhat.gss.brms.Main"` and you should see the execution of the first version of the rule;
* Go to the remote BRMS server, update the Rule, save  and build the project; 
* Wait 1 minute, press enter on the console and should see the newer version of the rule being executed.


**Note:**
There's a BRMS installation on my xpaas. Please, don't destroy it *-*

URL: http://bpms-wsiqueir.rhcloud.com/business-central
User: bpm-admin
Password: AsnN_luuDW-E


