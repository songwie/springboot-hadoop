package com.mogoroom.hadoop.dao.interfaces;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mogoroom.hadoop.dao.entity.UserRenterPortrait;
import com.mogoroom.hadoop.dao.entity.UserRenterPortraitDay;


@Repository
public interface UserRenterPortraintDayDao extends JpaRepository<UserRenterPortraitDay, Serializable> {
	@Query(value="select * from user_renter_portrait_day where cellphone=:cellphone ",nativeQuery = true)
	abstract UserRenterPortraitDay getSimpleDateByCellphone(@Param("cellphone") String cellphone);
}
