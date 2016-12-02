(ns filestore.db.core
  (:require
   [conman.core :as conman]
   [mount.core :refer [defstate]]
   [filestore.config :refer [env]]
   [clojure.string :as str]))

(defstate ^:dynamic *db*
           :start (conman/connect! {:jdbc-url (env :database-url)})
  :stop (conman/disconnect! *db*))


;; (mount.core/start #'*db*)
;; (mount.core/stop #'*db*)


(conman/bind-connection *db* "sql/queries.sql")


;; (add-new-file! {:name "test-name"
;;                 :alias (str (java.util.UUID/randomUUID))
;;                 :type "image/jpg"
;;                 :length 29
;;                 :owner "me@me.com"
;;                 :receivers (str/join ";" ["a@a.com", "b@b.com"])
;;                 :message "Blabla…"})
