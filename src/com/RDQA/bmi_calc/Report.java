package com.RDQA.bmi_calc;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Report extends Activity
{

	// 宣告Debug標籤Debug_bmi
	private static final String TAG_Bmi = "Debug_bmi";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);

		// 呼叫找出介面元件函式
		findViews();

		// 給定一個TAG_Bmi標籤，方便在showResults()涵式中Debug
		Log.d(TAG_Bmi, "Show Result");

		// 呼叫結果顯示函式
		showResults();

		// 等待按鈕被戳函式
		setListensers();

	}

	private Button button_back;
	private TextView view_result;
	private TextView view_suggest;

	// 找出介面元件函式
	private void findViews()
	{

		button_back = (Button) findViewById(R.id.report_back);
		view_result = (TextView) findViewById(R.id.result);
		view_suggest = (TextView) findViewById(R.id.suggest);

	}

	// "上一頁"按鈕
	private void setListensers()
	{

		button_back.setOnClickListener(backMain);

	}

	private Button.OnClickListener backMain = new Button.OnClickListener()
	{

		public void onClick(View v)
		{
			// 關閉report activity，返回Main Activity。
			Report.this.finish();
		}

	};

	// 計算BMI函式
	private void showResults()
	{

		try
		{

			// 格式化計算結果輸出位數
			DecimalFormat nFormat = new DecimalFormat("0.00");

			// 透過getExtras()函式取得附加在bundle上的物件
			Bundle bundle = this.getIntent().getExtras();

			// 身高公分轉公尺後，以浮點數型態存到height中
			double height = Double.parseDouble(bundle.getString("KEY_HEIGHT")) / 100;

			// 體重已福點數型態存到weight中
			double weight = Double.parseDouble(bundle.getString("KEY_WEIGHT"));

			// BMI=身高(m)/體重平方(kg)
			double BMI = weight / (height * height);

			// 將BMI計算結果丟到view_result這個TextView裡面
			view_result.setText(getText(R.string.bmi_result)
					+ nFormat.format(BMI));

			// 顯示健康提示
			if (BMI > 25)
			{
				// 將提示值丟到view_suggest這個TextView裡面
				view_suggest.setText(R.string.advice_heavy);
			} else if (BMI < 20)
			{
				view_suggest.setText(R.string.advice_light);
			} else
			{
				view_suggest.setText(R.string.advice_average);
			}

		} catch (Exception error)
		{

			//
			Log.e(TAG_Bmi, "Error" + error.toString());

			Toast.makeText(Report.this, R.string.error_msg, Toast.LENGTH_SHORT)
					.show();
			setListensers();

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
