create table IF NOT EXISTS task (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  detailedDescription VARCHAR(255),
  creationTime TIMESTAMP,
  subtaskIds INTEGER[],
  priority INTEGER
);

create table IF NOT EXISTS taskSubtask (
  id SERIAL PRIMARY KEY,
  parentId SERIAL REFERENCES task(id) ON DELETE CASCADE,
  childId SERIAL REFERENCES task(id) ON DELETE CASCADE
);