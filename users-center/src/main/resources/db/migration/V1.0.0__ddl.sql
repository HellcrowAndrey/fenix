-- create schema if not exists public

create table public.confirm_token
(
    id int8 generated by default as identity,
    create_at timestamp not null,
    token varchar(255) not null,
    update_at timestamp not null,
    user_id uuid not null,
    primary key (id)
);

create table public.pass_reset_token
(
    id int8 generated by default as identity,
    create_at timestamp not null,
    expire_time timestamp,
    new_pass varchar(255),
    token varchar(255),
    update_at timestamp not null,
    user_id uuid not null,
    primary key (id)
);

create table public.refresh_session
(
    id int8 generated by default as identity,
    create_at timestamp not null,
    expire_in timestamp,
    fingerprint varchar(255) not null,
    ip varchar(255) not null,
    refresh_token text not null,
    update_at timestamp not null,
    user_id uuid not null,
    primary key (id)
);

create table public.roles
(
    id int8 generated by default as identity,
    create_at timestamp not null,
    role varchar(50) not null,
    update_at timestamp not null,
    primary key (id)
);

create table public.users
(
    id uuid not null,
    create_at timestamp not null,
    email varchar(150) not null,
    f_name varchar(150) not null,
    is_enable boolean default false not null,
    is_locked boolean default false not null,
    l_name varchar(150) not null,
    login varchar(255) not null,
    pass varchar(255) not null,
    phone varchar(150) not null,
    update_at timestamp not null,
    primary key (id)
);

create table public.users_alias
(
    id int8 generated by default as identity,
    alias varchar(255) not null,
    create_at timestamp not null,
    update_at timestamp not null,
    user_id uuid not null,
    primary key (id)
);

create index confirm_token_idx on public.confirm_token (token);

alter table if exists public.confirm_token drop constraint if exists uk_confirm_token;

alter table if exists public.confirm_token add constraint uk_confirm_token unique (token);

create index reset_token_idx on public.pass_reset_token (token);

alter table if exists public.pass_reset_token drop constraint if exists uk_pass_reset;

alter table if exists public.pass_reset_token add constraint uk_pass_reset unique (token);

create index email_idx on public.users (email);

create index login_idx on public.users (login);

alter table if exists public.users drop constraint if exists uk_users_login;

alter table if exists public.users add constraint uk_users_login unique (login);

alter table if exists public.users drop constraint if exists uk_users_email;

alter table if exists public.users add constraint uk_users_email unique (email);

alter table if exists public.users_alias drop constraint if exists uk_users_alias;

alter table if exists public.users_alias add constraint uk_users_alias unique (alias);

create table users_roles (user_id uuid not null, role_id int8 not null);

alter table if exists public.confirm_token add constraint confirm_token_users_fk foreign key (user_id) references public.users;

alter table if exists public.pass_reset_token add constraint pass_reset_token_users_fk foreign key (user_id) references public.users;

alter table if exists public.users_alias add constraint aliases_users_fk foreign key (user_id) references public.users;

alter table if exists users_roles add constraint roles_users_fk foreign key (role_id) references public.roles;

alter table if exists users_roles add constraint users_roles_fk foreign key (user_id) references public.users;
