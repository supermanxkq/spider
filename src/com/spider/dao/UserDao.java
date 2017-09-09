package com.spider.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.spider.bean.User;

public class UserDao {

	Connection connection;

	public void clearTable() {
		try {
			connection = MysqlConn.getConn();
			String sql = "delete from user";
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.executeUpdate();
			// 关闭流
			prep.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int regeditUser(User user) {
		int count = 0;
		try {
			connection = MysqlConn.getConn();
			String sql = "insert into user(realName,email,repaymentMoney,idCard,telPhone,otherPayment,address,mobile,ovDays,coNa,ovAmount,coAddress)values(?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setObject(1, user.getRealName());
			prep.setObject(2, user.getEmail());
			prep.setObject(3, user.getRepaymentMoney());
			prep.setObject(4, user.getIdCard());
			prep.setObject(5, user.getTelPhone());
			prep.setObject(6, user.getOtherPayment());
			prep.setObject(7, user.getAddress());
			prep.setObject(8, user.getMobile());
			prep.setObject(9, user.getOvDays());
			prep.setObject(10, user.getCoNa());
			prep.setObject(11, user.getOvAmount());
			prep.setObject(12, user.getCoAddress());
			count = prep.executeUpdate();
			// 关闭流
			prep.close();
			connection.close();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;

	}

}
