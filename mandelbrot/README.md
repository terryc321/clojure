# playground



# deps.new

```
clojure -Tnew aclojure -Ttools install-latest :lib io.github.seancorfield/deps-new :as new
```

now create new playground directory

```
clojure -Tnew app :name playground
```

```
Create a new project using one of the built-in templates:

For an application (with an entry point and support for building an uberjar):
clojure -Tnew app :name yourname/yourproject

For a library (with support for building and deploying a JAR):
clojure -Tnew lib :name yourname/yourlibrary

For a minimal scratch project (a minimal setup for experimentation):
clojure -Tnew scratch :name playground

After creating the project, you can run common tasks like tests and builds using:

clojure -T:build test — run tests
clojure -T:build ci — run tests and build the uberjar (for apps) or JAR (for libraries) 
The generated project includes deps.edn, build.clj, a test directory, .gitignore, and a README.md — all set up for immediate use. 
```

# Day One 

# Cljfx

```
https://github.com/cljfx/cljfx
```


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

# Cljfx java fx

Lets try see if cljfx is known , what version do we need then 

```
> clj -X:deps find-versions :lib cljfx/cljfx

clj -X:deps find-versions :lib cljfx/cljfx
Downloading: cljfx/cljfx/maven-metadata.xml from clojars
{:mvn/version "1.9.6"}
{:mvn/version "1.10.0"}
{:mvn/version "1.10.1"}
{:mvn/version "1.10.2"}
{:mvn/version "1.10.3"}
{:mvn/version "1.10.4"}
{:mvn/version "1.10.5"}
{:mvn/version "1.10.6"}
```

So deps.edn needs to be structured in a certain way, need a clojure hash table.

```
{ }

```

core.clj

```
(ns cljfx-tutorial.core
  (:require [cljfx.api :as fx]))

(defn hello-world []
  {:fx/type :label :text "Hello, Cljfx!"})

(defn root []
  {:fx/type :stage
   :showing true
   :title "Cljfx Tutorial"
   :scene {:fx/type :scene
           :root {:fx/type :v-box
                  :padding 25
                  :spacing 40
                  :children [{:fx/type hello-world}]}}})

(defn -main []
  (fx/create-renderer)
  (fx/on-fx-thread (root)))
  
  
```

we can update our deps.edn so we can more easily run at command line

```
{
 :paths ["src"]
 :deps
 {
  clojure.java-time/clojure.java-time {:mvn/version "1.1.0"}
  cljfx/cljfx {:mvn/version "1.10.6"}
  }
 :aliases {:run {:main-opts ["-m" "core"]}} 
 }
```

```
> clj -A:run

WARNING: Use of :main-opts with -A is deprecated. Use -M instead.
WARNING: A restricted method in java.lang.System has been called
WARNING: java.lang.System::load has been called by com.sun.glass.utils.NativeLibLoader in an unnamed module (file:/home/terry/.m2/repository/org/openjfx/javafx-graphics/22-ea+16/javafx-graphics-22-ea+16-linux.jar)
WARNING: Use --enable-native-access=ALL-UNNAMED to avoid a warning for callers in this module
WARNING: Restricted methods will be blocked in a future release unless native access is enabled
... nothing on screen ??
```

Lets see version of java we are running
```
> clj 
(System/getProperty "java.version")
"25.0.2"
```

We can also see the class path

```
(System/getProperty "java.class.path")
```


# Aside 

Nothing on the screen , huh. Try deps.edn with cljfx version 1.7.12 . Still nothing.

```
{
 :paths ["src"]
 :deps
 {
  clojure.java-time/clojure.java-time {:mvn/version "1.1.0"}
  cljfx/cljfx {:mvn/version "1.7.12"}
  }
 :aliases {:run {:main-opts ["-m" "core"]}
           :jvm-opts ["--enable-native-access=ALL-UNNAMED"]} 
 }
```




