package com.example.techquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "login.db";
    public static final String TableUserName = "users";
    public static final String TableQuizScore = "quiz_score";
    public DBHelper(Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users" +
                "(id_user INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT  NOT NULL," +
                "password TEXT NOT NULL," +
                "score INTEGER DEFAULT 0)");

        db.execSQL("CREATE TABLE quiz_score" +
                "(username TEXT PRIMARY KEY NOT NULL," +
                "js_score INTEGER DEFAULT 0," +
                "java_score INTEGER DEFAULT 0," +
                "css_score INTEGER DEFAULT 0," +
                "html_score INTEGER DEFAULT 0," +
                "sql_score INTEGER DEFAULT 0," +
                "c_score INTEGER DEFAULT 0," +
                "cpp_score INTEGER DEFAULT 0," +
                "dotnet_score INTEGER DEFAULT 0," +
                "python_score INTEGER DEFAULT 0," +
                "php_score INTEGER DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
    }

    public Boolean insertData(String username, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        //insert to users table
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        Long result1 = db.insert("users", null, values);
        //insert to user quiz_score
        values.clear();
        values.put("username",username);
        long result2 = db.insert("quiz_score",null,values);
        if (result1 == -1 || result2 == -1)
            return false;
        else
            return true;
    }

    public User selectUser(String usernameSlc){
        User user = new User();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM users WHERE username = '"+usernameSlc+"'", null);
        if (c.moveToFirst()){
            do {
                // Passing values
                user.setUserId(c.getInt(0));
                user.setUsername(c.getString(1));
                user.setPassword(c.getString(2));
                user.setScore(c.getInt(3));
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return user;
    };

    public ArrayList<User> selectAllUsersList(){
        ArrayList<User> allUsers = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM users ORDER BY score DESC", null);
        if(c.moveToFirst()){
            do{
                User user= new User();
                // Passing values
                user.setUserId(c.getInt(0));
                user.setUsername(c.getString(1));
                user.setPassword(null);
                user.setScore(c.getInt(3));
                // Adding user to the list
                allUsers.add(user);
            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return allUsers;
    }


    public Boolean checkusername (String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    public Boolean checkusernamepassword (String username , String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", new String[]{username,password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    public boolean insertScore (String userName, String quizCode, int quizScore){
        SQLiteDatabase dbw = this.getWritableDatabase();
        SQLiteDatabase dbr = this.getReadableDatabase();
        String quizCodeLanguage = "none";
        switch (quizCode){
            case "JavaScript":
                quizCodeLanguage = "js_score";
                break;
            case "Java":
                quizCodeLanguage = "java_score";
                break;
            case "C":
                quizCodeLanguage = "c_score";
                break;
            case "C++":
                quizCodeLanguage = "cpp_score";
                break;
            case "Python":
                quizCodeLanguage = "python_score";
                break;
            case "CSS":
                quizCodeLanguage = "css_score";
                break;
            case "HTML":
                quizCodeLanguage = "html_score";
                break;
            case "PHP":
                quizCodeLanguage = "php_score";
                break;
            case "SQL":
                quizCodeLanguage = "sql_score";
                break;
            case ".Net":
                quizCodeLanguage = "dotnet_score";
                break;
        }
        dbw.execSQL("UPDATE quiz_score SET'" +quizCodeLanguage+ "'='" +quizScore+ "' WHERE username=?",new String[]{userName});
        Cursor c = dbr.rawQuery("SELECT js_score + java_score + c_score + cpp_score + python_score + css_score + html_score + php_score + sql_score + dotnet_score FROM quiz_score WHERE username='"+userName+"'", null);
        if(c.moveToFirst()){
            int scoreTotale = c.getInt(0);
            dbw.execSQL("UPDATE users SET score = '" +scoreTotale+ "' WHERE username=?",new String[]{userName});
        }

        c.close();
        dbr.close();
        dbw.close();
        return true;
    }

}
