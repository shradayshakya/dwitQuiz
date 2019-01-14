package services;



import domains.Category;
import utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    public String insertCategory(String name, String description) {
        String message = "";

        DatabaseConnection db = new DatabaseConnection();
        String sql = "INSERT INTO categories(name, description) VALUES (?,?);";
        try {
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.execute();
            message = "Category successfully registered";
        } catch (SQLException e) {
            message = e.getMessage();
        }
        return message;
    }


    public List<Category> getAllCategories() {
        DatabaseConnection db = new DatabaseConnection();
        String sql = "SELECT * FROM categories";
        List<Category> categories = new ArrayList<Category>();
        try {
            PreparedStatement ps = db.getPreparedStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                categories.add(category);
            }
            rs.close();
        } catch (SQLException e) {
            e.getMessage();
        }
        return categories;
    }
}