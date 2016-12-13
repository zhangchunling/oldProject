package com.zrgk.manu.util;

import java.sql.Clob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

public class ClobTest {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws SerialException 
	 */
	public static void main(String[] args) throws SerialException, SQLException {
		Clob clob=new SerialClob("1231111111121212111".toCharArray());
		System.out.println(clob);
		String cstr=new ClobToString().ClobToString(clob); 
		System.out.println(cstr);
	}

}
