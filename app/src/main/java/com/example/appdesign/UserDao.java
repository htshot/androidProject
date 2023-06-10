package com.example.appdesign;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert // 삽입
    void setInsertUser(User user);

    @Update // 갱신
    void setUpdateUser(User user);

    @Delete // 삭제
    void serDeleteUser(User user);

    @Query("SELECT * FROM User") //쿼리
    List<User> getUserAll();

    @Query("SELECT id FROM User ORDER BY id DESC LIMIT 1;")//
    User findByName();


    @Query("SELECT id FROM User ORDER BY id DESC LIMIT 1;")
    User findById();
}
