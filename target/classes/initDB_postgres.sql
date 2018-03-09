CREATE TABLE user_personal (
  id int not null PRIMARY KEY,
  first_name varchar(255) NOT NULL,
  second_name varchar(255) not NULL,
  last_name varchar(255) NOT NULL
);

CREATE TABLE user_data (
  id int NOT NULL PRIMARY KEY,
  data_reg varchar(100) NOT NULL,
  status boolean,
  login varchar(255),
  password varchar(255),
  user_personal_id int NOT NULL,
  FOREIGN KEY (user_personal_id) REFERENCES user_personal(id),
  UNIQUE (user_personal_id)
);


CREATE TABLE bank_account (
  id int NOT NULL PRIMARY KEY,
  status boolean,
  sum int NOT NULL,
  user_data_id int NOT NULL,

  FOREIGN KEY (user_data_id) REFERENCES user_data(id),
  UNIQUE (user_data_id)
);

CREATE TABLE bank_transfer (
  id int NOT NULL PRIMARY KEY,
  status boolean,
  sum int NOT NULL,
  bank_account_id int NOT NULL,
  user_data_id int NOT NULL,
  FOREIGN KEY (bank_account_id) REFERENCES bank_account(id),
  FOREIGN KEY (user_data_id) REFERENCES user_data(id),

  UNIQUE (bank_account_id,user_data_id)
);

CREATE TABLE request_bank_account (
  id int NOT NULL PRIMARY KEY,
  sum int NOT NULL,
  user_data_id int NOT NULL,

  FOREIGN KEY (user_data_id) REFERENCES user_data(id),
  UNIQUE (user_data_id)
);

CREATE TABLE roles_data (
  id int NOT NULL PRIMARY KEY,
  role_name varchar(250) NOT NULL
);

CREATE TABLE user_roles (
  id_user_data int NOT NULL,
  id_role int NOT NULL ,
  FOREIGN KEY (id_user_data) REFERENCES user_data(id),
  FOREIGN KEY (id_role) REFERENCES roles_data(id),
  UNIQUE (id_user_data, id_role)
);

--create roles
INSERT INTO roles_data VALUES (1,'ROLES_USER');
INSERT INTO roles_data VALUES (2,'ROLES_ADMIN');


