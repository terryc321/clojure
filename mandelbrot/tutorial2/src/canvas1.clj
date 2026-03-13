(ns canvas1
  (:require [cljfx.api :as fx])
  (:import [javafx.scene.canvas Canvas]
           [javafx.scene.paint Color]))


;; (def *window-size (atom {:width 800 :height 600}))
(def *window-size (atom {:width 640 :height 480}))

(defn canvas-fullscreen [{:keys [width height]}]
  {:fx/type :canvas
   :width width
   :height height
   :draw (fn [^javafx.scene.canvas.Canvas canvas]
           (let [gc (.getGraphicsContext2D canvas)]
             (.clearRect gc 0 0 width height)
             (.setFill gc Color/BLUE) ;;(.setFill gc :blue)
             (.fillRect gc 0 0 width height)
             (.setFill gc Color/RED) ;;(.setFill gc :blue)
             (.fillRect gc 50 50 100 100)
             ))})




(def renderer
  (fx/create-renderer
    :middleware
    (fx/wrap-map-desc
      (fn [size]
        {:fx/type :stage
         :showing true
         :width (:width size)
         :height (:height size)
         :scene {:fx/type :scene
                 :root {:fx/type :v-box
                        :children [{:fx/type canvas-fullscreen
                                    :width (:width size)
                                    :height (:height size)}]}}}))))

;; Initialize with initial window size
(fx/mount-renderer *window-size renderer)
;; C + u-c-o  clear cider repl window


