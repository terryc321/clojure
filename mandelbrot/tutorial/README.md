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
clj -X:deps find-versions :lib clojure.java-time/clojure.java-time
```

