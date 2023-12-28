package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.entity.delivery.DeliveryInfo;
import com.grp08.capstoneprojectg08.entity.delivery.RushDeliveryInfo;
import com.grp08.capstoneprojectg08.util.DatabaseConnection;

import java.sql.*;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 * <br> Used for query on table deliveryInfo, rushDeliveryInfo
 */
public interface DeliveryRepo extends BaseRepo{

    public int saveDeliveryInfo(DeliveryInfo deliveryInfo);
    public DeliveryInfo findDeliveryInfoById(int deliveryInfoId);
}
