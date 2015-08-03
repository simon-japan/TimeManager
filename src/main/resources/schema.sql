create table IF NOT EXISTS task (
  id int primary key auto_increment,
  name varchar(255),
  detailedDescription VARCHAR(255),
  creationTime DATETIME
);