# playground

[Go to Mandelbrot (day two)](#day-two)

we can run mandelbrot1 image generator pgm will output readable text to standard output

```
clj -X 'mandelbrot1/-main'
```

Q. how do we pass arguments to program ?

something to do with opts yes , but how.

Q. 

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


# Day Two

mandelbrot using ``` https://github.com/alanforr/complex ``` says clojars 0.1.12 . 

we can see this from clojars , we verify clojars complex/complex latest version is indeed 0.1.12 . so we can also verify that we are indeed able to find latest version of a library.

## Q . what if library was not called complex/complex ?

```
clj -X:deps find-versions :lib complex/complex
Downloading: complex/complex/maven-metadata.xml from clojars
{:mvn/version "0.1.5"}
{:mvn/version "0.1.6"}
{:mvn/version "0.1.7"}
{:mvn/version "0.1.8"}
{:mvn/version "0.1.9"}
{:mvn/version "0.1.10"}
{:mvn/version "0.1.11"}
{:mvn/version "0.1.12"}
```

Lets start cider-jack-in-clj .

## complex/complex 

trouble detecting complex/complex even though its in the deps.edn

```
{
 :paths ["src"]
 :deps
 {
  clojure.java-time/clojure.java-time {:mvn/version "1.1.0"}
  cljfx/cljfx {:mvn/version "1.10.6"}
  complex/complex {:mvn/version "0.1.12"}
  }
 :aliases {:run {:main-opts ["-A" "mandelbrot1"]}
           :jvm-opts ["--enable-native-access=ALL-UNNAMED"]} 
 }

```

```
> mvn install:install-file \
  -Dfile=complex-0.1.12.jar \
  -DgroupId=complex \
  -DartifactId=complex \
  -Dversion=0.1.12 \
  -Dpackaging=jar
```


```
> clj -r 
```

## complex/complex solved

```
> clj
user=> (require '[complex.core :as c])
nil

user=> c/complex
#object[complex.core$complex 0x408613cc "complex.core$complex@408613cc"]

user=> (c/complex 1)
#object[org.apache.commons.math3.complex.Complex 0x91c4a3f "(1.0, 0.0)"]
```

so why is it called complex.core then , whats the purpose , why not complex/complex 

We can see what is in the jar file , 

```
> jar -tf jar -tf ~/.m2/repository/complex/complex/0.1.12/complex-0.1.12.jar

META-INF/MANIFEST.MF
META-INF/maven/complex/complex/pom.xml
META-INF/leiningen/complex/complex/project.clj
META-INF/leiningen/complex/complex/README.md
META-INF/leiningen/complex/complex/LICENSE
complex/
complex/core$sqrt$fn__88.class
complex/core$nth_root.class
complex/core$add2c.class
complex/core$tanh$fn__91.class
complex/core$stringify.class
complex/core$real_part.class
complex/core$_.class
complex/core$sqrt1z$fn__94.class
complex/core$imaginary_part.class
complex/core$polar_form.class
complex/core$subtract2c.class
complex/core$sqrt.class
complex/core$abs.class
complex/core$divide2c.class
complex/core$equals.class
complex/core$sin.class
complex/core$pow.class
complex/core$_SLASH_.class
complex/core$sqrt1z.class
complex/core$cosh.class
complex/core$negate.class
complex/core$reciprocal.class
complex/core$log$fn__79.class
complex/core$conjugate.class
complex/core$loading__4958__auto__.class
complex/core$exp.class
complex/core$multiply2c.class
complex/core$abs$fn__56.class
complex/core$exp$fn__76.class
complex/core$cos$fn__70.class
complex/core__init.class
complex/core$tan.class
complex/core$tanh.class
complex/core$argument$fn__59.class
complex/core$cos.class
complex/core$_STAR_.class
complex/core$argument.class
complex/core$sin$fn__67.class
complex/core$complex.class
complex/core$log.class
complex/core$tan$fn__73.class
complex/core$cosh$fn__85.class
complex/core$sinh$fn__82.class
complex/core$fn__37.class
complex/core$_PLUS_.class
complex/core$powc.class
complex/core$sinh.class
META-INF/
META-INF/maven/
META-INF/maven/complex/
META-INF/maven/complex/complex/
META-INF/maven/complex/complex/pom.properties
complex/.DS_Store
complex/core.clj
```

so in the source code it will look like 

```
(ns mandelbrot1
  (:require [complex.core :as c]))
==> nil
```

which means the library can now be found.

The complex number in complex/complex looks to be just floating point or double precision, not arbitrary precision floating point in any way which is unfortunate.  So we probably would be better off if we did not use clojure for this project as there is some amount of mathematical knowledge features.

We can increase precision on the floating point 

# complex/complex

represented as a java double

```
(ns mandelbrot1
  (:require [complex.core :as c]))

(def a (c/complex 1 2))

;; split complex number into the two components real part and imaginary part
(c/real-part a)

(c/imaginary-part a)

;; square complex number
(c/* a a)

;; add a complex number
(c/+ a (complex 3 4))
;; expect (complex 4 6)

(c/= (complex 4 6) (c/+ a (complex 3 4)))
;; should be true

```

## caution complex

clearly its quite easy to produce some valuable nonsensical results

```
mandelbrot1> (> 4.000000000000001 4)
==> true

mandelbrot1> (> 4.0000000000000001 4)
==> false
```

# Day Three

try get something on the screen with humble ui

```
> clj -r
Downloading: io/github/humbleui/skija-linux-x64/0.116.2/skija-linux-x64-0.116.2.pom from central
Downloading: io/github/humbleui/skija-macos-x64/0.116.2/skija-macos-x64-0.116.2.pom from central
Downloading: io/github/humbleui/jwm/0.4.18/jwm-0.4.18.pom from central
Downloading: io/github/humbleui/skija-windows-x64/0.116.2/skija-windows-x64-0.116.2.pom from central
Downloading: io/github/humbleui/skija-macos-arm64/0.116.2/skija-macos-arm64-0.116.2.pom from central
Downloading: io/github/humbleui/types/0.2.0/types-0.2.0.pom from central
Downloading: io/github/tonsky/extend-clj/0.1.0/extend-clj-0.1.0.pom from clojars
Downloading: io/github/humbleui/skija-shared/0.116.2/skija-shared-0.116.2.pom from central
Downloading: io/github/humbleui/skija-windows-x64/0.116.2/skija-windows-x64-0.116.2.jar from central
Downloading: io/github/humbleui/types/0.2.0/types-0.2.0-clojure.jar from central
Downloading: io/github/humbleui/skija-linux-x64/0.116.2/skija-linux-x64-0.116.2.jar from central
Downloading: io/github/humbleui/jwm/0.4.18/jwm-0.4.18.jar from central
Downloading: io/github/humbleui/skija-macos-x64/0.116.2/skija-macos-x64-0.116.2.jar from central
Downloading: io/github/humbleui/skija-macos-arm64/0.116.2/skija-macos-arm64-0.116.2.jar from central
Downloading: io/github/humbleui/skija-shared/0.116.2/skija-shared-0.116.2.jar from central
Downloading: io/github/tonsky/extend-clj/0.1.0/extend-clj-0.1.0.jar from clojars
WARNING: Implicit use of clojure.main with options is deprecated, use -M -r

```

The ai slop given was humble1.clj

```
(ns humble1
  (:require [io.github.humbleui.ui :as ui]))


(ui/defcomp app []
  [ui/center
   [ui/label "Hello, world"]])

(defn -main [& args]
  (ui/start-app!
    (ui/window #'app)))

```

heres the updated deps.edn , theres no humble-ui on clojars , so i guess no maven version

```
;; completely new deps.edn for humble.ui no idea what any of this is actually doing at all 
{:paths ["src" "resources"]
 :deps
 {org.clojure/clojure                  {:mvn/version "1.12.0"}
  ;;io.github.humbleui/humbleui          {:mvn/version "0.0.9"}
  ;; io.github.humbleui/humbleui {:git/sha "c5b4179e44c486b2d9171be845eb14ff8a7936cf"}
  io.github.humbleui/humbleui {:git/sha "774853e4ec912168ad96fff96c0296dda98531f6"}
  io.github.humbleui/types$clojure     {:mvn/version "0.2.0"}
  io.github.humbleui/jwm               {:mvn/version "0.4.18" :exclusions [io.github.humbleui/types]}
  io.github.humbleui/skija-windows-x64 {:mvn/version "0.116.2" :exclusions [io.github.humbleui/types]}
  io.github.humbleui/skija-linux-x64   {:mvn/version "0.116.2" :exclusions [io.github.humbleui/types]}
  io.github.humbleui/skija-macos-x64   {:mvn/version "0.116.2" :exclusions [io.github.humbleui/types]}
  io.github.humbleui/skija-macos-arm64 {:mvn/version "0.116.2" :exclusions [io.github.humbleui/types]}
  io.github.tonsky/extend-clj          {:mvn/version "0.1.0"}}

 :aliases
 {:dev
  {:main-opts   ["-m" "user"]
   :extra-paths ["dev" "test"]
   :extra-deps  {io.github.tonsky/duti       {:git/sha "fc833a87a8687b67e66281e216eeee1ad6048168"}
                 io.github.tonsky/clj-reload {:mvn/version "0.7.0"}
                 criterium/criterium         {:mvn/version "0.4.6"}
                 com.clojure-goes-fast/clj-async-profiler {:mvn/version "1.3.0"}}
   :jvm-opts    ["-ea"
                 "-Dclojure.main.report=stderr"
                 "-Duser.language=en"
                 "-Duser.country=US"
                 "-Dfile.encoding=UTF-8"
                 "-Djdk.attach.allowAttachSelf"
                 "-XX:+UnlockDiagnosticVMOptions"
                 "-XX:+DebugNonSafepoints"
                 "-XX:+EnableDynamicAgentLoading"
                 "-Dclj-async-profiler.output-dir=."]}
  }}

```

We can look at what is defined in ui 

```
(ns humble1
  (:require [io.github.humbleui.ui :as ui]
            [clojure.repl :as repl]))

;; dir is part of clojure.repl
;; shows library loaded

(repl/dir io.github.humbleui.ui)

animation
backdrop
button
canvas
center
checkbox
clickable
clip
clip-rrect
column
default-theme
draggable
dynamic
event-listener
focus-controller
focusable
gap
grid
halign
height
hoverable
image
image-snapshot
key-listener
label
max-width
mouse-listener
on-key-focused
padding
rect
rounded-rect
row
shadow
shadow-inset
slider
stack
start-app!
svg
text-field
text-input
text-listener
toggle
tooltip
valign
vscroll
vscrollbar
width
window
with-bounds
with-context
with-cursor
with-scale

```
