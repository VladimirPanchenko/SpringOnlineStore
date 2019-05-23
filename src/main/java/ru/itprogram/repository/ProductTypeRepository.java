package ru.itprogram.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dao.ProductType;
import ru.itprogram.utils.*;
import ru.itprogram.utils.generater.ArrayListGenerate;
import ru.itprogram.utils.generater.CurrentConnectionGenerate;
import ru.itprogram.utils.generater.dao.ProductTypeGenerate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProductTypeRepository implements Repository<ProductType> {
    @Autowired
    private ArrayListGenerate arrayListGenerate;
    @Autowired
    private CurrentConnectionGenerate currentConnectionGenerate;
    @Autowired
    private ProductTypeGenerate productTypeGenerate;

    @Override
    public List<ProductType> getAllEntity() {
        List<ProductType> productTypes = arrayListGenerate.getArrayList();
        CurrentConnection currentConnection = currentConnectionGenerate.getCurrentConnection();
        try {
            Statement statement = currentConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SelectSql.SELECT_ALL_PRODUCT_TYPE);
            while (resultSet.next()) {
                ProductType productType = productTypeGenerate.getProductType();
                productType.setId(resultSet.getInt("id"));
                productType.setType(resultSet.getString("type"));
                productTypes.add(productType);
            }
            resultSet.close();
            statement.close();
            currentConnection.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productTypes;
    }

    @Override
    public void saveEntity(ProductType productType) {
        CurrentConnection currentConnection = currentConnectionGenerate.getCurrentConnection();
        try {
            PreparedStatement preparedStatement = currentConnection.getConnection()
                    .prepareStatement(InsertSql.INSERT_PRODUCT_TYPE);
            preparedStatement.setString(1, productType.getType());
            preparedStatement.execute();
            preparedStatement.close();
            currentConnection.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
