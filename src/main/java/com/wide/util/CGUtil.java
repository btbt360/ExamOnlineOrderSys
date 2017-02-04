package com.wide.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CGUtil {
	
	/**
	 * @author cg
	 * 
	 * */
	public static String createUUid(){
		UUID uuid = UUID.randomUUID();
		return uuid+"";
	}

	public static void main(String[] args){
		//System.out.println(createUUid());
		Double lastnum = 0.990;
		try {
			System.out.println(Double.parseDouble("002101010101"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @author cg
	 * @param lastnum 上级num
	 * @param maxnum 本级别最大num
	 * @return sst当前最大的sort
	 * */
	public static Double createSort(Double lastnum ,Double maxnum ){
		String sst ="";
		String maxstr = maxnum+"";
		String[] lastnums = {};
		if(lastnum!=0.0){
			lastnums = (lastnum+"").split("[.]");
		}
		if(maxnum<1){
			if(lastnum>0){
				if(lastnums.length>1){
					Double kint =Double.parseDouble(lastnums[1].replace("0","").equals("")?"0":lastnums[1].replace("0",""));
					if(kint>0){
						sst=lastnum+"01";
					}else{
						sst=lastnum+"1";
					}
				}else{
					sst=lastnum+"1";
				}
			}else{				
				sst="1";
			}
		}else{
			String[] maxs = maxstr.split("[.]");
			String mmsstr= maxs[1];
			if(maxs[1].length()%2!=0){
				mmsstr = maxs[1]+"0";
			}
			if(Double.parseDouble(mmsstr)>0){
				Double dwg=DoubleUtil.mul(Math.pow(10,Double.parseDouble(mmsstr.length()+"")),maxnum)+ 1;//现在最大的排序数
				dwg = DoubleUtil.div(dwg,Math.pow(10,Double.parseDouble(mmsstr.length()+"")),20);
				sst = dwg+"";
			}else{
				sst = (maxnum+1)+"";
			}
		}
 		return Double.parseDouble(sst);
	}
	
	/**
	 * @author cg
	 * @param lastnum 上级num
	 * @return 当前级别数
	 **/
	public static int createLevelNum(Double lastnum){
		String zstr = (lastnum+"").split("[.]")[0];
		String xstr = (lastnum+"").split("[.]")[1];
		xstr = xstr.replace("0", "");
		if(xstr.length()==0){
			int levelnum = zstr.length()+1;
			return levelnum;
		}else {
			int levelnum = zstr.length()+1+xstr.length();
			return levelnum;
		}
			
		
	} 
	/**
	 * @author cg
	 * @param str 需要截取的字符串
	 * @param sprit 标识符
	 * @return List<String>
	 * */
	public static List<String> cutOffString(String str,String sprit){
		String[] strs = str.split("["+sprit+"]");
		List<String> list = new ArrayList<String>();
		for(int i = 0;i<strs.length;i++){
			if(strs[i]!=null&&!strs[i].equals("")){
				list.add(strs[i]);
			}
		}
		return list;
	}

	/**
     * 对给定数目的自0开始步长为1的数字序列进行乱序
     * @param no 给定数目
     * @return 乱序后的数组
     */
    public static int[] getSequence(int no) {
        int[] sequence = new int[no];
        for(int i = 1; i < no+1; i++){
        	sequence[i-1] = i;
        }
        Random random = new Random();
        for(int i = 1; i < no+1; i++){
            int p = random.nextInt(no);
            while(p<0){
            	p = random.nextInt(no);
            }
            int tmp = sequence[i-1];
            sequence[i-1] = sequence[p];
            sequence[p] = tmp;
        }
        random = null;
        return sequence;
    }
    /**
     * 获取本地mac地址
     * @author cg
     * 
     * */
    public static String getLocalMac() throws SocketException {
		// TODO Auto-generated method stub
		//获取网卡，获取地址
    	String macstring = "";
		try {
			InetAddress ia = InetAddress.getLocalHost();
			byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
			System.out.println("mac数组长度："+mac.length);
			StringBuffer sb = new StringBuffer("");
			for(int i=0; i<mac.length; i++) {
				if(i!=0) {
					sb.append("-");
				}
				//字节转换为整数
				int temp = mac[i]&0xff;
				String str = Integer.toHexString(temp);
				System.out.println("每8位:"+str);
				if(str.length()==1) {
					sb.append("0"+str);
				}else {
					sb.append(str);
				}
			}
			System.out.println("本机MAC地址:"+sb.toString().toUpperCase());
			macstring = sb.toString().toUpperCase();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return macstring;
	}
    /**
     * 取得随机4位整数
     * 
     * */
    public static int getRandomInt() {
        int x;//定义两变量
        Random ne=new Random();//实例化一个random的对象ne
        x=ne.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999
        System.out.println("产生的随机数是:"+x);//输出
        return x;
    }
}
