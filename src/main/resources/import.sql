INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Hector', 'Cornejo', 'alumno@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Samuel', 'Gomez', 'samuel@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Borja', 'Cornejo', 'alumno@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Pepe', 'Gomez', 'samuel@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Emilio', 'Cornejo', 'alumno@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Satur', 'Gomez', 'samuel@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Jose', 'Cornejo', 'alumno@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Fede', 'Gomez', 'samuel@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Luis', 'Cornejo', 'alumno@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Roberto', 'Gomez', 'samuel@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Marta', 'Cornejo', 'alumno@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Ines', 'Gomez', 'samuel@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Mario', 'Cornejo', 'alumno@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Sheila', 'Gomez', 'samuel@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Miriam', 'Cornejo', 'alumno@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Sendoa', 'Gomez', 'samuel@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Lucas', 'Cornejo', 'alumno@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Angel', 'Gomez', 'samuel@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Adriana', 'Gomez', 'samuel@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Ana', 'Cornejo', 'alumno@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Pedro', 'Gomez', 'samuel@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Alex', 'Cornejo', 'alumno@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Adrian', 'Gomez', 'samuel@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Gonzalo', 'Cornejo', 'alumno@bolsadeideas.com', '2023-04-10', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('David', 'Gomez', 'samuel@bolsadeideas.com', '2023-04-10', '');

INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 999, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('NoteBook', 1999, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony TV', 700, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('OPPO MH13', 225, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sansumg Galaxy J1', 60, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Experia E1', 15, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Bicicleta BH', 9, NOW());

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura de equipos de oficina', null, 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 7);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);

INSERT INTO users (username, password, enabled) VALUES('hector', '$2a$10$8k4sPdn3/syaFAMxREkQF.qyODOk5FH0vOUZ0ZxcvMAw2iRR5Xnyy', 1);
INSERT INTO users (username, password, enabled) VALUES('admin', '$2a$10$drVYR4frAcdtgB8UfimMDu54TCpi2y81RVy9MJU1QGLTE3gcO8.6O', 1);

INSERT INTO authorities (user_id, authority) VALUES(1, "ROLE_USER");
INSERT INTO authorities (user_id, authority) VALUES(2, "ROLE_USER");
INSERT INTO authorities (user_id, authority) VALUES(2, "ROLE_ADMIN");