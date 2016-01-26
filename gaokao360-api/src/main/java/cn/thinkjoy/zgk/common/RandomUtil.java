package cn.thinkjoy.zgk.common;

import java.util.Random;

/**随机数工具
 * @author huangshengqing
 * 2015年9月24日
 */
public class RandomUtil {
	// 可以将字符转换赋值给int类型，查看其ASCII码
	public static void main(String[] args) {
		// 随机生成纯数字
//		for (int i = 0; i < 15; i++)
//			createData(20);
//		System.out.println("---------------");
		System.out.println("goo");
		// 生成数字字母
		for (int i = 0; i < 150; i++){
			System.out.println(createRandomCharNo0Data(10));
//			System.out.println(createRandomNumData(10));
		}
	}


	/**	 根据指定长度生成字母和数字的随机数<br/>
	 0~9的ASCII为48~57<br/>
	 A~Z的ASCII为65~90<br/>
	 a~z的ASCII为97~122
	 * @param length
	 * @return
	 */
	public static String createRandomCharData(int length) {
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();// 随机用以下三个随机生成器
		Random randdata = new Random();
		int data = 0;
		for (int i = 0; i < length; i++) {
			int index = rand.nextInt(3);
			// 目的是随机选择生成数字，大小写字母
			switch (index) {
			case 0:
				data = randdata.nextInt(10);// 仅仅会生成0~9
				sb.append(data);
				break;
			case 1:
				data = randdata.nextInt(26) + 65;// 保证只会产生65~90之间的整数
				sb.append((char) data);
				break;
			case 2:
				data = randdata.nextInt(26) + 97;// 保证只会产生97~122之间的整数
				sb.append((char) data);
				break;
			}
		}
		return sb.toString();
	}
	
	/**生成不含数字0的小写随机码
	 * @param length
	 * @return
	 */
	public static String createRandomCharNo0Data(int length) {
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();// 随机用以下三个随机生成器
		Random randdata = new Random();
		int data = 0;
		for (int i = 0; i < length; i++) {
			int index = rand.nextInt(2);
			// 目的是随机选择生成数字，大小写字母
			switch (index) {
			case 0:
				data = randdata.nextInt(10);// 仅仅会生成0~9
				while(data==0){
					data = randdata.nextInt(10);// 仅仅会生成0~9
				}
				sb.append(data);
				break;
//			case 1:
//				data = randdata.nextInt(26) + 65;// 保证只会产生65~90之间的整数
//				sb.append((char) data);
//				break;
			case 1:
				data = randdata.nextInt(26) + 97;// 保证只会产生97~122之间的整数
				sb.append((char) data);
				break;
			}
		}
		return sb.toString();
	}


	/**根据指定长度生成纯数字的随机数
	 * @param length
	 * @return
	 */
	public static String createRandomNumData(int length) {
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(rand.nextInt(10));
		}
		return sb.toString();
	}
	
}