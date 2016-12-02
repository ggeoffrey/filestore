-- :name add-new-file! :! :n
-- :doc add a new file in the files table
INSERT INTO files
(name, alias, type, length, nbDownloads, owner, receivers, message, creation, lastDownload)
VALUES(:name, :alias, :type, :length, 0, :owner, :receivers, :message, 0, 0)
