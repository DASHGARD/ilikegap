package com.gapprint.ilikegap.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Cart.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CART = "my_cart";
    private static final String CART_ID = "_id";
    private static final String PRODUCT_IMAGE = "produk_image";
    private static final String CART_PRICE = "cart_price";
    private static final String CART_TITLE = "cart_title";
    private static final String CART_QTY = "cart_qty";
    private static final String CART_SELECTED = "cart_selected";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_CART +
//                        " (" + CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " (" + CART_ID + " TEXT UNIQUE PRIMARY KEY, " +
                PRODUCT_IMAGE + " TEXT, " +
                CART_PRICE + " TEXT, " +
                CART_TITLE + " TEXT, " +
                CART_QTY + " TEXT, " +
                CART_SELECTED + " TEXT);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }

    public void addCart(String id, String productimage, String price, String title, String qty,String cart_selected){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CART_ID, id);
        cv.put(PRODUCT_IMAGE, productimage);
        cv.put(CART_PRICE, price);
        cv.put(CART_TITLE, title);
        cv.put(CART_QTY, qty);
        cv.put(CART_SELECTED, cart_selected);
        long result = db.insert(TABLE_CART,null, cv);
        if(result == -1){
//            Toast.makeText(context, "Failed added", Toast.LENGTH_SHORT).show();
//            updateData2(id,productimage, price, title,qty);
        }else {
//            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_CART;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateData(String row_id,String qty){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
//        cv.put(PRODUCT_IMAGE, image);
//        cv.put(CART_PRICE, price);
//        cv.put(CART_TITLE, title);
        cv.put(CART_QTY, qty);

        long result = db.update(TABLE_CART, cv, "_id=?", new String[]{row_id});
        if(result == -1){
//            Toast.makeText(context, "Failed update", Toast.LENGTH_SHORT).show();
        }else {
//            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }
    public void updateData2(String row_id,String selected){
        SQLiteDatabase dbs = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
//        cv.put(PRODUCT_IMAGE, image);
//        cv.put(CART_PRICE, price);
//        cv.put(CART_TITLE, title);
        cv.put(CART_SELECTED, selected);

        long result = dbs.update(TABLE_CART, cv,"_id=?", new String[]{row_id});

        if(result == -1){
//            Toast.makeText(context, "Failed update", Toast.LENGTH_SHORT).show();
        }else {
//            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }
//

    public void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_CART, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CART);
    }

}
