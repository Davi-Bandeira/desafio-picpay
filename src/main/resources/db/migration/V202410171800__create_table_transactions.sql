CREATE TABLE IF NOT EXISTS transactions (
    id          BIGSERIAL PRIMARY KEY,
    payer       BIGINT,
    payee       BIGINT,
    amount      DECIMAL(10, 2),
    create_at   TIMESTAMP,
    FOREIGN KEY (payer) REFERENCES wallets (id),
    FOREIGN KEY (payee) REFERENCES wallets (id)
);