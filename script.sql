CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT null,
    created_at timestamptz default current_timestamp,
	updated_at timestamptz null,
	deleted_at timestamptz null
);

CREATE TABLE accesses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT null,
    created_at timestamptz default current_timestamp,
	updated_at timestamptz null,
	deleted_at timestamptz null
);

CREATE TABLE role_accesses (
    id SERIAL PRIMARY KEY,
    role_id INT NOT NULL REFERENCES roles(id),
    access_id INT NOT NULL REFERENCES accesses(id),
    created_at timestamptz default current_timestamp,
	updated_at timestamptz null,
	deleted_at timestamptz null
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    password TEXT NOT null,
    created_at timestamptz default current_timestamp,
	updated_at timestamptz null,
	deleted_at timestamptz null
);

CREATE TABLE user_roles (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(id),
    role_id INT NOT NULL REFERENCES roles(id),
    created_at timestamptz default current_timestamp,
	updated_at timestamptz null,
	deleted_at timestamptz null
);

INSERT INTO roles (id, name, created_at) VALUES
(1, 'Project Manager', CURRENT_TIMESTAMP),
(2, 'Konsultan', CURRENT_TIMESTAMP),
(3, 'Development', CURRENT_TIMESTAMP);

INSERT INTO accesses (id, name, created_at) VALUES
(1, 'Create project order', CURRENT_TIMESTAMP),
(2, 'Close project order', CURRENT_TIMESTAMP),
(3, 'Delete project order', CURRENT_TIMESTAMP),
(4, 'Read project order', CURRENT_TIMESTAMP),
(5, 'Create sub task project', CURRENT_TIMESTAMP),
(6, 'Update sub task project', CURRENT_TIMESTAMP),
(7, 'Delete sub task project', CURRENT_TIMESTAMP),
(8, 'View project order', CURRENT_TIMESTAMP),
(9, 'View sub task project', CURRENT_TIMESTAMP);


