package com.ilerna.mp8desarrollouf2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseManager extends SQLiteOpenHelper {

    public DatabaseManager(@Nullable Context context,
                           @Nullable String name,
                           @Nullable SQLiteDatabase.CursorFactory factory,
                           int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDatabaseQuery = "create table Users(\n" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "Name TEXT,\n" +
                "LastName TEXT,\n" +
                "Email TEXT,\n" +
                "Username TEXT UNIQUE,\n" +
                "Password TEXT \n" +
                ");";

        db.execSQL(createDatabaseQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void InsertUser(User user) {
        SQLiteDatabase writableDb = getWritableDatabase();
        String insertUser = "INSERT INTO Users (Name, LastName, Email, Username, Password) VALUES (\"" + user.getName()
                + "\",\"" + user.getLastName()
                + "\", \"" + user.getEmail()
                + "\", \"" + user.getUserName()
                + "\", \"" + user.getPassword() + "\" )";

        writableDb.execSQL(insertUser);
        writableDb.close();
    }

    public User GetUserByNick(String userName) {
        SQLiteDatabase readableDb = getWritableDatabase();
        String readUsers = "SELECT * FROM Users WHERE Username = \"" + userName + "\"" + " LIMIT 1";
        Cursor cursor = readableDb.rawQuery(readUsers, null);
        cursor.moveToFirst();
        User user;
        do {
            int Id = cursor.getInt(0);
            String name = cursor.getString(1);
            String lastName = cursor.getString(2);
            String email = cursor.getString(3);
            String username = cursor.getString(4);
            String password = cursor.getString(5);

            user = new User(Id, name, lastName, email, username, password);

        } while (cursor.moveToNext());
        readableDb.close();

        return user;
    }
}
