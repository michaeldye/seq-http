(defproject seq_http "0.2.0"
  :description "HTTP service that generates basic mathematical sequences given input"
  :url "https://github.com/michaeldye/seq_http"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [ring "1.4.0"]
                 [ring/ring-json "0.4.0"]
                 [compojure "1.4.0"]]
  :plugins [[lein-ring "0.9.6"]]
  :ring {:handler seq_http.handler/app
         :init seq_http.handler/init
         :destroy seq_http.handler/destroy
         :stacktrace-middleware ring.middleware.stacktrace/wrap-stacktrace-log}
  :profiles {
              :production {
                :ring {:stacktraces? false
                       :auto-reload? false} } })
