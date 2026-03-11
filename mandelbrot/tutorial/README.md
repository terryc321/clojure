# Tutorial

clojure 101

clojure java time library on github if we wish to use it we can include it
in the dependencies file deps.edn 

```
https://github.com/dm3/clojure.java-time

deps.edn
{:deps
 {clojure.java-time/clojure.java-time {:mvn/version "1.1.0"}}}
```

Lets see what versions are available using this 

```
> clj -X:deps find-versions :lib clojure.java-time/clojure.java-time

Downloading: clojure/java-time/clojure.java-time/maven-metadata.xml from clojars
{:mvn/version "1.0.0"}
{:mvn/version "1.1.0"}
{:mvn/version "1.2.0"}
{:mvn/version "1.3.0"}
{:mvn/version "1.4.0"}
{:mvn/version "1.4.1"}
{:mvn/version "1.4.2"}
{:mvn/version "1.4.3"}
```

Lets now make a source directory for the actual project code 

```
> mkdir src
> touch src/hello.clj
```

Here is what hello.clj source file can look like.

Demonstrates a run procedure that will display the local time.

```
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

We can run this program from the command line if we wish 

```
> clj -X hello/run
```
Alternatively using emacs 

```
M-x cider-jack-in-clj

open src/hello.clj file in emacs C-x C-f 

user> (in-ns 'hello)
hello> (run 1)
Hello world, the time is 12:17 pm
```

Alternatively we can run the hello.clj run procedure from another namespace using slash 

```
user>(hello/run 1)
Hello world, the time is 12:20 pm
```







