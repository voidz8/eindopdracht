INSERT into client (company_name, email, debtor_number) VALUES('Profplastic', 'info@profplastic.com', 1234)
INSERT into client (company_name, email, debtor_number) VALUES('Hypesply', 'info@hypesply.com', 12)
INSERT into client (company_name, email, debtor_number) VALUES('Hsproxies', 'info@hsproxies.com', 11)
INSERT into client (company_name, email, debtor_number) VALUES('Polaris', 'info@polaris.com', 123)

INSERT into orders(company_name, production_date, delivery_date) VALUES ( 'Profplastic', '2020-02-02', '2020-01-02')
INSERT into orders(company_name, production_date, delivery_date) VALUES ( 'Profplastic', '2020-02-02', '2020-01-02')
INSERT into orders(company_name, production_date, delivery_date) VALUES ( 'Profplastic', '2020-02-02', '2020-01-02')
INSERT into orders(company_name, production_date, delivery_date) VALUES ( 'Profplastic', '2020-02-02', '2020-01-02')

INSERT into machine(machine) VALUES ('PORTAALFREES')
INSERT into machine(machine) VALUES ('CONVENTIONEEL')
INSERT into machine(machine) VALUES ('LASER')
INSERT into machine(machine) VALUES ('BUIGBANK')
INSERT into machine(machine) VALUES ('ZAAG')

INSERT into product(drawing_number, amount, material, operation_time) VALUES ('a1', 1,'HMPE blauw', 60)
INSERT into product(drawing_number, amount, material, operation_time) VALUES ('a2', 5,'POM blauw', 60)
INSERT into product(drawing_number, amount, material, operation_time) VALUES ('a3', 3,'NYLON naturel', 60)
INSERT into product(drawing_number, amount, material, operation_time) VALUES ('a4', 10,'HDPE zwart', 60)
INSERT into product(drawing_number, amount, material, operation_time) VALUES ('a5', 15,'HMPE blauw', 60)

INSERT into app_user(username, email, password) VALUES ('chip', 'chip@example.com', '$2y$12$I6fZQ7QIDGcw1s/QyvwVnu9G008ENu0Vp2YuxgebYNX4MdT3HTkIa')
INSERT into app_user(username, email, password) VALUES ('harry','harry@example.com','$2y$12$I6fZQ7QIDGcw1s/QyvwVnu9G008ENu0Vp2YuxgebYNX4MdT3HTkIa')
INSERT into app_user(username, email, password) VALUES ('phase', 'phase@example.com', '$2y$12$I6fZQ7QIDGcw1s/QyvwVnu9G008ENu0Vp2YuxgebYNX4MdT3HTkIa')
INSERT into app_user(username, email, password) VALUES ('flame', 'flame@example.com', '$2y$12$I6fZQ7QIDGcw1s/QyvwVnu9G008ENu0Vp2YuxgebYNX4MdT3HTkIa')
INSERT into app_user(username, email, password) VALUES ('bolton', 'bolton@example.com', '$2y$12$I6fZQ7QIDGcw1s/QyvwVnu9G008ENu0Vp2YuxgebYNX4MdT3HTkIa')
INSERT into app_user(username, email, password) VALUES ('arme', 'arme@example.com', '$2y$12$I6fZQ7QIDGcw1s/QyvwVnu9G008ENu0Vp2YuxgebYNX4MdT3HTkIa')

INSERT into role(name) VALUES ('ROLE_ADMIN')
INSERT into role(name) VALUES ('ROLE_PLANNER')
INSERT into role(name) VALUES ('ROLE_WORKSHOPWORKER')

INSERT into user_role(username, role_id) VALUES ('chip', 1)
INSERT into user_role(username, role_id) VALUES ('harry', 2)
INSERT into user_role(username, role_id) VALUES ('phase', 3)
INSERT into user_role(username, role_id) VALUES ('flame', 1)
INSERT into user_role(username, role_id) VALUES ('bolton', 2)

INSERT into user_machine(username, machine_id) VALUES ('chip', 1)
INSERT into user_machine(username, machine_id) VALUES ('chip', 2)
INSERT into user_machine(username, machine_id) VALUES ('harry', 2)
INSERT into user_machine(username, machine_id) VALUES ('phase', 3)
INSERT into user_machine(username, machine_id) VALUES ('flame', 4)
INSERT into user_machine(username, machine_id) VALUES ('bolton', 5)


INSERT into planning(date) VALUES ('2020-02-01')
INSERT into planning(date) VALUES ('2020-02-02')
INSERT into planning(date) VALUES ('2020-02-03')
INSERT into planning(date) VALUES ('2020-02-04')

INSERT into order_product(order_number, drawing_number) VALUES (1, 'a5'), (1, 'a4')
INSERT into order_product(order_number, drawing_number) VALUES (2, 'a2')
INSERT into order_product(order_number, drawing_number) VALUES (3, 'a3')

