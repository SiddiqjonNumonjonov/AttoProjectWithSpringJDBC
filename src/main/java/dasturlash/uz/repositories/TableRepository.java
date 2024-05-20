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

//        id,cardId,profileId,visible,created_date

        String sqlForProfileCard = "create table if not exists  profileCard(" +
                "id serial primary key," +
                "card_id int unique not null," +
                "profile_id int not null," +
                "visible boolean default true," +
                "created_at timeStamp default now()," +
                "constraint fk_card foreign key (card_id) references card(id), " +
                " constraint fk_profile foreign key (profile_id) references profile(id) " +
                ")";

        String sqlForTransaction = "create table if not exists  transactions(" +
                "id  serial primary key," +
                "card_id int not null," +
                "amount numeric(12,2)," +
                "terminal_id int," +
                "types varchar(20) not null," +
                "created_at timeStamp not null," +
                "foreign key(card_id) references card(id)," +
                "foreign key(terminal_id) references terminal(id)" +
                ")";

        jdbcTemplate.execute(sqlForProfile);
        jdbcTemplate.execute(sqlForCard);
        jdbcTemplate.execute(sqlForTerminal);
        jdbcTemplate.execute(sqlForProfileCard);
        jdbcTemplate.execute(sqlForTransaction);
    }
}
