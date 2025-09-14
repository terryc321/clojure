
(ns hello
  (:require [java-time :as t]
            [clj-antlr.core :as antlr]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            ))


(defn time-str
  "Returns a string representation of a datetime in the local time zone."
  [instant]
  (t/format
   (t/with-zone (t/formatter "hh:mm a") (t/zone-id))
   instant))

(defn run
  "show the time"
  [opts]
  (println "Hello world, the time is" (time-str (t/instant))))

;; grammar has to be capitalized letter 'J'son 
(def json (antlr/parser "grammars/Json.g4"))

  ;; in repl hello/json to see its value
  ;; user> hello/json
  ;; {:parser
  ;;  {:local
  ;;   #object[java.lang.ThreadLocal 0x766fb99b "java.lang.ThreadLocal@766fb99b"],
  ;;   :lexer-grammar
  ;;   #object[org.antlr.v4.tool.LexerGrammar 0x44e11e2c "org.antlr.v4.tool.LexerGrammar@44e11e2c"],
  ;;   :grammar
  ;;   #object[org.antlr.v4.tool.Grammar 0x3765f050 "org.antlr.v4.tool.Grammar@3765f050"]},
  ;;  :opts {}}

;; change namespace in repl
;; (in-ns 'hello)


;; pretty-printer from clojure.pprint
(pp/pprint (json "[1,2,3]"))


(defn depth
  "figure out recursive depth can keep calling until stack over-run"
  [n]
  (println "depth is " n)
  (depth (+ n 1)))


;; clojure blows stack at 4500 items 
;;
;; 1. Unhandled java.lang.StackOverflowError
;;    (No message)
;;              transport.clj:  136  nrepl.transport/bencode/fn
;;              transport.clj:   36  nrepl.transport.FnTransport/send
;;                  print.clj:   92  nrepl.middleware.print/send-streamed
;;                  print.clj:   75  nrepl.middleware.print/send-streamed
;;                  print.clj:  134  nrepl.middleware.print/printing-transport/reify
;;            track_state.clj:  336  cider.nrepl.middleware.track-state/make-transport/reify
;;                 caught.clj:   58  nrepl.middleware.caught/caught-transport/reify
;;                  print.clj:   70  nrepl.middleware.print/replying-PrintWriter/fn
;;        CallbackWriter.java:   39  nrepl.CallbackWriter/write
;;        CallbackWriter.java:   26  nrepl.CallbackWriter/write
;;        BufferedWriter.java:  178  java.io.BufferedWriter/implFlushBuffer
;;        BufferedWriter.java:  169  java.io.BufferedWriter/flushBuffer
;;        BufferedWriter.java:  371  java.io.BufferedWriter/implFlush
;;        BufferedWriter.java:  365  java.io.BufferedWriter/flush
;;           PrintWriter.java:  426  java.io.PrintWriter/implFlush
;;           PrintWriter.java:  418  java.io.PrintWriter/flush
;;                   core.clj: 3737  clojure.core/flush
;;                   core.clj: 3747  clojure.core/prn
;;                   core.clj: 3740  clojure.core/prn
;;                RestFn.java:  140  clojure.lang.RestFn/applyTo
;;                   core.clj:  667  clojure.core/apply
;;                   core.clj: 3759  clojure.core/println
;;                   core.clj: 3759  clojure.core/println
;;                RestFn.java:  424  clojure.lang.RestFn/invoke
;;                  hello.clj:   44  hello/depth
;;                  hello.clj:   41  hello/depth
;; ...
;; ...
;; depth is  4509
;; depth is  4510
;; depth is  4511
;; depth is  4512
;; depth is  4513
;; depth is  4514
;; Execution error (StackOverflowError) at nrepl.transport/bencode$fn (transport.clj:136).
;; null
;; depth is  4514

;; more or less parsing line by line in most advent of code puzzles
;; integers seperated by commas 
(def csvg (antlr/parser "grammars/CommaSV.g4"))
(pp/pprint (csvg "1,2,3"))

;; a list of integers only 
(def numg (antlr/parser "grammars/Numbers.g4"))
;;(pp/pprint (numg "1,2,3"))

;; a list of integers 
(def num2g (antlr/parser "grammars/Numbers2.g4"))
(pp/pprint (num2g "1 2"))
(pp/pprint (num2g "1 2 3"))
(pp/pprint (num2g "1 2 3 4 5 6"))

;; how do we slurp a file ?

;; read file and split by newlines
(def string1 (str/split-lines (slurp "inputs/example.txt")))

;;(pp/pprint (numg string1))

;; (:numValue "1") -> 1
(defn str->int
  "coerce a string to an integer"  
  [v]
  (Integer. v))

;; ((:numValue "1")(:numValue "2")(:numValue "3")) -> (1 2 3)
(defn nvs->ints
  "coerce a string to an integer"  
  [xs]
  (map (fn [pr] (str->int (second pr))) xs))

(def example1input (map (fn [str] (nvs->ints (rest (numg str)))) string1))

;; (def parString1 (map (fn [s] (pp/pprint (numg s))) string1))

(defn length
  "alias for count"
  [xs]
  (count xs))


;; use recur to 


(defn all-increasing?
  [xs]
  (defn helper [xs n]
    (cond
      (empty? xs) true
      true (if (let [diff (- n (first xs))]
                 (and (<= diff -1) (>= diff -3)))
             (recur (rest xs) (first xs)))))
  (cond
    (empty? xs) true
    true (helper (rest xs) (first xs))))

(all-increasing? '(10 7 4 3 2 1))


(defn all-decreasing?
  [xs]
  (defn helper [xs n]
    (cond
      (empty? xs) true
      true (if (let [diff (- n (first xs))]
                 (and (>= diff 1) (<= diff 3)))
             (recur (rest xs) (first xs)))))
  (cond
    (empty? xs) true
    true (helper (rest xs) (first xs))))

(all-decreasing? '(10 7 4 3 2 1))


;; differ by atleast 1 and atmost 3
(defn safe-level?
  "is level safe?"
  [xs]
  (cond
    (all-increasing? xs) true
    (all-decreasing? xs) true 
   true false 
   ))


(safe-level? '())
(safe-level? '(1 2 3))
(safe-level? '(1 2 3 4))

;; can see example1input is safe or not
(map safe-level? example1input)

;; generate a big list of numbers - if no recursion then how do this ?
(defn make-big-list
  "make an arbitrary long list of random numbers"
  [size]
  (defn helper [xs sz]
    (cond
      (< sz 1) xs
      true (recur (conj xs (rand-int 100)) (- sz 1))))
  (helper '() size))


(make-big-list 10)

;; (rand-int 2) ->  either 0 or 1  two options


(defn make-big-list-always-increasing
  "make an arbitrary long list of random numbers always 1 2 3 more than previous value"
  [size]
  (defn helper [xs sz n]
    (cond
      (< sz 1) xs
      true (let [diff (+ 1 (rand-int 3))
                 n2 (+ n diff)]
             (recur (conj xs n2) (- sz 1) n2))))
  (reverse (helper '() size 0)))

(make-big-list-always-increasing 10)

;; (make-big-list-always-increasing 100000)
;; (make-big-list-always-increasing 10000000)
;; (make-big-list-always-increasing 1000000000)

;;(conj 'a-list 'a-value) <=> (cons a-value a-list)

;; (def some-big-list (make-big-list-always-increasing 100000))
;; (all-increasing? some-big-list)

;; have some screwy ways to think in clojure , mostly c type code
;; functional but not thread safe ?
;; 






















