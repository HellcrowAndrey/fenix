create table public.addresses
(
    id int8 generated by default as identity,
    city varchar(128) not null,
    country varchar(128) not null,
    create_at timestamp not null,
    flat_number varchar(50),
    region varchar(128),
    status varchar(255),
    street varchar(128) not null,
    street_number varchar(128) not null,
    update_at timestamp not null,
    zip_code varchar(255) not null,
    primary key (id)
);

create table public.companies
(
    id int8 generated by default as identity,
    create_at timestamp not null,
    name varchar(100) not null,
    status varchar(255) not null,
    update_at timestamp not null,
    primary key (id)
);

create table public.customer_last_delivery
(
    id int8 generated by default as identity,
    company_name varchar(100) not null,
    create_at timestamp not null,
    status varchar(255),
    delivery_type varchar(255) not null,
    update_at timestamp not null,
    user_id uuid not null,
    address_id int8,
    primary key (id)
);

create table public.novaposhta_settings
(
    id int8 generated by default as identity,
    api_key varchar(255) not null,
    base_url varchar(255) not null,
    create_at timestamp not null,
    status varchar(255) not null,
    update_at timestamp not null,
    primary key (id)
);

create table public.novaposta_internet_document
(
    id int8 generated by default as identity,
    cost_on_size int4 not null,
    create_at timestamp not null,
    estimated_delivery_date varchar(255) not null,
    int_doc_number varchar(255) not null,
    order_id int8 not null,
    ref varchar(255) not null,
    status varchar(255) not null,
    type_document varchar(255) not null,
    update_at timestamp not null,
    primary key (id)
);

create table public.novaposta_legal_conterparty
(
    id int8 generated by default as identity,
    counterparty varchar(255) not null,
    counterparty_type varchar(255) not null,
    create_at timestamp not null,
    description varchar(255) not null,
    edrpou varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    middle_name varchar(255) not null,
    ownership_form varchar(255) not null,
    ref varchar(255) not null,
    status varchar(255) not null,
    update_at timestamp not null,
    primary key (id)
);

create index customer_last_delivery_user_id_idx on public.customer_last_delivery (user_id);

alter table if exists public.customer_last_delivery drop constraint if exists uk_customer_last_delivery_user_id;

alter table if exists public.customer_last_delivery add constraint uk_customer_last_delivery_user_id unique (user_id);

create table novaposhta_settings_headers (novaposhta_settings_id int8 not null, headers varchar(255), headers_key varchar(255) not null, primary key (novaposhta_settings_id, headers_key));

alter table if exists public.customer_last_delivery add constraint customer_address_fk foreign key (address_id) references public.addresses;

alter table if exists novaposhta_settings_headers add constraint novaposhta_settings_headers_fk foreign key (novaposhta_settings_id) references public.novaposhta_settings;