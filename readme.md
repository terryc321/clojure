# clojure

## first steps ``my-stuff`` project

following is ripped from [Deps and CLI](https://clojure.org/guides/deps_and_cli) clojure documentation

we will be using CLI command line interface of clojure , the documentation will just say CLI or CLI tools.

lets assume you have installed all the clojure tools ?

```
$ clj
Clojure 1.12.1
user=> 
```

then we can try adding 2 to 3 
```
user=> (+ 2 3)
5
```

then we can try dividing 2 by 3 and find it breaks for some reason
```
user=> (/ 2 3)
2/3
user=> (* 3 (/ 2 3))
2N
```

## first project intro-001

let us create a special file called deps.edn
```
{deps
  {clojure.java-time/clojure.java-time {:mvn/version "1.1.0"}}
}
```

we can find what versions of a specific library exist 
```
$ clj -X:deps find-versions :lib clojure.java-time/clojure.java-time
```

create more of the project infrastructure
```
$ mkdir src
$ touch src/hello.clj
```

inside hello source file we put 
```clojure
(ns hello
  (:require [java-time.api :as t]))

(defn time-str
  "Returns a string representation of a datetime in the local time zone."
  [instant]
  (t/format
    (t/with-zone (t/formatter "hh:mm a") (t/zone-id))
    instant))

(defn run [opts]
  (println "Hello world, the time is" (time-str (t/instant))))
```

then to run hello world we do 
```
clj -X hello/run
```

supposed to see ```Hello world, the time is 12:19 PM```

and find java_time class not found
```
Execution error (FileNotFoundException) at hello/eval226$loading (hello.clj:1).
Could not locate java_time/api__init.class, java_time/api.clj or java_time/api.cljc on classpath. Please check that namespaces with dashes use underscores in the Clojure file name.

```

```
# solution hack is just set CLASSPATH to maven repository where it is downloaded to
export CLASSPATH="/home/terry/.m2/repository" 
```


## lein

![](images/leiningen.jpg) [leiningen docs](https://leiningen.org/)

install Leiningen for clojure on the operating system 

start a new clojure application
```
lein new app my-stuff
```

lets see the directories laid out
```
# see how it looks like using the "tree" command
$ tree -F -a --dirsfirst my-stuff/
```

```
my-stuff/
├── doc/
│   └── intro.md
├── resources/
├── src/
│   └── my-stuff/
│       └── core.clj
├── test/
│   └── my-stuff/
│       └── core_test.clj
├── CHANGELOG.md
├── .gitignore
├── .hgignore
├── LICENSE
├── project.clj
└── README.md

7 directories, 9 files
```

change into the ```my-stuff``` directory

```
$ cd my-stuff
$ lein repl
```

alternatively load src/my-stuff/core.clj into emacs 
run M-x cider
choose root directory to be toplevel my-stuff as opposed to ones under src and test
cider-jack-in-clj



### single segment namespaces

Note that we use my-stuff.core instead of just my-stuff since single-segment namespaces are discouraged in Clojure as using those would imply classes are being assigned to the default (no-name) package.


```

```

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
$ export CLASSPATH=".:/usr/local/lib/antlr/antlr-4.13.2-complete.jar:$CLASSPATH"
```

at moment my antlr is called antlr4

brings issue of maintainability and keeping relevant libraries up to date

what if java is unavailable for whatever reason ?

## clj-antlr

get source code [clj-antlr](https://github.com/aphyr/clj-antlr) 

```
$ git clone git@github.com:aphyr/clj-antlr.git
$ unset CLASSPATH
$ cd clj-antlr
$ lein jar
```

this builds a jar file

```
$ sudo mkdir -pv /usr/local/lib/clj/antlr
$ sudo cp -v target/clj-antlr-0.2.15-SNAPSHOT.jar /usr/local/lib/clj/clj-antlr

```

place jar file somewhere safe , that cannot be changed by normal user directly

update classpath

```
$ CLASSPATH=".:/usr/local/lib/antlr" 
$ export CLASSPATH=".:/usr/local/lib/antlr:/usr/local/lib/clj/clj-antlr//usr/local/lib/clj/clj-antlr/clj-antlr-0.2.15-SNAPSHOT.jar"
```

should be able to start clojure and require clj-antlr

```
$ clj
> (require ['clj-antlr.core :as 'antlr])
```

