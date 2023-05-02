INSERT INTO account (id, first_name, last_name, routing_number, national_identification_number, account_number, user_id)
    VALUES (random_uuid(), 'Tony', 'Stark', 623852453, 33278129, 23100299907, 1000);


INSERT INTO transaction (id, user_id, status, amount, message, payment_transaction_id, wallet_transaction_id, fee, created_at)
    VALUES (random_uuid(), 1000, 'IN_PROGRESS', -500, 'Bank transfer to your account',random_uuid(), 1, 50, {ts '2023-05-01 11:01:1.00'});

INSERT INTO transaction (id, user_id, status, amount, message, payment_transaction_id, wallet_transaction_id, fee, created_at)
    VALUES (random_uuid(), 1000, 'COMPLETED', -20, 'Bank transfer to your account',random_uuid(), 2, 2, {ts '2023-05-01 10:00:00.00'});

INSERT INTO transaction (id, user_id, status, amount, message, payment_transaction_id, wallet_transaction_id, fee, created_at)
    VALUES (random_uuid(), 1000, 'FAILED', -20, 'Bank transfer to your account',random_uuid(), 3, 2, {ts '2023-05-01 09:50:00.00'});

INSERT INTO transaction (id, user_id, status, amount, message, payment_transaction_id, wallet_transaction_id, fee, created_at)
    VALUES (random_uuid(), 1000, 'COMPLETED', -200, 'Bank transfer to your account',random_uuid(), 4, 20, {ts '2023-04-01 18:10:00.00'});

INSERT INTO transaction (id, user_id, status, amount, message, payment_transaction_id, wallet_transaction_id, fee, created_at)
    VALUES (random_uuid(), 1001, 'COMPLETED', -100, 'Bank transfer to your account', random_uuid(), 5, 10, {ts '2023-04-01 17:10:00.00'});

INSERT INTO transaction (id, user_id, status, amount, message, payment_transaction_id, wallet_transaction_id, fee, created_at)
    VALUES (random_uuid(), 1002, 'COMPLETED', -100, 'Bank transfer to your account', random_uuid(), 6, 10, {ts '2023-04-30 16:10:12.00'});
