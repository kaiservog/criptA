create table info (
  id INT PRIMARY KEY NOT NULL,
  service text not null,
  value text not null
);

CREATE sequence seq_info START 1001;