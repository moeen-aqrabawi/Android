package net.aqraba.www.ICW;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBProvider extends ContentProvider {

    private static final String AUTHORITY = "net.aqraba.www.ICW.dbprovider";
    private static final String BASE_PATH = "prayertimes";

    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH );

    // Constant to identify the requested operation

    private static final int P_DATE = 1;
    private static final int P_ID = 2;

    private static final UriMatcher uriMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, P_DATE);
        uriMatcher.addURI(AUTHORITY, BASE_PATH +  "/#", P_ID);
    }


    public SQLiteDatabase database;

    public boolean onCreate() {
        DBOpenHelper helper = new DBOpenHelper(getContext());
        database = helper.getWritableDatabase();
        Toast toast = Toast.makeText(getContext(), "DB opened", Toast.LENGTH_SHORT);
        toast.show();
        return true;
    }

    public boolean close() {
        DBOpenHelper helper = new DBOpenHelper(getContext());
        database = helper.getWritableDatabase();
        Toast toast = Toast.makeText(getContext(), "DB closed", Toast.LENGTH_SHORT);
        toast.show();
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        if (uriMatcher.match(uri) == P_ID) {
            selection = DBOpenHelper.P_ID + "=" + uri.getLastPathSegment();
        }

        return database.query(DBOpenHelper.TABLE_PRAYER, DBOpenHelper.ALL_COLUMNS,
                selection, null, null, null,
                DBOpenHelper.P_ID);
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        int uriType = uriMatcher.match(uri);
        long id = 0;
        switch (uriType) {
            case P_DATE:
                id = database.insert(DBOpenHelper.TABLE_PRAYER,
                        null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return database.delete(DBOpenHelper.TABLE_PRAYER, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return database.update(DBOpenHelper.TABLE_PRAYER,
                values, selection, selectionArgs);
    }


    public boolean doesTableExist() {
        Cursor cursor = database.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + DBOpenHelper.TABLE_PRAYER + "'", null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    public void dropTable() {
        DialogInterface.OnClickListener dialogClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int button) {
                        if (button == DialogInterface.BUTTON_POSITIVE) {

                            //Insert Data management code here
                            database.delete(getType(CONTENT_URI), null, null);

                            Toast.makeText(getContext(),
                                    "All Deleted",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                };

    }

    public void CopyDB(InputStream inputStream, OutputStream outputStream) throws IOException {
        //---copy 1K bytes at a time---
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
            }
        inputStream.close();
        outputStream.close();
       }


    public void ToastRecord(Cursor cursor)
        {
            //Toast toast = Toast.makeText(this, "Reading Dara!!", Toast.LENGTH_SHORT);
            Toast toast = Toast.makeText(getContext(),
                        "id: "   + cursor.getString(0) + "\n" +
                        "Date: " + cursor.getString(1) + "\n" +
                        "Day : " + cursor.getString(2), Toast.LENGTH_SHORT);
                        toast.show();
        }

    public boolean tableExists(String tableName) {
        Toast toast = Toast.makeText(getContext(), "Searching table", Toast.LENGTH_SHORT);
        toast.show();

            if (tableName == null)
            {
                return false;
            }
            //Cursor cursor = database.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[] {"table", tableName});

        Cursor cursor = database.rawQuery("SELECT COUNT(*) FROM ?", new String[]{tableName});

        if (!cursor.moveToFirst())
            {
                return false;
            }
            int count = cursor.getInt(0);
            cursor.close();
            return count > 0;

    }

}
