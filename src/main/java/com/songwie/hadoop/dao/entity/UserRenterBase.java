package com.songwie.hadoop.dao.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the user_renter_base database table.
 * 
 */
@Entity
@Table(name="user_renter_base")
public class UserRenterBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private Integer id;

	@Column(length=5)
	private String active;

	private Integer age;

	@Column(length=64)
	private String career;

	@Column(length=16)
	private String cellphone;

	private Integer city;

	@Column(length=16)
	private String constellation;

	@Temporal(TemporalType.DATE)
	private Date createTime;

	@Column(length=16)
	private String educational;

	@Column(length=64)
	private String hobby;

	@Column(length=32)
	private String idCard;

	@Column(length=16)
	private String ip;

	@Column(length=1)
	private String isCharge;

	@Temporal(TemporalType.DATE)
	private Date lastLoginTime;

	@Column(length=16)
	private String nationality;

	private Integer province;

	@Column(nullable=false)
	private Integer regChannel;

	@Column(nullable=false)
	private Integer regChannelDtl;

	@Temporal(TemporalType.DATE)
	private Date registTime;

	private Integer sex;

	private Integer userId;

	public UserRenterBase() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCareer() {
		return this.career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Integer getCity() {
		return this.city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public String getConstellation() {
		return this.constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEducational() {
		return this.educational;
	}

	public void setEducational(String educational) {
		this.educational = educational;
	}

	public String getHobby() {
		return this.hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIsCharge() {
		return this.isCharge;
	}

	public void setIsCharge(String isCharge) {
		this.isCharge = isCharge;
	}

	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Integer getProvince() {
		return this.province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getRegChannel() {
		return this.regChannel;
	}

	public void setRegChannel(Integer regChannel) {
		this.regChannel = regChannel;
	}

	public Integer getRegChannelDtl() {
		return this.regChannelDtl;
	}

	public void setRegChannelDtl(Integer regChannelDtl) {
		this.regChannelDtl = regChannelDtl;
	}

	public Date getRegistTime() {
		return this.registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserRenterBase [id=" + id + ", active=" + active + ", age=" + age + ", career=" + career
				+ ", cellphone=" + cellphone + ", city=" + city + ", constellation=" + constellation + ", createTime="
				+ createTime + ", educational=" + educational + ", hobby=" + hobby + ", idCard=" + idCard + ", ip=" + ip
				+ ", isCharge=" + isCharge + ", lastLoginTime=" + lastLoginTime + ", nationality=" + nationality
				+ ", province=" + province + ", regChannel=" + regChannel + ", regChannelDtl=" + regChannelDtl
				+ ", registTime=" + registTime + ", sex=" + sex + ", userId=" + userId + "]";
	}

}