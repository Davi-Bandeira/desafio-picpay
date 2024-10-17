CREATE TABLE IF NOT EXISTS wallets (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(100),
    cpf         BIGINT,
    email       VARCHAR(100),
    password    VARCHAR(100),
    wallet_type VARCHAR(50),
    balance     DECIMAL(10, 2),
    version     BIGINT
);

CREATE UNIQUE INDEX IF NOT EXISTS cpf_idx ON wallets (cpf);

CREATE UNIQUE INDEX IF NOT EXISTS email_idx ON wallets (email);

INSERT INTO wallets (id, name, cpf, email, password, wallet_type, balance, version)
VALUES (1, 'Joao - User', 12345678900, 'joao@test.com', '123456', 'COMMON', 1000.00, 1);

INSERT INTO wallets (id, name, cpf, email, password, wallet_type, balance, version)
VALUES (2, 'Maria - Lojista', 12345678901, 'maria@test.com', '123456', 'MERCHANT', 1000.00, 1);