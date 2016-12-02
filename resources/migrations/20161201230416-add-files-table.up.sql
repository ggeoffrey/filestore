CREATE TABLE files (
id INTEGER PRIMARY KEY AUTOINCREMENT,
alias VARCHAR(50),
name VARCHAR(255),
type VARCHAR(50),
length INTEGER,
nbDownloads INTEGER,
owner VARCHAR(100),
receivers TEXT,
message TEXT,
creation INTEGER,
lastDownload INTEGER
);
