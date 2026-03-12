
(ns core
  (:require [cljfx.api :as fx]))

(defn opts [] 
  (java.lang.management.ManagementFactory/getRuntimeMXBean)
  (.getInputArguments (java.lang.management.ManagementFactory/getRuntimeMXBean)))


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
  (println "Hello, A world!")
  (println (format "java class path = %s" (System/getProperty "java.class.path")))
  (fx/create-renderer)
  (fx/on-fx-thread (root)))


;;(System/getProperty "java.class.path")
;; (System/getProperty "java.version") ;; "25.0.2"
;;/home/terry/.m2/repository/org/openjfx/javafx-graphics/22-ea+16/javafx-graphics-22-ea+16.jar
;;/home/terry/.m2/repository/org/openjfx/javafx-graphics/22-ea+16/javafx-graphics-22-ea+16-linux.jar
;; WARNING: A restricted method in java.lang.System has been called
;; WARNING: java.lang.System::load has been called by com.sun.glass.utils.NativeLibLoader in an unnamed module (file:/home/terry/.m2/repository/org/openjfx/javafx-graphics/22-ea+16/javafx-graphics-22-ea+16-linux.jar)
;; WARNING: Use --enable-native-access=ALL-UNNAMED to avoid a warning for callers in this module
;; WARNING: Restricted methods will be blocked in a future release unless native access is enabled

;;   C-c C-c (main) ~/code/clojure/mandelbrot/tutorial$ 
