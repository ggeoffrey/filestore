(ns filestore.handler
  (:require [compojure.core :refer [routes wrap-routes]]
            [filestore.layout :refer [error-page]]
            [filestore.routes.home :refer [all-routes]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.multipart-params :refer [wrap-multipart-params]]
            [compojure.route :as route]
            [filestore.env :refer [defaults]]
            [mount.core :as mount]
            [filestore.middleware :as middleware]))

(mount/defstate init-app
                :start ((or (:init defaults) identity))
                :stop  ((or (:stop defaults) identity)))

(def app-routes
  (routes
   (-> #'all-routes
        (wrap-routes middleware/wrap-csrf)
        (wrap-routes middleware/wrap-formats))
    (route/not-found
      (:body
       (error-page {:status 404
                    :title  "page not found"})))))


(defn app [] (middleware/wrap-base #'app-routes))
