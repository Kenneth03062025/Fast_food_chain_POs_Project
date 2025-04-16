package com.app.service.implementations;

import com.app.model.Item;
import com.app.model.Operation;
import com.app.model.Response;
import com.app.model.Setup;
import com.app.service.interfaces.OperationService;
import com.app.util.DBConnection;
import com.app.util.RiskyFunctionAnyType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

import static com.app.util.FunctionWithTryCatch.tryCatchAnyResponseExecute;
import static com.app.util.FunctionWithTryCatch.tryCatchTransactionalExecute;

public class OperationServiceImpl implements OperationService {

    @Override
    public Response<?> createOperation() {
        String query = "INSERT INTO operations(operationNumber, date)"
                + "VALUES(?,?)";
        String query2 = "Select identification_prefix,current_number FROM setup WHERE id=?";
        String query3 = "UPDATE setup SET current_number = ? WHERE id=?";
        int setupId = 5;
        DBConnection con = new DBConnection();

        RiskyFunctionAnyType func = () -> {
            Response<Item> res = new Response<>();
            Setup setup = null;

            PreparedStatement stmt1;
            ResultSet rs;
            stmt1 = con.getConnection().prepareStatement(query2);
            stmt1.setInt(1,setupId);
            rs = stmt1.executeQuery();

            while(rs.next()){
                String prefix = rs.getString(1);
                int currentNumber = rs.getInt(2);
                setup = new Setup(prefix,currentNumber);
            }

            PreparedStatement stmt2;
            stmt2 = con.getConnection().prepareStatement(query);
            stmt2.setString(1,setup.getNewNumber());
            stmt2.setString(2, LocalDateTime.now().toString());
            stmt2.executeUpdate();

            PreparedStatement stmt3 = con.getConnection().prepareStatement(query3);
            stmt3.setInt(1,setup.getNextCurrentNumber());
            stmt3.setInt(2,setupId);
            stmt3.executeUpdate();

            res.setStatus("success");
            res.setMessage("Successfully Opened Operation");

            return res;
        };
        return tryCatchTransactionalExecute(con, func);
        //return null;
    }

    @Override
    public Response<?> openCashering(String operationNumber) {
        String query = "UPDATE operations SET openAt = ? WHERE item_no=?";
        DBConnection con = new DBConnection();
        RiskyFunctionAnyType func = () -> {
            Response<?> res = new Response<>();
            PreparedStatement preparedStatement;
            preparedStatement = con.getConnection().prepareStatement(query);
            preparedStatement.setString(1,LocalDateTime.now().toString());
            preparedStatement.setString(2,operationNumber);
            preparedStatement.executeUpdate();
            return res = new Response<>("success","Successfully Opened Cashering");
        };

        return tryCatchAnyResponseExecute(con,func);
        //return null;
    }

    @Override
    public Response<?> closeCashering(String operationNumber) {
        String query = "UPDATE operations SET closeAt = ? WHERE item_no=?";
        DBConnection con = new DBConnection();
        RiskyFunctionAnyType func = () -> {
            Response<?> res = new Response<>();
            PreparedStatement preparedStatement;
            preparedStatement = con.getConnection().prepareStatement(query);
            preparedStatement.setString(1,LocalDateTime.now().toString());
            preparedStatement.setString(2,operationNumber);
            preparedStatement.executeUpdate();
            return res = new Response<>("success","Successfully Closed Cashering");
        };

        return tryCatchAnyResponseExecute(con,func);
        //return null;
    }
}
