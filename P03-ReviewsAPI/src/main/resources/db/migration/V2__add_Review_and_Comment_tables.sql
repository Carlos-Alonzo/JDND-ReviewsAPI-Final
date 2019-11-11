-- write sql to create reviews  table
create table if not exists reviews (reviewid int auto_increment,
                                                        rating int not null,
                                                        title varchar(30) not null,
                                                        text varchar(100) not null,
                                                        createdon timestamp not null,
                                                        reviewer varchar(30)  not null,
                                                        productid int not null,
                                                       primary key (reviewid),
                                                      constraint foreign key (productid) references products (productid) on delete cascade on update cascade);

-- write sql to create comments table
create table if not exists comments (commentid int auto_increment,
                                                        title varchar(30) not null,
                                                        text varchar(30) not null,
                                                        createdon timestamp not null,
                                                        reviewid int  not null,
                                                       primary key (commentid),
                                                      constraint foreign key (reviewid) references reviews (reviewid) on delete cascade on update cascade);


