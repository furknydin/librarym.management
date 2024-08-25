package com.frknydin.librarym.management.DAO.book;

import com.frknydin.librarym.management.model.entity.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book>  {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong(1));
        book.setAuthorId(rs.getLong(2));
        book.setIsbn(rs.getString(3));
        book.setPublisher(rs.getString(4));
        book.setTitle(rs.getString(5));
        
        return book;
    }
}
