INSERT INTO helloworld(language_code, text)
VALUES ('en', 'Hello World'),
       ('sr', 'Здраво Свету'),
       ('zh', '你好世界'),
       ('sl', 'Pozdravljen svet'),
       ('it', 'Ciao mondo'),
       ('es', '¡Hola Mundo'),
       ('cs', 'Ahoj světe'),
       ('de', 'Hallo Welt'),
       ('ja', 'こんにちは世界'),
       ('ar', 'مرحبا بالعالم')
ON CONFLICT DO NOTHING;