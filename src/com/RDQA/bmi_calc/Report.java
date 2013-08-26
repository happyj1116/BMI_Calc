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

	// �ŧiDebug����Debug_bmi
	private static final String TAG_Bmi = "Debug_bmi";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);

		// �I�s��X��������禡
		findViews();

		// ���w�@��TAG_Bmi���ҡA��K�bshowResults()�[����Debug
		Log.d(TAG_Bmi, "Show Result");

		// �I�s���G��ܨ禡
		showResults();

		// ���ݫ��s�Q�W�禡
		setListensers();

	}

	private Button button_back;
	private TextView view_result;
	private TextView view_suggest;

	// ��X��������禡
	private void findViews()
	{

		button_back = (Button) findViewById(R.id.report_back);
		view_result = (TextView) findViewById(R.id.result);
		view_suggest = (TextView) findViewById(R.id.suggest);

	}

	// "�W�@��"���s
	private void setListensers()
	{

		button_back.setOnClickListener(backMain);

	}

	private Button.OnClickListener backMain = new Button.OnClickListener()
	{

		public void onClick(View v)
		{
			// ����report activity�A��^Main Activity�C
			Report.this.finish();
		}

	};

	// �p��BMI�禡
	private void showResults()
	{

		try
		{

			// �榡�ƭp�⵲�G��X���
			DecimalFormat nFormat = new DecimalFormat("0.00");

			// �z�LgetExtras()�禡���o���[�bbundle�W������
			Bundle bundle = this.getIntent().getExtras();

			// ���������ऽ�ث�A�H�B�I�ƫ��A�s��height��
			double height = Double.parseDouble(bundle.getString("KEY_HEIGHT")) / 100;

			// �魫�w���I�ƫ��A�s��weight��
			double weight = Double.parseDouble(bundle.getString("KEY_WEIGHT"));

			// BMI=����(m)/�魫����(kg)
			double BMI = weight / (height * height);

			// �NBMI�p�⵲�G���view_result�o��TextView�̭�
			view_result.setText(getText(R.string.bmi_result)
					+ nFormat.format(BMI));

			// ��ܰ��d����
			if (BMI > 25)
			{
				// �N���ܭȥ��view_suggest�o��TextView�̭�
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
