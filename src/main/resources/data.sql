INSERT into client (company_name, email, debtor_number) VALUES('Profplastic', 'info@profplastic.com', 1234)

INSERT into orders(company_name, production_date, delivery_date) VALUES ( 'Profplastic', '2020-02-02', '2020-01-02')

INSERT into machine(machine) VALUES ('PORTAALFREES')

INSERT into product(drawing_number, operation_time) VALUES ('a1',60)

INSERT into app_user(username, email, password) VALUES ('chip', 'chip@example.com', '$2y$12$I6fZQ7QIDGcw1s/QyvwVnu9G008ENu0Vp2YuxgebYNX4MdT3HTkIa')
INSERT into app_user(username, email, password) VALUES ('harry','harry@example.com','$2y$12$I6fZQ7QIDGcw1s/QyvwVnu9G008ENu0Vp2YuxgebYNX4MdT3HTkIa')
INSERT into app_user(username, email, password) VALUES ('phase', 'phase@example.com', '$2y$12$I6fZQ7QIDGcw1s/QyvwVnu9G008ENu0Vp2YuxgebYNX4MdT3HTkIa')
INSERT into app_user(username, email, password) VALUES ('flame', 'flame@example.com', '$2y$12$I6fZQ7QIDGcw1s/QyvwVnu9G008ENu0Vp2YuxgebYNX4MdT3HTkIa')
INSERT into app_user(username, email, password) VALUES ('bolton', 'bolton@example.com', '$2y$12$I6fZQ7QIDGcw1s/QyvwVnu9G008ENu0Vp2YuxgebYNX4MdT3HTkIa')
INSERT into app_user(username, email, password) VALUES ('arme', 'arme@example.com', '$2y$12$I6fZQ7QIDGcw1s/QyvwVnu9G008ENu0Vp2YuxgebYNX4MdT3HTkIa')

INSERT into role(name) VALUES ('ROLE_ADMIN')
INSERT into role(name) VALUES ('ROLE_USER')
INSERT into role(name) VALUES ('ROLE_MACHINEPROGRAMMER')
INSERT into role(name) VALUES ('ROLE_PLANNER')
INSERT into role(name) VALUES ('ROLE_WORKSHOPWORKER')

INSERT into user_role(username, role_id) VALUES ('chip', 1)
INSERT into user_role(username, role_id) VALUES ('harry', 2)
INSERT into user_role(username, role_id) VALUES ('phase', 3)
INSERT into user_role(username, role_id) VALUES ('flame', 4)
INSERT into user_role(username, role_id) VALUES ('bolton', 5)

