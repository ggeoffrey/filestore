(ns user
  (:require [mount.core :as mount]
            filestore.core))

(defn start []
  (mount/start-without #'filestore.core/repl-server))

(defn stop []
  (mount/stop-except #'filestore.core/repl-server))

(defn restart []
  (stop)
  (start))


