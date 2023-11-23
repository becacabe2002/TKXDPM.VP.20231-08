package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.entity.delivery.DeliveryInfo;
import com.grp08.capstoneprojectg08.entity.delivery.RushDeliveryInfo;

import java.sql.SQLException;

// Used for query on table deliveryInfo, rushDeliveryInfo
public class DeliveryRepo extends BaseRepo{
    public int saveDeliveryInfo(DeliveryInfo deliveryInfo){
        int deliveryInfoId = -1;
        // return saved delivery info id
        String querySave = "insert into deliveryInfo(address, instructions, province, name) values(?, ?, ?, ?)";
        String querySaveRush = "insert into rushDeliveryInfo(id, shippingTime, rushDeliveryInstructions) values(?, ?, ?)";
        String queryGetLastId = "select id from deliveryInfo order by id desc limit 1";
        try{
            ppStatement = dbConnection.prepareStatement(querySave);
            ppStatement.setString(1, deliveryInfo.getAddress());
            ppStatement.setString(2, deliveryInfo.getInstructions());
            ppStatement.setString(3, deliveryInfo.getProvince());
            ppStatement.setString(4, deliveryInfo.getName());
            ppStatement.executeUpdate();

            if (deliveryInfo.getRushDeliveryInfo() != null){
                // get the newest inserted deliveryInfo id
                ppStatement = dbConnection.prepareStatement(queryGetLastId);
                resultSet = ppStatement.executeQuery();
                resultSet.next();
                deliveryInfoId = resultSet.getInt("id");

                // save rush delivery info
                ppStatement = dbConnection.prepareStatement(querySaveRush);
                ppStatement.setInt(1, deliveryInfoId);
                ppStatement.setDate(2, deliveryInfo.getRushDeliveryInfo().getShippingTime());
                ppStatement.setString(3, deliveryInfo.getRushDeliveryInfo().getRushDeliveryInstructions());
                ppStatement.executeUpdate();
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return deliveryInfoId;
    }
    public DeliveryInfo findDeliveryInfoById(int deliveryInfoId){
        String query = "select * from deliveryInfo left join rushDeliveryInfo on deliveryInfo.id = rushDeliveryInfo.id where deliveryInfo.id = ? limit 1";
        DeliveryInfo deliveryInfo = null;
        try{
            ppStatement = dbConnection.prepareStatement(query);
            ppStatement.setInt(1, deliveryInfoId);
            resultSet = ppStatement.executeQuery();
            while(resultSet.next()){
                deliveryInfo =  new DeliveryInfo(
                        resultSet.getInt("id"),
                        resultSet.getString("address"),
                        resultSet.getString("instructions"),
                        resultSet.getString("province"),
                        resultSet.getString("name")
                );
                if(resultSet.getDate("shippingTime") != null){
                    deliveryInfo.setRushDeliveryInfo(
                            new RushDeliveryInfo(
                                    resultSet.getDate("shippingTime"),
                                    resultSet.getString("rushDeliveryInstructions")
                            )
                    );
                }
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return deliveryInfo;
    }
}
