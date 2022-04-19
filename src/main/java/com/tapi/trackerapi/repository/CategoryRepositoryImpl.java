package com.tapi.trackerapi.repository;

import com.tapi.trackerapi.domain.Category;
import com.tapi.trackerapi.exceptions.TBadRequestException;
import com.tapi.trackerapi.exceptions.TCategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository{

    private static final String SQL_CREATE = "INSERT INTO T_CATEGORIES (CATEGORY_ID, USER_ID, TITLE, DESCRIPTION) " +
            "VALUES(NEXTVAL('T_CATEGORIES_SEQ'), ?, ?, ?)";

    private static final String SQL_FIND_BY_ID = "SELECT C.CATEGORY_ID, C.USER_ID, C.TITLE, C.DESCRIPTION, COALESCE" +
            "(SUM(T.AMOUNT), 0) TOTAL_EXPENSE FROM T_TRANSACTIONS T RIGHT OUTER JOIN T_CATEGORIES C ON C.CATEGORY_ID" +
            " = T.CATEGORY_ID WHERE C.USER_ID = ? AND C.CATEGORY_ID = ? " +
            "GROUP BY C.CATEGORY_ID";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> findall(Integer userId) throws TCategoryNotFoundException {
        return null;
    }

    @Override
    public Category findById(Integer userId, Integer categoryId) throws TCategoryNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId, categoryId}, categoryRowMapper);
        } catch (Exception e) {
            throw new TCategoryNotFoundException("Category not found");
        }
    }

    @Override
    public Integer create(Integer userId, String title, String description) throws TBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setString(2, title);
                ps.setString(3, description);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("CATEGORY_ID");
        } catch (Exception e) {
            throw new TBadRequestException("Invalid Request");
        }
    }

    @Override
    public void update(Integer userId, Integer categoryId, Category category) throws TBadRequestException {

    }

    @Override
    public void deleteById(Integer userId, Integer categoryId) {

    }

    //  to map in rows & cols
    private RowMapper<Category> categoryRowMapper = (((rs, rowNum) -> {
        return new Category(
                rs.getInt("USER_ID"),
                rs.getInt("CATEGORY_ID"),
                rs.getString("TITLE"),
                rs.getString("DESCRIPTION"),
                rs.getDouble("TOTAL_EXPENSE")
        );
    }));

}
