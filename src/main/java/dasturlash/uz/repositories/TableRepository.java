package dasturlash.uz.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TableRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void createTables() {
        String sqlForProfile = "create table if not exists profile(" +
                "id serial primary key," +
                "name varchar(25) not null," +
                "surname varchar(25) not null," +
                "phone varchar(13) unique not null," +
                "password varchar(50) not null," +
                "created_at timeStamp default now()," +
                "visible boolean default true," +
                "status varchar(25) not null," +
                "role varchar(25) not null" +
                ")";


        String sqlForCard = "create table if not exists card(" +
                "id serial primary key," +
                "cardNumber varchar(16) unique not null," +
                "expired_date date not null," +
                "balance numeric not null," +
                "status varchar(20) not null," +
                "visible boolean default true," +
                "created_at timeStamp default now()" +
                ")";

        String sqlForTerminal = "create table if not exists terminal(" +
                "id serial primary key," +
                "code varchar unique not null," +
                "address varchar(25)," +
                "status varchar(25)," +
                "visible boolean not null," +
                "created_at timeStamp default now()" +
                ")";

        jdbcTemplate.execute(sqlForProfile);
        jdbcTemplate.execute(sqlForCard);
        jdbcTemplate.execute(sqlForTerminal);
    }
}
