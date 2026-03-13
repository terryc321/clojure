
(ns humble1
  (:require [io.github.humbleui.ui :as ui]
            [clojure.repl :as repl]))

;; dir is part of clojure.repl

;; shows library loaded

(repl/dir io.github.humbleui.ui)

;;(require '[io.github.humbleui.ui :as ui])

(ui/defcomp app []
  [ui/center
   [ui/label "Hello, world"]])

(defn -main [& args]
  (ui/start-app!
    (ui/window #'app)))   
