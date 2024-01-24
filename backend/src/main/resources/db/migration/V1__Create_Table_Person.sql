CREATE TABLE person (
                               id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
                               first_name character varying(80) NOT NULL,
                               last_name character varying(80) NOT NULL,
                               address character varying(100) NOT NULL,
                               gender character varying(10) NOT NULL

);


