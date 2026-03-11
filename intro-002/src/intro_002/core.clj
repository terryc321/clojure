(ns intro-002.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

"vectors 0th indexes array like things "
["a" "vector" 1 "of"  "things"]
(get ["a" "vector" 1 "of"  "things"] 0)
(get ["a" "vector" 1 "of"  "things"] 1)
(get ["a" "vector" 1 "of"  "things"] 2)

"lists"
;;(cons 1 2 ) no cons
'(1 2 3)
(list 1 2 3)

(reverse (list 1 2 3))
;;
;;(append (list 1 2 3) (list 4 5 6))

;; cons becomes conj
(conj '(3 4 5) 2)
;; append becomes concat
(concat '(1 2 3) '(4 5 6))

(concat [1 2 3] [4 5 6])
(vector 1 2 3)

#{:a 1 :b 2}

(vector? '(1 2 3))
(vector? [1 2 3])
'[1 2 3]

(str "asdf" "asdf")

"maps"
{:a 1 :b 2}
(hash-map :a 1 :b 2)
(get {:a 1 :b 2} :a)
(get {:a 1 :b 2} :b)
"we look into :b keyword then :c keyword"
(get-in {:a 0 :b {:c "ho hum"}} [:b :c])

"keywords"
"keywords can be used as hash lookups"
(:a {:a 1 :b 2})


