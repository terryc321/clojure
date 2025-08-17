(ns my-stuff.core
  (:gen-class))

;; if this  file fails to load , set classpath explicitly
;; export CLASSPATH="/home/terry/.m2/repository"


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

;; -- Date and Time via Interop

(import (java.time LocalDate LocalTime LocalDateTime))

;; What's the current day?
(LocalDate/now)
;; => #object[java.time.LocalDate 0x28cb30b8 "2023-11-01"]
;; You may see a different result.

;; What's the current time?
(LocalTime/now)
;; => #object[java.time.LocalTime 0x4dc69d04 "12:53:09.791974265"]
;; You may see a different result.

;; What's the date and time of today?
(LocalDateTime/now)
;; => #object[java.time.LocalDateTime 0x6d7119a7 "2023-11-03T12:53:36.765946815"]
;; You may see a different result.

;; Does date1 come before date2?
(let [date1 (LocalDate/parse "2023-01-01")
      date2 (LocalDate/parse "2023-10-01")]
  (.isBefore date1 date2))
;; => true

;; Add N days to a date
(.plus (LocalDate/now) (java.time.Period/ofDays 10))
;; => #object[java.time.LocalDate 0x507f3b0d "2023-11-13"]
;; Or
(.plusDays (LocalDate/now) 10)
;; => #object[java.time.LocalDate 0x439c6250 "2023-11-13"]

;; What's the date a year before?
(.minus (LocalDate/parse "2023-11-03") (java.time.Period/ofYears 1))
;; => #object[java.time.LocalDate 0x37380cf2 "2022-11-03"]

;; Difference in days between a date and a year after
(.between (java.time.temporal.ChronoUnit/DAYS)
          (LocalDate/parse "2023-11-03")
          (.plusYears (LocalDate/parse "2023-11-03")
                      1))
;; => 366

;; What day of the week was it?
(.getDayOfWeek (LocalDate/parse "2023-11-03"))
;; => #object[java.time.DayOfWeek 0x7823658a "FRIDAY"]

;; How to format the output in a specific way?
(.format (LocalDate/parse "2023-11-01")
         (java.time.format.DateTimeFormatter/ofPattern "yyyy/MM/dd"))
;; => "2023/11/01"

;; To parse the date we just formatted
(LocalDate/parse "2023/11/01"
                 (java.time.format.DateTimeFormatter/ofPattern "yyyy/MM/dd"))
;; => #object[java.time.LocalDate 0xa349bd0 "2023-11-01"]

;; This opens up the door to creating your own parsers.
(defn ydm
  "A parses similar to ydm() from the R package lubridate."
  [s]
  (LocalDate/parse s
                   (java.time.format.DateTimeFormatter/ofPattern "yyyyddMM")))
;; => #'user/ydm


(ydm "20170108")

;; => #object[java.time.LocalDate 0x3135d642 "2017-08-01"]
;; -- clojure.java-time

(require '[java-time.api :as jt])
;; What's the current day?
(jt/local-date)
;; => #object[java.time.LocalDate 0x28cb30b8 "2023-11-01"]
;; You may see a different result.

;; What's the current time?
(jt/local-time)
;; => #object[java.time.LocalTime 0x7f536ee5 "23:53:32.896427602"]
;; You may see a different result.

;; What's the date and time of today?
(jt/local-date-time)
;; => #object[java.time.LocalDateTime 0x3ac70fac "2023-11-01T23:54:14.020607313"]
;; You may see a different result.

;; Does date1 come before date2?
(let [date1 (jt/local-date "2023-01-01")
      date2 (jt/local-date "2023-10-01")]
  (jt/before? date1
              date2))
;; => true

;; Add N days to a date
(jt/plus (jt/local-date "2023-11-01")
         (jt/days 10))
;; => #object[java.time.LocalDate 0x165637c5 "2023-11-13"]

;; What's the date a year before?
(jt/minus (jt/local-date "2023-11-03") (jt/years 1))
;; => #object[java.time.LocalDate 0x6e927fd0 "2022-11-03"]

;; Difference in days between a date and a year after
(jt/time-between :days
                 (jt/local-date "2023-11-03")
                 (jt/plus (jt/local-date "2023-11-03")
                          (jt/years 1)))
;; => 366

;; What day of the week was it?
(jt/day-of-week (jt/minus (jt/local-date) (jt/years 1)))
;; => #object[java.time.DayOfWeek 0x10fb6e5f "THURSDAY"]

;; How to format the output in a specific way?
(jt/format :iso-date (jt/local-date "2023-11-01"))
;; => "2023-11-01"

(jt/format "yyyy/MM/dd" (jt/local-date "2023-11-01"))
;; => "2023/11/01"

;; To parse the date we just formatted
(jt/local-date "yyyy/MM/dd" "2023/11/01")

;; This opens up the door to creating your own parsers.
(defn ydm
  "A parses similar to ydm() from the R package lubridate."
  [s]
  (jt/local-date "yyyyddMM" s))
;; => #'user/ydm

(ydm "20170108")
;; => #object[java.time.LocalDate 0x4851aa55 "2017-08-01"]


