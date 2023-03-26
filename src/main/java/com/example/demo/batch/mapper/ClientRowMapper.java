package com.example.demo.batch.mapper;

import com.example.demo.model.Client;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component("clientRowMapper")
public class ClientRowMapper
        implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Client.builder()
                     .id(rs.getLong("SQ_CLIENT"))
                     .name(rs.getString("ST_NAME"))
                     .lastName(rs.getString("ST_LAST_NAME"))
                     .age(rs.getInt("NU_AGE"))
                     .email(rs.getString("ST_EMAIL"))
                     .build();
    }
}
