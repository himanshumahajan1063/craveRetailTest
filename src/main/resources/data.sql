CREATE TABLE IF NOT EXISTS Users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  age INT
);
INSERT INTO Users (name, age) VALUES ('John Doe', 30);
INSERT INTO Users (name, age) VALUES ('Jane Smith', 41);