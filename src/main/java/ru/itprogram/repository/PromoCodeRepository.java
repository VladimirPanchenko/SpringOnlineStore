package ru.itprogram.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dao.PromoCode;
import ru.itprogram.utils.*;
import ru.itprogram.utils.generater.ArrayListGenerate;
import ru.itprogram.utils.generater.CurrentConnectionGenerate;
import ru.itprogram.utils.generater.dao.PromoCodeGenerate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PromoCodeRepository implements Repository<PromoCode> {
    @Autowired
    private ArrayListGenerate arrayListGenerate;
    @Autowired
    private CurrentConnectionGenerate currentConnectionGenerate;
    @Autowired
    private PromoCodeGenerate promoCodeGenerate;

    @Override
    public List<PromoCode> getAllEntity() {
        List<PromoCode> promoCodes = arrayListGenerate.getArrayList();
        CurrentConnection currentConnection = currentConnectionGenerate.getCurrentConnection();
        try {
            Statement statement = currentConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SelectSql.SELECT_ALL_PROMO_CODE);
            while (resultSet.next()) {
                PromoCode promoCode = promoCodeGenerate.getPromoCode();
                promoCode.setId(resultSet.getInt("id"));
                promoCode.setCode(resultSet.getString("code"));
                promoCode.setDiscount(resultSet.getShort("discount"));
                promoCodes.add(promoCode);
            }
            resultSet.close();
            statement.close();
            currentConnection.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promoCodes;
    }

    @Override
    public void saveEntity(PromoCode promoCode) {
        CurrentConnection currentConnection = currentConnectionGenerate.getCurrentConnection();
        try {
            PreparedStatement preparedStatement = currentConnection.getConnection()
                    .prepareStatement(InsertSql.INSERT_PROMO_CODE);
            preparedStatement.setString(1, promoCode.getCode());
            preparedStatement.setShort(2, promoCode.getDiscount());
            preparedStatement.execute();
            preparedStatement.close();
            currentConnection.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
