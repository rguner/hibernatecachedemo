CREATE TABLE planets (
  id INTEGER PRIMARY KEY,
  name VARCHAR(255),
  type VARCHAR(255),
  mass NUMBER,
  diameter INTEGER,
  distance NUMBER
);

CREATE TABLE stars (
     id INTEGER PRIMARY KEY,
     name VARCHAR(255),
     type VARCHAR(255),
     mass NUMBER,
     diameter INTEGER,
     distance NUMBER
);

CREATE TABLE moons (
  id INTEGER PRIMARY KEY,
  planet_id INTEGER,
  name VARCHAR(255),
  diameter INTEGER,
  FOREIGN KEY (planet_id) REFERENCES planets(id)
);

CREATE TABLE star_moons (
                       id INTEGER PRIMARY KEY,
                       star_id INTEGER,
                       name VARCHAR(255),
                       diameter INTEGER,
                       FOREIGN KEY (star_id) REFERENCES stars(id)
);