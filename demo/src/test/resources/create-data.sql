INSERT INTO
    USER (ID, NAME, EMAIL, PASSWORD)
VALUES
    (
        1,
        'Test',
        'test@test.com',
        '95 22|5cd182d2134d7433818f55ae69d053938b298e558830677067a1883473422be867fa99b3ee5e101fa04d742e08793943'
    ),
    (
        2,
        'Test2',
        'test2@test.com',
        '42 53|2725318d244d5e426d534c2586b961d7e60387223b1118f642fa879907608e9c676be28bf3b33eb4675f01dd63955441'
    ),
    (
        3,
        'Test3',
        'test3@test.com',
        '69 25|52c65e208a38e7ad559c002a550fa61563cf744092881dcc70dbcbc5da064090d0b89f7301e4f8261941013ea7491c0b'
    );

INSERT INTO
    AUTHORITY (ID, NAME)
VALUES
    (1, 'BASIC'),
    (2, 'MODERATOR'),
    (3, 'ADMIN');

INSERT INTO
    USER_AUTHORITIES (USER_ID, AUTHORITIES_ID)
VALUES
    (1, 1),
    (2, 1),
    (2, 2),
    (3, 1),
    (3, 2),
    (3, 3);
