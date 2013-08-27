package com.RDQA.bmi_calc;

public class Test1
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		int a = 2;
		switch (a)
		{
		case 1:
			System.out.println("a=1");
			a = 111;
			break;
		case 2:
			System.out.println("a=2");
			a = 222;
			break;
		case 3:
			System.out.println("a=3");
			break;
		case 4:
			System.out.println("a=4");
			break;
		}
		System.out.println("Result:" + "a=" + a);
	}

}
