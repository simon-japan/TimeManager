create table IF NOT EXISTS task (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  detailedDescription VARCHAR(255),
  creationTime TIMESTAMP,
  subtaskIds INTEGER[],
  priority INTEGER
);