package com.app.service.implementations;

import com.app.model.OrderItem;
import com.app.model.Response;
import com.app.model.Setup;
import com.app.service.interfaces.OrderService;
import com.app.util.DBConnection;
import com.app.util.RiskyFunctionAnyType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.app.util.FunctionWithTryCatch.tryCatchTransactionalExecute;

public class OrderServiceImpl implements OrderService {
    @Override
    public Response<?> createOrder(ArrayList<OrderItem> orderItems) {
        String query1 = "Select identification_prefix,current_number FROM setup WHERE id=?";
        String query2 = "INSERT INTO orders (order_no, cashering_no, placeAt, status)"
                + "VALUES(?,?,?,?)";
        String query3 = "INSERT INTO order_items (order_no,item_no,quantity,item_total) VALUES(?,?,?,?)";
        String query4 = "UPDATE setup SET current_number = ? WHERE id=?";
        int setupId = 3;

        DBConnection con = new DBConnection();

        RiskyFunctionAnyType func = () -> {
            Response<String> res = new Response<>();
            Setup setup = null;
            PreparedStatement stmt1;
            ResultSet rs;
            stmt1 = con.getConnection().prepareStatement(query1);
            stmt1.setInt(1,setupId);
            rs = stmt1.executeQuery();

            while(rs.next()){
                String prefix = rs.getString(1);
                int currentNumber = rs.getInt(2);
                setup = new Setup(prefix,currentNumber);
            }

            PreparedStatement stmt2 = con.getConnection().prepareStatement(query2);
            stmt2.setString(1,setup.getNewNumber());
            stmt2.setString(2,"CSH-1001");
            stmt2.setString(3, LocalDateTime.now().toString());
            stmt2.setString(4,"created");
            stmt2.executeUpdate();

            PreparedStatement stmt3 = con.getConnection().prepareStatement(query3);
            for (OrderItem ite: orderItems) {
                stmt3.setString(1,setup.getNewNumber());
                stmt3.setString(2,ite.getItemNumber());
                stmt3.setInt(3,ite.getQuantity());
                stmt3.setDouble(4,ite.getQuantity()*ite.getPrice());
                stmt3.addBatch();
                stmt3.executeUpdate();
            }

            PreparedStatement stmt4 = con.getConnection().prepareStatement(query4);
            stmt4.setInt(1,setup.getNextCurrentNumber());
            stmt4.setInt(2,setupId);
            stmt4.executeUpdate();


            res.setStatus("success");
            res.setMessage("Successfully Saved Payment");
            res.setData(setup.getNewNumber());
            return res;
        };
        return tryCatchTransactionalExecute(con, func);
    }
}
