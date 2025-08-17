# clojure

## antlr4

see [building antlr](https://github.com/antlr/antlr4/blob/master/doc/building-antlr.md#compile) using maven tool 

```
$ export MAVEN_OPTS="-Xmx1G"   # don't forget this on linux
cd /tmp/antlr4 # or wherever you have the software
rm -rf ~/.m2/repository/org/antlr*
mvn clean
mvn -DskipTests install
```

make antlr system library directory and copy in complete jar file
```
$ sudo mkdir -pv /usr/local/lib/antlr
$ sudo cp -v ./tool/target/antlr4-4.13.3-SNAPSHOT-complete.jar /usr/local/lib/antlr
```

see this link [to install antlr4](https://www.cs.upc.edu/~cl/practica/install.html) so it is a command line tool 

here is the shell script for antlr tool , place it in ```/usr/local/bin/antlr```

```
#! /bin/bash
export CLASSPATH=".:/usr/local/lib/antlr-4.13.2-complete.jar:$CLASSPATH"
java -jar /usr/local/lib/antlr/antlr4-4.13.3-SNAPSHOT-complete.jar "$@"
```

give it permissions to execute 
```
sudo chmod +x /usr/local/bin/antlr
```

anything dependent on jar file will require correct classpath 

any editor setup slightly misconfigured classpath will mean antlr facilities likely fail

setup environment correctly

```
$ export CLASSPATH=".:/usr/local/lib/antlr-4.13.2-complete.jar:$CLASSPATH"
```

at moment my antlr is called antlr4

brings issue of maintainability and keeping relevant libraries up to date

what if java is unavailable for whatever reason ?

