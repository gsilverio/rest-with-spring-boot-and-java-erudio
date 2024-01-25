CREATE TABLE IF NOT EXISTS permission (
                                        id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
                                        description varchar(255) DEFAULT NULL,
                                        PRIMARY KEY (id)
);