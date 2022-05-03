INSERT INTO
    USER (ID, NAME, EMAIL, SALT, HASH)
VALUES
    (
        1,
        'Adam Andersson',
        'adam@mail.com',
        'a50795b4ea93f4c51f88b3174fdb353d',
        '0ef57ba8904e095c6ce3276964bb70af46b29d57cabdeafdc070a72817ac1a0f'
    ),
    (
        2,
        'Berit Bengtsson',
        'berit@mail.com',
        'd0287a395a3e95b28f19d778da166209',
        'aa1647c5fa4a86d279b3d031c3aa503cf6b5401de44ea2fd6ed4530d52446b65'
    ),
    (
        3,
        'Carl Carlsson',
        'carl@mail.com',
        '79f88e368fb6c33ff0c6a3fc289a0d0e',
        '97c0fddf8d41d3430152fe9eebcde1721b2a6c0a3391cf9ba1cbb4336edf412b'
    );

INSERT INTO
    PRODUCT (id, base64, name, price)
VALUES
    (
        4,
        'awndandakoamdwamdadwadoad7991823',
        'Mango',
        15.9
    ),
    (
        5,
        'könmawldnadjaidja00112adawawadasd',
        'Banana',
        22.0
    ),
    (
        6,
        'knajdnawipdanpåawokdp001ölmawdawdklöm',
        'Apple',
        11.99
    );
INSERT INTO
    ORDER_TABLE (id, created_at, user_id)
VALUES
    (
        7,
        CURRENT_TIMESTAMP(),
        1
    ),
    (
        8,
        CURRENT_TIMESTAMP(),
        1
    ),
    (
        9,
        CURRENT_TIMESTAMP(),
        1
    ),
    (
        10,
        CURRENT_TIMESTAMP(),
        2
    );
INSERT INTO
    ORDER_TABLE_PRODUCTS (order_id, products_id)
VALUES
    (
        7,
        4
    ),
    (
        7,
        5
    ),
    (
        8,
        4
    ),
    (
        8,
        6
    ),
    (
        9,
        4
    ),
    (
        9,
        5
    ),
    (
        9,
        6
    ),
    (
        10,
        6
    );