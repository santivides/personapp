CREATE TABLE telefono (
  id INT AUTO_INCREMENT PRIMARY KEY,
  numero VARCHAR(15) NOT NULL,
  tipo VARCHAR(10),
  persona_id INT,
  FOREIGN KEY (persona_id) REFERENCES persona(id)
);
