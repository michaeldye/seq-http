(ns seq-http.handler
  (:require [ring.util.response :refer [response]]
            [ring.middleware.json :as middleware]
            [clojure.tools.logging :as log]
            [compojure.route :as route]
            [compojure.core :refer [context defroutes ANY GET]])
  (:gen-class))

(defn init []
  (println "seq-http is starting..."))

(defn destroy []
  (println "seq-http is shutting down..."))

(defn to-int [n]
  (cond
    (instance? String n) (Integer/parseInt n)
    :else (int n)))

(defn -fib-sum-pairs [^long n]
  (let [next-fib (fn [[a b]] [b (+ a b)])
        fibs (iterate next-fib [0N 1N])]
    (take n fibs)))

(defn fib [n] (cond
                (< n 0) '()
                (= n 0) '(0)
                :else (let [ss (-fib-sum-pairs n)]
                (cons (ffirst ss) (map last ss)))))

(defn wrap-exception-handling
  [handler]
  (fn [request]
    (try
      (handler request)
      (catch Exception e
        (log/error e)
        {:status 400 :body "Invalid data"}))))

(defroutes app-routes
  ; context for entire app
  (context "/api" []
    (GET "/repeat/:x/:n" [n x] (response (repeat (to-int n) (to-int x ))))
    (GET "/count/:n" [n] (response (range (to-int n))))
    (GET "/fib/:n" [n] (response (fib (to-int n))))
  (route/not-found "not found")))

(def app
  (-> app-routes
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)
      (wrap-exception-handling)))
