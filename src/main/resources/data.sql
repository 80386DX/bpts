CREATE TABLE IF NOT EXISTS account (
    id VARCHAR(255) PRIMARY KEY,
    funds DECIMAL(10, 2) NOT NULL
);


INSERT INTO account (id, funds) VALUES ('5533', 1000);
INSERT INTO account (id, funds) VALUES ('2233', 500);
INSERT INTO account (id, funds) VALUES ('2255', 700);
INSERT INTO account (id, funds) VALUES ('22655', 700);
INSERT INTO account (id, funds) VALUES ('55555', 900);
