package com.songwie.hadoop.dao.interfaces;

import java.io.Serializable;

import com.songwie.hadoop.dao.entity.UserRenterPortraitDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRenterPortraintDayDao extends JpaRepository<UserRenterPortraitDay, Serializable> {
	@Query(value="select * from user_renter_portrait_day where cellphone=:cellphone ",nativeQuery = true)
	abstract UserRenterPortraitDay getSimpleDateByCellphone(@Param("cellphone") String cellphone);
}
