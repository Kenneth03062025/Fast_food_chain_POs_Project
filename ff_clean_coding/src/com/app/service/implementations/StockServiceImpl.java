package com.app.service.implementations;

import com.app.model.*;
import com.app.model.dto.ListOfStocksResponse;
import com.app.service.interfaces.StockService;
import com.app.util.DBConnection;
import com.app.util.RiskyFunctionAnyType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.app.util.FunctionWithTryCatch.tryCatchAnyResponseExecute;
import static com.app.util.FunctionWithTryCatch.tryCatchTransactionalExecute;

public class StockServiceImpl implements StockService {
    @Override
    public ListOfStocksResponse getStocksbyCashering(String casheringNumber) {
        String query = "SELECT stocks.id, stocks.cashering_no, stocks.item_no ,items.item_name," +
                "stocks.quantity, stocks.items_sold FROM stocks " +
                "INNER JOIN items ON stocks.item_no=items.item_no " +
                "WHERE stocks.cashering_no='" + casheringNumber + "'";
        DBConnection con = new DBConnection();
        ListOfStocksResponse resToConsole;

        RiskyFunctionAnyType func = () -> {

            List<Stocks> stocks = new ArrayList<>();
            Statement statement;
            ResultSet resultSet;
            statement = con.getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String cashering_no = resultSet.getString(2);
                String item_no = resultSet.getString(3);
                String item_name = resultSet.getString(4);
                int quantity = resultSet.getInt(5);
                int items_sold = resultSet.getInt(6);
                Stocks itemStock = new Stocks(id,cashering_no,item_no,item_name,quantity, items_sold);
//                String unit = resultSet.getString(6);
//                String status = resultSet.getString(7);
//                Item item = new Item(id, item_no, item_name, item_description, price, unit, status);
                stocks.add(itemStock);
            }
            Response<Stocks> res = new Response<>("success", "Successfully fetch Items",stocks);
            return res;
        };
        Response<?> res = tryCatchAnyResponseExecute(con,func);
        ListOfStocksResponse stocksRes = new ListOfStocksResponse();
        List<Stocks> stockItems = new ArrayList<>();
        stocksRes.setStatus(res.getStatus());
        stocksRes.setMessage(res.getMessage());
        if(res.getStatus().equals("success")){

            for (Object obj: res.getListOfItems()) {
                Stocks item = (Stocks) obj;
                stockItems.add(item);
            }
            stocksRes.setStocks(stockItems);
        }

        return stocksRes;
    }

    @Override
    public Response<Stocks> addItemsToCashering(List<Stocks> stocks) {
        String query = "INSERT INTO stocks (cashering_no,item_no, quantity)"
                + "VALUES(?,?,?)";

        DBConnection con = new DBConnection();

        RiskyFunctionAnyType func = () -> {
            Response<Stocks> res = new Response<>();

            PreparedStatement stmt1 = con.getConnection().prepareStatement(query);
            for ( Stocks ite: stocks) {
                stmt1.setString(1,ite.getCasheringNumber());
                stmt1.setString(2,ite.getItemNumber());
                stmt1.setInt(3,ite.getQuantity());
                stmt1.executeUpdate();
            }

            res.setStatus("success");
            res.setMessage("Successfully Saved Stocks to Cashering");
            return res;
        };
        return (Response<Stocks>) tryCatchTransactionalExecute(con, func);
//        return null;
    }
}
