(ns filestore.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[filestore started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[filestore has shut down successfully]=-"))
   :middleware identity})
