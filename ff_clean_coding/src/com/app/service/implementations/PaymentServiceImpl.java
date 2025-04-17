package com.app.service.implementations;

import com.app.model.*;
import com.app.service.interfaces.PaymentService;
import com.app.util.DBConnection;
import com.app.util.RiskyFunctionAnyType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

import static com.app.util.FunctionWithTryCatch.tryCatchTransactionalExecute;

public class PaymentServiceImpl implements PaymentService {

    @Override
    public Response<?> createPayment(Payment payment) {
        String query1 = "Select identification_prefix,current_number FROM setup WHERE id=?";
        String query2 = "INSERT INTO payments (payment_number, order_number, total, createdAt, status)"
                + "VALUES(?,?,?,?,?)";
        String query3 = "UPDATE setup SET current_number = ? WHERE id=?";
        String query4 = "UPDATE orders SET status = 'paid' WHERE order_no=?";
        int setupId = 1;
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
            stmt2.setString(2,payment.getOrderNumber());
            stmt2.setDouble(3, payment.getTotal());
            stmt2.setString(4, LocalDateTime.now().toString());
            stmt2.setString(5,"paid");
//            stmt2.setString(6,"inactive");
            stmt2.executeUpdate();

            PreparedStatement stmt3 = con.getConnection().prepareStatement(query3);
            stmt3.setInt(1,setup.getNextCurrentNumber());
            stmt3.setInt(2,setupId);
            stmt3.executeUpdate();

            PreparedStatement stmt4 = con.getConnection().prepareStatement(query4);
            stmt4.setString(1, payment.getOrderNumber());


            res.setStatus("success");
            res.setMessage("Successfully Saved Payment");
            res.setData(setup.getNewNumber());
            return res;
        };
        return tryCatchTransactionalExecute(con, func);
        //return null;
    }
}
