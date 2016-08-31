package com.riguz.EntityEngine;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.ofbiz.entity.Delegator;
import org.ofbiz.entity.DelegatorFactory;
import org.ofbiz.entity.GenericEntityException;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.entity.condition.EntityCondition;
import org.ofbiz.entity.jdbc.ConnectionFactory;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Delegator delegator = DelegatorFactory.getDelegator("default");
        System.out.println(".-");

        List<GenericValue> valueList = null;

        try {
            valueList = delegator.findList("PartyAndUser", EntityCondition.makeCondition("partyName", "TEST"), null, null, null, true);
            System.out.println(valueList.size());

            String groupHelperName = delegator.getGroupHelperName("org.ofbiz");
            Connection conn = ConnectionFactory.getConnection(groupHelperName);
            Statement stmt = conn.createStatement();
            String query = "select * from user";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println("->" + rs.getString(1));
            }
            valueList = delegator.findList("User", EntityCondition.makeCondition("userId", "00000001"), null, null, null, true);
            System.out.println(valueList.size());
        } catch (GenericEntityException | SQLException e) {
            e.printStackTrace();
        }

    }
}
