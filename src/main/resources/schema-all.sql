if exists (select * from sysobjects where name='docket' and xtype='U')
	drop table docket;
;

if exists (select * from sysobjects where name='company' and xtype='U')
	drop table company;
;

if exists (select * from sysobjects where name='docket_ric' and xtype='U')
	drop table docket_ric;
;

create table company (
    company_cod bigint identity not null,
    ceo varchar(255), 
    name varchar(255), 
    phone bigint, 
    primary key (company_cod)
);

create table docket_ric (
    docket_ric_cod bigint identity not null,
    is_enabled bit not null, 
    name varchar(255), 
    primary key (docket_ric_cod)
);

create table docket (
    docket_cod bigint identity not null,
    x int, 
    case_type1 varchar(255), 
    case_type2 varchar(255), 
    date date, 
    docket_id varchar(255), 
    mkt_cap float, 
    oa_id varchar(255), 
    open_close_status varchar(255), 
    price_eod float, 
    seccode int, 
    sector int, 
    start_date date, 
    trade_date date, 
    company_company_cod bigint, 
    ric_docket_ric_cod bigint, 
    primary key (docket_cod)
);

alter table docket 
    add constraint FKmrq50afirvhiov1bdvlxiyo30 
    foreign key (company_company_cod) references company;
        
alter table docket 
    add constraint FK8g0316sjvp0lygjymsdj6url3 
    foreign key (ric_docket_ric_cod) references docket_ric;