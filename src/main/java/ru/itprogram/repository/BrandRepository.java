package ru.itprogram.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dao.Brand;
import ru.itprogram.utils.*;
import ru.itprogram.utils.generater.ArrayListGenerate;
import ru.itprogram.utils.generater.CurrentConnectionGenerate;
import ru.itprogram.utils.generater.dao.BrandGenerate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BrandRepository implements Repository<Brand> {
    @Autowired
    private ArrayListGenerate arrayListGenerate;
    @Autowired
    private CurrentConnectionGenerate currentConnectionGenerate;
    @Autowired
    private BrandGenerate brandGenerate;

    @Override
    public List<Brand> getAllEntity() {
        List<Brand> brands = arrayListGenerate.getArrayList();
        CurrentConnection currentConnection = currentConnectionGenerate.getCurrentConnection();
        Statement statement;
        try {
            statement = currentConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SelectSql.SELECT_ALL_BRAND);
            while (resultSet.next()) {
                Brand brand = brandGenerate.getBrand();
                brand.setId(resultSet.getInt("id"));
                brand.setName(resultSet.getString("name"));
                brands.add(brand);
            }
            resultSet.close();
            statement.close();
            currentConnection.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return brands;
    }

    @Override
    public void saveEntity(Brand brand) {
        CurrentConnection currentConnection = currentConnectionGenerate.getCurrentConnection();
        try {
            PreparedStatement preparedStatement = currentConnection.getConnection()
                    .prepareStatement(InsertSql.INSERT_BRAND);
            preparedStatement.setString(1, brand.getName());
            preparedStatement.execute();
            preparedStatement.close();
            currentConnection.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
