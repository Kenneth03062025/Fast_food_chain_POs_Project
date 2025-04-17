package com.app.service.implementations;

import com.app.model.Item;
import com.app.model.Response;
import com.app.model.Stocks;
import com.app.model.dto.ListOfItemsResponse;
import com.app.model.dto.ListOfStocksResponse;
import com.app.service.interfaces.StockService;
import com.app.util.DBConnection;
import com.app.util.RiskyFunctionAnyType;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.app.util.FunctionWithTryCatch.tryCatchAnyResponseExecute;

public class StockServiceImpl implements StockService {
    @Override
    public ListOfStocksResponse getStocksbyCashering(String casheringNumber) {
        String query = "SELECT * FROM stocks WHERE cashering_no='" + casheringNumber + "'";
        DBConnection con = new DBConnection();
        ListOfStocksResponse resToConsole;

        RiskyFunctionAnyType func = () -> {
            Response<Stocks> res = new Response<>();
            List<Stocks> stocks = new ArrayList<>();
            Statement statement;
            ResultSet resultSet;
            statement = con.getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String cashering_no = resultSet.getString(2);
                String item_no = resultSet.getString(3);
                int quantity = resultSet.getInt(4);
                int items_sold = resultSet.getInt(5);
                Stocks itemStock = new Stocks(id,cashering_no,item_no,quantity, items_sold);
//                String unit = resultSet.getString(6);
//                String status = resultSet.getString(7);
//                Item item = new Item(id, item_no, item_name, item_description, price, unit, status);
                stocks.add(itemStock);
            }

            return res = new Response<>("success", "Successfully fetch Items",stocks);
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
}
