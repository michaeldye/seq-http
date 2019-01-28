(ns seq_http.handler
  (:require [ring.util.response :refer [response]]
            [ring.middleware.json :as middleware]
            [clojure.tools.logging :as log]
            [compojure.route :as route]
            [compojure.core :refer [context defroutes ANY GET]])
  (:gen-class))



(defn init []
  (println "seq_http is starting..."))

(defn destroy []
  (println "seq_http is shutting down..."))

(defn to-int [n] (Integer/parseInt n))

(defn fib [a b] (cons a (lazy-seq (fib b (+ b a)))))

(defn fib-limited [n]
  (if (< (or (to-int (System/getenv "MAX_FIB")) 10) n)
    (throw (IllegalArgumentException. "Provided fib value too large"))
    (take n (fib 0 1N))))

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
    (GET "/repeat/:x/:n" [n x] (response (repeat (to-int n) x)))
    (GET "/count/:n" [n] (response (range (to-int n))))
    (GET "/fib/:n" [n] (response (fib-limited (to-int n))))
  (route/not-found "not found")))

(def app
  (-> app-routes
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)
      (wrap-exception-handling)))
