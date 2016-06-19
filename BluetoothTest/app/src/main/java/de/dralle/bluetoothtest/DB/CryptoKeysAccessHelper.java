package de.dralle.bluetoothtest.DB;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by nils on 19.06.16.
 */
public class CryptoKeysAccessHelper {
    /**
     * Log tag. Used to identify this´ class log messages in log output
     */
    private static final String LOG_TAG = CryptoKeysAccessHelper.class.getName();
    /**
     * SQLite database connection
     */
    private SQLiteDatabase connection;

    public CryptoKeysAccessHelper(SQLiteDatabase connection) {
        this.connection = connection;
    }

    /**
     * Add a received message to the message history
     * @param senderId
     * @param message
     * @param userId
     * @param encrypted
     */
    public void addReceivedMessage(int senderId, String message, int userId,boolean encrypted) {
        Log.i(LOG_TAG, "Logging message " + message + " from " + senderId + " for " + userId);
        if (senderId > -1) {
            ContentValues cv = new ContentValues();
            cv.put("Text", message);
            cv.put("Timestamp", System.currentTimeMillis() / 1000);
            cv.put("Encrypted",encrypted);
            cv.put("UserID", userId);
            cv.put("DeviceID", senderId);
            connection.insert("Received", null, cv);
            Log.i(LOG_TAG, "Logged message " + message + " from " + senderId + " for " + userId);
        } else {
            Log.i(LOG_TAG, "Logging message " + message + " from " + senderId + " for " + userId + " failed");
        }

    }

    /**
     * Add a send message to history
     * @param receiverId
     * @param message
     * @param userId
     * @param encrypted
     */
    public void addSendMessage(int receiverId, String message, int userId,boolean encrypted) {
        Log.i(LOG_TAG, "Logging message " + message + " for " + receiverId + " from " + userId);
        if (receiverId > -1) {
            ContentValues cv = new ContentValues();
            cv.put("Text", message);
            cv.put("Timestamp", System.currentTimeMillis() / 1000);
            cv.put("Encrypted",encrypted);
            cv.put("UserID", userId);
            cv.put("DeviceID", receiverId);
            connection.insert("Send", null, cv);
            Log.i(LOG_TAG, "Logged message " + message + " for " + receiverId + " from " + userId);
        } else {
            Log.i(LOG_TAG, "Logging message " + message + " for " + receiverId + " from " + userId + " failed");
        }

    }

}
