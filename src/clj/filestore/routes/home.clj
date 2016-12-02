(ns filestore.routes.home
  (:require [filestore.layout :as layout]
            [compojure.core :refer [defroutes GET POST context]]
            [ring.util.http-response :refer [ok header content-type]]
            [clojure.java.io :as io]
            [filestore.db.core :refer [*db* add-new-file!]]
            [immutant.transactions.scope :as transaction]))

(defn index-page []
  (layout/render
    "index.html" {}))

#_(defn about-page []
    (layout/render "about.html"))



(def file-store-dir "/Users/geoffrey/Desktop/ftp/")


(defn upload-file
  [file]
  (let [uuid (java.util.UUID/randomUUID)
        in-file (:tempfile file)
        out-file (io/file (str file-store-dir uuid))]
    (.renameTo in-file out-file)
    out-file))

(defroutes all-routes
  (GET "/" [] (index-page))
                                        ;(GET "/about" [] (about-page))
  (context
   "/api"
   {}
   (GET "/ping" []
        (-> (str "pong")
            (ok)
            (content-type "text/plain")))
   (POST "/files" {params :params}
         ;;#break
         (transaction/required
          (let [{:keys [file owner receivers message]} params
                uploaded (upload-file (:file params))]
            (add-new-file! {:name (:filename file)
                            :alias (.getName uploaded)
                            :length (.length uploaded)
                            :type (:content-type file)
                            :owner owner
                            :receivers receivers
                            :message message})))
         (ok "ok"))))
