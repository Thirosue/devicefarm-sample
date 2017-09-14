#!/bin/bash

wget -O /etc/yum.repos.d/jenkins.repo http://pkg.jenkins-ci.org/redhat/jenkins.repo
rpm --import http://pkg.jenkins-ci.org/redhat/jenkins-ci.org.key

yum install -y git jenkins java-1.8.0-openjdk
alternatives --set java /usr/lib/jvm/jre-1.8.0-openjdk.x86_64/bin/java

chkconfig jenkins on
/etc/init.d/jenkins start

exit 0