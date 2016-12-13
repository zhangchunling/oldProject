package com.zrgk.manu.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

public class ClobToString {
	 public static String ClobToString(Clob clob) {
	        String reString = "";
	        Reader is = null;
	        try {
	            is = clob.getCharacterStream();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        // 得到流
	        BufferedReader br = new BufferedReader(is);
	        String s = null;
	        try {
	            s = br.readLine();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        StringBuffer sb = new StringBuffer();
	        while (s != null) {
	            //执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
	            sb.append(s);
	            try {
	                s = br.readLine();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        reString = sb.toString();
	        return reString;
	    }
}

