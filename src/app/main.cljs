(ns app.main)

(def a 1)

(defonce b 2)

(defn main! []
  (println "[main]: loading"))

(defn reload! []
  (println "[main] reloaded:" a b))
