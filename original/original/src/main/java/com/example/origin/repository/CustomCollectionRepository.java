package com.example.origin.repository;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.origin.entity.Collection;

@Repository
public class CustomCollectionRepository {

	private final NamedParameterJdbcTemplate template;

    public CustomCollectionRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private final RowMapper<Collection> COLLECTION_ROW_MAPPER = (rs, i) -> {
        Collection collection = new Collection();
        collection.setId(rs.getInt("id"));
        collection.setName(rs.getString("name"));
        return collection;
    };

    public List<Collection> findAllCollections(String sortFlag) {
        StringBuilder sql = new StringBuilder();

        if ("1".equals(sortFlag)) {
            sql.append("SELECT id, name FROM collection ORDER BY created_at DESC");
        } else if ("2".equals(sortFlag)) {
            sql.append("SELECT id, name FROM collection ORDER BY name ASC");
        }

        return template.query(sql.toString(), COLLECTION_ROW_MAPPER);
    }

}
