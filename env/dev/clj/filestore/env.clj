(ns filestore.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [filestore.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[filestore started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[filestore has shut down successfully]=-"))
   :middleware wrap-dev})
