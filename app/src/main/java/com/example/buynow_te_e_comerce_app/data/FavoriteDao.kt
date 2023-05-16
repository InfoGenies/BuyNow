package com.example.buynow_te_e_comerce_app.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertFavorite(favorite: FavorEntity)

    @Query("SELECT * FROM favorite_items order by id desc")
    fun GetFavorite(): LiveData<List<FavorEntity>>

    @Query("SELECT  EXISTS (SELECT 1 FROM favorite_items WHERE Product_id = :idPro)")
    fun GetFavoriteByID(idPro: String):LiveData<Boolean>

    @Delete
    suspend fun delete(favorite: FavorEntity)

    @Update
    suspend fun update(vararg favorite: FavorEntity)


}