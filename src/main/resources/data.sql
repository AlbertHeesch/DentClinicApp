SET NAMES UTF8;

insert into services (service_id, cost, service_description) values
                                                                 (1, 100, 'Follow up visit'),
                                                                 (2, 200, 'Tooth treatment per one'),
                                                                 (3, 400, 'Surgery tooth removal per one'),
                                                                 (4, 500, 'Teeth whitening');

insert into dentists (dentist_id, name, surname, EXPERIENCE) values
                                                                 (1, 'Andrzej', 'Bak', '1999-09-21'),
                                                                 (2, 'Izabela', 'Kloc', '1989-01-31'),
                                                                 (3, 'Sasza', 'Krawczenko', '2022-03-04');