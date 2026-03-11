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



