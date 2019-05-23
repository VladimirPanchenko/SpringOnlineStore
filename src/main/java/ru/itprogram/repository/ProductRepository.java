package ru.itprogram.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dao.Product;
import ru.itprogram.utils.*;
import ru.itprogram.utils.generater.ArrayListGenerate;
import ru.itprogram.utils.generater.CurrentConnectionGenerate;
import ru.itprogram.utils.generater.dao.ProductGenerate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProductRepository implements Repository<Product> {
    @Autowired
    private ArrayListGenerate arrayListGenerate;
    @Autowired
    private CurrentConnectionGenerate currentConnectionGenerate;
    @Autowired
    private ProductGenerate productGenerate;

    @Override
    public List<Product> getAllEntity() {
        List<Product> products = arrayListGenerate.getArrayList();
        CurrentConnection currentConnection = currentConnectionGenerate.getCurrentConnection();
        try {
            Statement statement = currentConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SelectSql.SELECT_ALL_PRODUCT);
            while (resultSet.next()) {
                Product product = productGenerate.getProduct();
                product.setId(resultSet.getInt("id"));
                product.setBrandId(resultSet.getInt("brand_id"));
                product.setProductTypeId(resultSet.getInt("product_type_id"));
                product.setDescription(resultSet.getString("description"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setWarranty(resultSet.getShort("warranty"));
                product.setAvailable(resultSet.getBoolean("available"));
                product.setPrice(resultSet.getDouble("price"));
                product.setPromoCodId(resultSet.getInt("promo_cod_id"));
                products.add(product);
            }
            resultSet.close();
            statement.close();
            currentConnection.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void saveEntity(Product product) {
        CurrentConnection currentConnection = currentConnectionGenerate.getCurrentConnection();
        try {
            PreparedStatement preparedStatement = currentConnection.getConnection()
                    .prepareStatement(InsertSql.INSERT_PRODUCT);
            preparedStatement.setInt(1, product.getBrandId());
            preparedStatement.setInt(2, product.getProductTypeId());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setShort(5, product.getWarranty());
            preparedStatement.setBoolean(6, product.isAvailable());
            preparedStatement.setDouble(7, product.getPrice());
            preparedStatement.setInt(8, product.getPromoCodId());
            preparedStatement.execute();
            preparedStatement.close();
            currentConnection.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
