(defproject seq-http "0.3.0"
  :description "HTTP service that generates basic mathematical sequences given input"
  :url "https://github.com/michaeldye/seq-http"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring "1.8.0"]
                 [ring/ring-json "0.5.0"]
                 [org.clojure/tools.logging "0.4.1"]
                 [compojure "1.6.1"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler seq-http.handler/app
         :init seq-http.handler/init
         :destroy seq-http.handler/destroy
         :stacktrace-middleware ring.middleware.stacktrace/wrap-stacktrace-log}
  :profiles {
              :production {
                :ring {:stacktraces? false
                       :auto-reload? false} } })
