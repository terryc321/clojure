
(ns mandelbrot2
  (:require [complex.core :as c])
  (:require [clojure.math :as math]))

;;(math/round 3.2)

(def a (c/complex 1 2))

;; split complex number into the two components real part and imaginary part
(c/real-part a)

(c/imaginary-part a)

;; square complex number
(c/* a a)
;; z^2 = (a+ib)(a+ib) = (aa - bb) + i(2ab)
;; expect following to be true 
(= (c/* a a) (let [real 1.0 
                   imag 2.0]
               (c/complex (+ (* real real)(- (* imag imag))) (* 2 real imag))))


;; add a complex number
(c/+ a (c/complex 3 4))
;; expect (complex 4 6)

(= (c/complex 4 6) (c/+ a (c/complex 3 4)))
;; should be true

;; let z = a + ib
;; complex number has escaped if |z| > 2
;; |z| = sqrt ( a^2 + b^2 )
;;
;; escape condition is if |z| > 2 
;; or |z|^2 > 4 if square both sides
;; |z|^2 = (a^2 + b^2)
;; hence if (a^2 + b^2) > 4 for some complex number z
;;
;; z_(n+1) = z_(n) ^ 2 + c 
;; Let z_0 be 0
;; we specify what the complex number c should be
;;
;; we keep iterating for 1000 times until we determine it has not yet escaped or it has escaped

(defn has-escaped [z]
  (let [real (c/real-part z)
        imag (c/imaginary-part z)]
    (> (+ (* real real) (* imag imag)) 4)))

;;

;; if we use z0 as 0.0 initially then first iteration z^2 + c is just 0^2 + c or just c 
(defn iter [c]
  (loop [z c
         ntimes 0]
    ;;(println (format "z = %s with c = %s" z c))
    (cond
      (> ntimes 1000) false
      (has-escaped z)  ntimes
      true (recur (c/+ (c/* z z) c) (+ ntimes 1)))))

;; from 1 to 1001 ... yields at most 254 
(defn grayscale [n]
  (cond
    true (+ 1 (math/round (* n (/ 253 1000))))))


;; X -1.5 to 1.0 in step of 0.001
;; Y 
(defn mandelbrot []
  (let [xs (range -1.5 1.0 0.001)
        ys (range -1.0 1.0 0.001)
        wid (count xs)
        hgt (count ys)
        ]
    (println "P2")
    (println "# test.pgm")
    (println (format "%d %d" wid hgt))
    (println (format "%d" 254)) ;; grayscale 254
    (doseq [y ys]
      (doseq [x xs]
        (let [esc (iter (c/complex x y))]
          (cond
            (= esc false) (print (format "%d " 0))            
            true (print (format "%d " (grayscale esc))))))
      (println ""))
    (println "")))


;; in pgm file format P2 gray scale image 
;; 0 is black 
;; 254 is white



;; ;; caution to the wind
;; mandelbrot1> (> 4.000000000000001 4)
;; true
;; mandelbrot1> (> 4.0000000000000001 4)
;; false

;; just ignore options passed to program!
(defn -main [opts]
  (mandelbrot))



;; loop 1000 times until 


;; (defn time-str
;;   "Returns a string representation of a datetime in the local time zone."
;;   [instant]
;;   (t/format
;;     (t/with-zone (t/formatter "hh:mm a") (t/zone-id))
;;     instant))

;; (defn run [opts]
;;   (println "Hello world, the time is" (time-str (t/instant))))

