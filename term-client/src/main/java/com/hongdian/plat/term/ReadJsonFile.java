/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.plat.term;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @date 2018-05-21 00:00:00
 * @Description:
 */
public class ReadJsonFile {

	public static void main(String[] args) throws Exception {
		String topic = "fep/10000/fep_itss_comm";
		String path = "F:\\swic-svn\\H7760\\src\\back-end\\term-platform\\src\\test\\java\\com\\hongdian\\plat\\term\\json\\";
		String filename = path + "devEvent.json";
		File file = new File(filename);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		InputStream is = new FileInputStream(filename);
		is.read(filecontent);
		is.close();
		System.out.println("end");
	}
}
