package com.hesabbook.batch;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.hesabbook.entity.inventory.Inventory;
import com.hesabbook.entity.party.Partner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BatchUpdate {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void batchUpdate(List<Partner> entities) {
        try {
            String sql = "insert   " +
                    "        into  " +
                    "            partner  " +
                    "            (billing_address, company, creation_date_time, credit_limit, " +
                    "credit_period, credit_period_type, email, gst_number, loyality, mobile_number, " +
                    "opening_balance, opening_balance_type, p_name, party_category, party_type, shipping_address,primary_user_id,secondary_user_id)   " +
                    "        values  " +
                    "            (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    Partner entity = entities.get(i);
                    preparedStatement.setString(1, entity.getBillingAddress());
                    preparedStatement.setString(2, entity.getCompany());
                    preparedStatement.setDate(3, new Date(System.currentTimeMillis()));
                    preparedStatement.setString(4, entity.getCreditLimit());
                    preparedStatement.setString(5, entity.getCreditPeriod());
                    preparedStatement.setString(6, entity.getCreditPeriodType());
                    preparedStatement.setString(7, entity.getEmail());
                    preparedStatement.setString(8, entity.getGstNumber());
                    preparedStatement.setString(9, entity.getLoyality());
                    preparedStatement.setString(10, entity.getMobileNumber());
                    preparedStatement.setString(11, entity.getOpeningBalance());
                    preparedStatement.setString(12, entity.getOpeningBalanceType());
                    preparedStatement.setString(13, entity.getPName());
                    preparedStatement.setString(14, entity.getPartyCategory());
                    preparedStatement.setString(15, entity.getPartyType());
                    preparedStatement.setString(16, entity.getShippingAddress());
                    preparedStatement.setString(17, entity.getPrimary_user_id());
                    preparedStatement.setString(18, entity.getSecondary_user_id());
                }

                @Override
                public int getBatchSize() {
                    return entities.size();
                }
            });
        }catch (Exception exception){
            System.out.println("exception   "+exception);
        }
        System.out.println("DONE ");
    }

/*    public void batchUpdateItems(List<Inventory> entities) {
        String sql = "    insert " +
                "           into" +
                "                inventory" +
                "               (bar_code_value, batch_no, category, cgst, challan_no, company_name, compensation_cess," +
                " expire_date, gst_percentage, hsn, igst, " +
                "insertion_date, item, item_code, item_description, low_stock, " +
                "low_stock_check_box, mfg_date" +
                ", mrp, package_items, purchase_price, purchase_price_tax, rack_no, sale_price," +
                " sale_price_tax, salt, sgst, supplier, total_stock, unit_no, user_name, utgst, warehouse,creation_date_time,primary_user_id,secondary_user_id) " +
                "           values" +
                "               (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Inventory entity = entities.get(i);
                preparedStatement.setString(1, entity.getBarCodeValue());
                preparedStatement.setString(2, entity.getBatchNo());
                preparedStatement.setString(3, entity.getCategory());
                preparedStatement.setString(4, entity.getCgst());
                preparedStatement.setString(5, entity.getChallanNo());
                preparedStatement.setString(6, entity.getCompanyName());
                preparedStatement.setString(7, entity.getCompensationCess());
                preparedStatement.setString(8, entity.getExpireDate());
                preparedStatement.setString(9, entity.getGst());
                preparedStatement.setString(10, entity.getHsn());
                preparedStatement.setString(11, entity.getIgst());
                preparedStatement.setString(12, entity.getInsertionDate());
                preparedStatement.setString(13, entity.getItem());
                preparedStatement.setString(14, entity.getItemCode());
                preparedStatement.setString(15, entity.getItemDescription());
                preparedStatement.setString(16, entity.getLowStock());
                preparedStatement.setString(17, entity.getLowStockCheckBox());
                preparedStatement.setString(18, entity.getMfgDate());
                preparedStatement.setString(19, entity.getMrp());
                preparedStatement.setString(20, entity.getPackageItems());
                preparedStatement.setString(21, entity.getPurchasePrice());
                preparedStatement.setString(22, entity.getPurchasePriceTax());
                preparedStatement.setString(23, entity.getRackNo());
                preparedStatement.setString(24, entity.getSalePrice());
                preparedStatement.setString(25, entity.getSalePriceTax());
                preparedStatement.setString(26, entity.getSalt());
                preparedStatement.setString(27, entity.getSgst());
                preparedStatement.setString(28, entity.getSupplier());
                preparedStatement.setString(29, entity.getTotalStock());
                preparedStatement.setString(30, entity.getUnitNo());
                preparedStatement.setString(31, entity.getUserName());
                preparedStatement.setString(32, entity.getUtgst());
                preparedStatement.setString(33, entity.getWarehouse());
                preparedStatement.setDate(34, new Date(System.currentTimeMillis()));
                preparedStatement.setString(35, entity.getPrimary_user_id());
                preparedStatement.setString(36, entity.getSecondary_user_id());
            }

            @Override
            public int getBatchSize() {
                return entities.size();
            }
        });
    }*/
}
