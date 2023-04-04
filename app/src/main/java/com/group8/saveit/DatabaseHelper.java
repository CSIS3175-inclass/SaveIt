package com.group8.saveit;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.group8.saveit.FoodBundle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class DatabaseHelper extends SQLiteOpenHelper{

    final static String DATABASE_NAME = "SaveIt.db";
    final static int DATABASE_VERSION = 13;

    //Restaurant table
    final static String RESTAURANT = "Restaurant_table";
    final static String R_COL1 = "RID";
    final static String R_COL2 = "Name";
    final static String R_COL3 = "Start_Time";
    final static String R_COL4 = "End_Time";
    final static String R_COL5 = "Street_Num";
    final static String R_COL6 = "Street";
    final static String R_COL7 = "City";
    final static String R_COL8 = "Postal_Code";

    //FoodBundle Table
    final static String BUNDLES = "Bundles_table";
    final static String B_COL1 = "BID";
    final static String B_COL2 = "Price";
    final static String B_COL3 = "Items";
    final static String B_COL4 = "RID";

    //Customer table
    final static String USER = "User_table";
    final static String U_COL1 = "Email";
    final static String U_COL2 = "Name";
    final static String U_COL3 = "Password";
    final static String U_COL4 = "Phone_Num";
    final static String U_COL5 = "Street_Num";
    final static String U_COL6 = "Street";
    final static String U_COL7 = "City";
    final static String U_COL8 = "Postal_Code";

    //Manager table
    final static String MANAGER = "Manager_table";
    final static String M_COL1 = "Email";
    final static String M_COL2 = "Name";
    final static String M_COL3 = "Password";
    final static String M_COL4 = "Phone_Num";
    final static String M_COL5 = "RID";

    //Order table
    final static String ORDER = "Order_table";
    final static String O_COL1 = "OID";
    final static String O_COL2 = "OrderDate";
    final static String O_COL3 = "OrderStatus"; //true for completed, else false
    final static String O_COL4 = "RID"; // connects to restaurant table
    final static String O_COL5 = "Email"; //connects to customer table
    final static String O_COL6 = "Delivery_Option"; //1 for Delivery and 2 for Pick-up
    final static String O_COL7 = "Address";

    //Order_FoodBundle
    final static String ORDER_BUNDLE = "Order_Bundle_table";
    final static String OB_COL1 = "OID"; //composite key
    final static String OB_COL2 = "BID"; //composite key


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + RESTAURANT + " (" +
                R_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                R_COL2 + " TEXT, " +
                R_COL3 + " TIME, " +
                R_COL4 + " TIME, " +
                R_COL5 + " INTEGER, " +
                R_COL6 + " TEXT, " +
                R_COL7 + " TEXT, " +
                R_COL8 + " TEXT);";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE " + BUNDLES + " (" +
                B_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                B_COL2 + " FLOAT, " +
                B_COL3 + " TEXT, " +
                B_COL4 + " INTEGER, " +
                "FOREIGN KEY(" + B_COL4 + ") REFERENCES " + RESTAURANT + "(" + R_COL1 + ")" +
                ");";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE " + USER + " (" +
                U_COL1 + " TEXT PRIMARY KEY, " +
                U_COL2 + " TEXT, " +
                U_COL3 + " TEXT, " +
                U_COL4 + " INTEGER, " +
                U_COL5 + " INTEGER, " +
                U_COL6 + " TEXT, " +
                U_COL7 + " TEXT, " +
                U_COL8 + " TEXT" +
                ");";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE " + MANAGER + " (" +
                M_COL1 + " TEXT PRIMARY KEY," +
                M_COL2 + " TEXT," +
                M_COL3 + " TEXT," +
                M_COL4 + " TEXT," +
                M_COL5 + " INTEGER," +
                "FOREIGN KEY(" + M_COL5 + ") REFERENCES " + RESTAURANT + "(" + R_COL1 + ")" +
                ")";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE " + ORDER + " (" +
                O_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                O_COL2 + " DATE," +
                O_COL3 + " BOOLEAN," +
                O_COL4 + " INTEGER," +
                O_COL5 + " TEXT," +
                O_COL6 + " INTEGER," +
                O_COL7 + " TEXT," +
                "FOREIGN KEY("+O_COL4+") REFERENCES "+RESTAURANT+"("+R_COL1+")," +
                "FOREIGN KEY("+O_COL5+") REFERENCES "+USER+"("+U_COL1+")"
                + ")";

        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE " + ORDER_BUNDLE + "(" +
                OB_COL1 + " INTEGER, "+
                OB_COL2 + " INTEGER, "+
                "PRIMARY KEY("+OB_COL1+","+OB_COL2+"),"+
                "FOREIGN KEY("+OB_COL1+") REFERENCES "+ORDER+"("+O_COL1+"),"+
                "FOREIGN KEY("+OB_COL2+") REFERENCES "+BUNDLES+"("+B_COL1+"))";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RESTAURANT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BUNDLES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MANAGER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ORDER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ORDER_BUNDLE);
        onCreate(sqLiteDatabase);

    }

    public boolean addBundleData(Integer price, String items)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(B_COL2, price);
        String[] itemArray = items.split(" ");
        // Join the array of items into a comma-separated string
        String itemString = TextUtils.join(",", itemArray);
        values.put(B_COL3, itemString);

        long l = sqLiteDatabase.insert(BUNDLES, null, values);
        if(l > 0)
            return true;
        else
            return false;
    }


    public ArrayList<FoodBundle> getAllBundles() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + BUNDLES, null);
        ArrayList<FoodBundle> bundles = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int bundleId = cursor.getInt(cursor.getColumnIndex(B_COL1));
                @SuppressLint("Range") int price = cursor.getInt(cursor.getColumnIndex(B_COL2));
                @SuppressLint("Range") String items = cursor.getString(cursor.getColumnIndex(B_COL3));
//                FoodBundle bundle = new FoodBundle(bundleId, price, items);
                FoodBundle bundle = new FoodBundle(bundleId, items,price);
                bundles.add(bundle);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return bundles;
    }

    public void deleteBundle(int bundleId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BUNDLES, B_COL1 + "=?", new String[]{String.valueOf(bundleId)});

    }

    //Get foodbundle by id
    @SuppressLint("Range")
    public FoodBundle getFoodBundleById(int foodBundleId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+BUNDLES+" WHERE "+B_COL1+"=?",new String[]{Integer.toString(foodBundleId)});

        FoodBundle foodBundle = null;
        if(cursor.moveToFirst() && cursor.getColumnIndex(B_COL3)>-1 && cursor.getColumnIndex(B_COL1)>-1 && cursor.getColumnIndex(B_COL2)>-1){
            String items = cursor.getString(cursor.getColumnIndex(B_COL3));
            String[] itemList = items.split(",");
            foodBundle = new FoodBundle(cursor.getInt(cursor.getColumnIndex(B_COL1)),
                    cursor.getDouble(cursor.getColumnIndex(B_COL2)),
                    itemList);
        }
        cursor.close();
        return  foodBundle;

    }
    //Get FoodBundles of a specific restaurant
    @SuppressLint("Range")
    public ArrayList<FoodBundle> getFoodBundleByRestaurant(int restaurantId){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+BUNDLES+" WHERE "+B_COL4+"=?",new String[]{Integer.toString(restaurantId)});
        ArrayList<FoodBundle> foodBundles = new ArrayList<>();

        if(cursor.moveToFirst()){
            do {
                //make sure the index use is valid and the returned food bundle is not null
                if(cursor.getColumnIndex(B_COL1)>-1){
                    FoodBundle foodBundle= getFoodBundleById(cursor.getInt(cursor.getColumnIndex(B_COL1)));
                    if (foodBundle!=null)
                        foodBundles.add(foodBundle);
                }

            }while (cursor.moveToNext());
        }
        cursor.close();
        return foodBundles;
    }

    //add New Customer order
    public long addCustomerOrder(Date date,boolean isCompleted, int restaurantId, String customerEmail, int deliveryOption,String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(O_COL2, String.valueOf(date));
        contentValues.put(O_COL3, isCompleted);
        contentValues.put(O_COL4, restaurantId);
        contentValues.put(O_COL5, customerEmail);
        contentValues.put(O_COL6, deliveryOption);
        contentValues.put(O_COL7, address);

//        returns the added Order's ID if inserted successfully, else return negative number
        return db.insert(ORDER,null,contentValues);

    }

    //update customer order status
    public boolean updateOrderStatus(int orderId,boolean status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(O_COL3,status);

        // returns true if updated status successfuly else false
        return db.update(ORDER,contentValues,O_COL1+"=?",new String[]{Integer.toString(orderId)}) > 0;
    }

    //delete an order
    public boolean deleteOrder(int orderId){
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedOrder = db.delete(ORDER,O_COL1+"=?",new String[]{Integer.toString(orderId)});
        //attempt to delete record in ORDER TABLE FIRST, THEN ORDER_BUNDLE Table
        if(deletedOrder>0)
        {
            int deletedOrderBundle = db.delete(ORDER_BUNDLE,OB_COL1+"=?",new String[]{Integer.toString(orderId)});
            if(deletedOrder>0){
                return true;
            }
            else
                return false;
        }
        else
            return false;

    }
    //add foodbundle to order
    public boolean addBundleToOrder(int orderId,int bundleId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(OB_COL1,orderId);
        contentValues.put(OB_COL2,bundleId);
        //returns true when bundle is added successfully to the order
        return db.insert(ORDER_BUNDLE,null,contentValues)>0;
    }
    //get foodbundles by order
    @SuppressLint("Range")
    public ArrayList<FoodBundle> getFoodBundleByOrderId(int orderId){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ORDER_BUNDLE+" WHERE "+OB_COL1+"=?",new String[]{Integer.toString(orderId)});
        ArrayList<FoodBundle> foodBundles = new ArrayList<>();

        if(cursor.moveToFirst()){
            do {
                //make sure the index use is valid and the returned food bundle is not null
                if(cursor.getColumnIndex(OB_COL2)>-1){
                    FoodBundle foodBundle= getFoodBundleById(cursor.getInt(cursor.getColumnIndex(OB_COL2)));
                    if (foodBundle!=null)
                        foodBundles.add(foodBundle);
                }

            }while (cursor.moveToNext());
        }
        cursor.close();
        return foodBundles;
    }

    //Get All restaurants
    public ArrayList<Restaurant> getAllRestaurants(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+RESTAURANT,null);
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                int ridIndex = cursor.getColumnIndex(R_COL1);
                int nameIndex = cursor.getColumnIndex(R_COL2);
                int startIndex = cursor.getColumnIndex(R_COL3);
                int endIndex = cursor.getColumnIndex(R_COL4);
                int streetNumIndex = cursor.getColumnIndex(R_COL5);
                int streetNameIndex = cursor.getColumnIndex(R_COL6);
                int cityIndex = cursor.getColumnIndex(R_COL7);
                int postIndex = cursor.getColumnIndex(R_COL8);

                //make sure all indexes are valid
                if(ridIndex>-1&&nameIndex>-1&&startIndex>-1&&endIndex>-1&&streetNumIndex>-1&&streetNameIndex>-1&&cityIndex>-1&&postIndex>-1){
                    restaurants.add(new Restaurant(cursor.getInt(ridIndex),
                            cursor.getString(nameIndex),
                            cursor.getString(startIndex),
                            cursor.getString(endIndex),
                            cursor.getInt(streetNumIndex),
                            cursor.getString(streetNameIndex),
                            cursor.getString(cityIndex),
                            cursor.getString(postIndex)));
                }
            }while (cursor.moveToNext());
        }
        return restaurants;
    }

}
