-- write sql to create member table
create table if not exists products (productid int auto_increment,
                                                        name varchar(30) not null,
                                                        brand varchar(30) not null,
                                                        description varchar(1000) not null,
                                                        createdon timestamp not null,
                                                        productcode varchar(14) not null,
                                                        primary key (productid));
