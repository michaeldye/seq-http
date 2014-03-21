(ns seq_http.handler
  (:use ring.util.response)
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]))

(defn init []
  (println "seq_http is starting..."))

(defn destroy []
  (println "seq_http is shutting down..."))

(defn to_int [n] (Integer/parseInt n))

(defn fib [a b] (cons a (lazy-seq (fib b (+ b a)))))

; TODO: handle exceptions; ill input "like curl -i http://localhost:3000/api/repeat/12/aa" yields 500 with stacktrace, yikes!)
; TODO: handle input out of bounds
(defroutes app-routes
  ; context for entire app
  (context "/api" []
    (GET "/repeat/:x/:n" [n x] (response (repeat (to_int n) x)))
    (GET "/count/:n" [n] (response (range (to_int n))))
    (GET "/fib/:n" [n] (response (take (to_int n) (fib 0 1)))))
  (route/not-found "not found"))

(def app
  (-> (handler/api app-routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)))
