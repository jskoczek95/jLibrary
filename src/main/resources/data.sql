insert into user_role(role, description)
VALUES ('ROLE_USER', 'default role for user');
insert into user_role(role, description)
values ('ROLE_ADMIN', 'role for admin');
insert into user (email, first_name, last_name, password)
values ('admin@admin.pl', 'admin', 'admin', '{bcrypt}$2a$10$PG/5c58jyJ03AJKZDouS5uWQAp.Xq1fzZfXy2hQ1PdvTbkcLm/Lj.');
insert into user_roles(user_id, roles_id)
values ('1', '2');