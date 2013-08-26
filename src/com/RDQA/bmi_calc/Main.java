package com.RDQA.bmi_calc;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
	
	
	private Button button_calculate;
	private EditText field_weight;
	private EditText field_height;
	private TextView view_result_feet;
	private TextView view_result_pound;
	private Button button_about;
	private Button button_bmi;
	
	
	//宣告Debug標籤Debug_calc
	private static final String TAG_Calc = "Debug_calc";
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//呼叫找出介面元件函式  
		findViews();
		
		//等待換算按鈕被戳函式
		CalcSetListeners();
		
		//等待BMI計算按鈕被戳函式
		BMISetListeners();
		
			
	}
	
	
	private void findViews(){
		
		button_calculate = (Button)findViewById(R.id.submit);
		field_weight = (EditText)findViewById(R.id.input_weight);
		field_height = (EditText)findViewById(R.id.input_height);
		view_result_feet = (TextView)findViewById(R.id.result_feet);
		view_result_pound = (TextView)findViewById(R.id.result_pound);
		button_about = (Button)findViewById(R.id.about);
		button_bmi = (Button)findViewById(R.id.calc);
		
	}
		
	
	//等待換算按鈕被戳函式
	private void CalcSetListeners(){
		
		//透過前面宣告的TAG_Calc標籤，方便在CalcSetListeners()涵式中Debug
		//d=debug 除錯資訊
		Log.d(TAG_Calc, "Calc Set Listeners");
		
		button_calculate.setOnClickListener(calc);
		button_about.setOnClickListener(about);
				
	}
	
	
	//等待BMI按鈕被戳函式
	private void BMISetListeners(){

		button_bmi.setOnClickListener(bmi);
		
	}
	
	
	
	
	
	
	
	
	//單位換算程式(公斤>磅，公分>英呎)
	private Button.OnClickListener calc = new Button.OnClickListener(){
		
		public void onClick(View V){
			
			
			//Try error
			try {
				
				DecimalFormat nFormat = new DecimalFormat("0.00");
				
				//公分轉英呎
				double feet = Double.parseDouble(field_height.getText().toString())/30.48;
				
				//公斤轉英磅
				double pound = Double.parseDouble(field_weight.getText().toString())*2.2;
				
				//將換算結果存到view_result_feet與view_result_pound這兩個TextView中。
				view_result_feet.setText(getText(R.string.result_height)+ nFormat.format(feet));
				view_result_pound.setText(getText(R.string.result_weight)+ nFormat.format(pound));
						
				
			} catch (Exception error) {
				
				//
				Log.e(TAG_Calc, "Error"+ error.toString());
				
				
				//錯誤處理，Toast函式。
				Toast.makeText(Main.this, R.string.error_msg, Toast.LENGTH_LONG).show();
			}
								
		}
		
	};
	
	
	
	//About按鈕函式
    private Button.OnClickListener about = new Button.OnClickListener()
    {
    	public void onClick(View V)
    	{
    		//呼叫Dialog對話函式
    		openAboutDialog();
    	}  	
    	
    };
	
    
    //Dialog對話框函式
    private void openAboutDialog()
    {
    	//宣告一個新的AlertDialog實體
    	AlertDialog.Builder dialog = new AlertDialog.Builder(Main.this);
    	
    	//對話框標題
    	dialog.setTitle(R.string.about_title);
    	
    	//對話框內文
    	dialog.setMessage(R.string.about_msg);
    	
    	
    	//OK按鈕 (PositiveButton)
    	dialog.setPositiveButton(R.string.ok_label,
    			
    			new DialogInterface.OnClickListener(){
    		
    				public void onClick(DialogInterface dialog, int which) {
    					
    				}
    					
									
				});
    	
    	//某網頁按鈕 (NeutralButton)
    	dialog.setNeutralButton(R.string.google_label,
    			
    			new DialogInterface.OnClickListener(){
    		
    				public void onClick(DialogInterface dialog, int which) {
    					
    					//建立一個uri(Universal Resource Identifier)實體，將google網址存入.
    					Uri uri = Uri.parse(getString(R.string.google_url));
    					
    					//建立一個intent實體,並傳入這個意圖的"動作"與"內容"
    					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
    					
    					//導入intent到startActivity()函式中
    					startActivity(intent);
    					
    				}
    					
									
				});
    	
    	
    	//聯絡作者按鈕 (NegativeButton)
    	dialog.setNegativeButton(R.string.contact_label,
        		new DialogInterface.OnClickListener() {
    				
    				public void onClick(DialogInterface dialog, int which) {
    					
    					//Intent
    					Uri uri = Uri.parse("tel:0926913951");
    					Intent intent = new Intent(Intent.ACTION_DIAL, uri);
    					startActivity(intent);
    				}
    			});
    	
    	//透過show()函式，將此對話框顯示出來。
    	dialog.show();
    }
    
    
    
    
    
    //BMI計算函式按鈕
    private Button.OnClickListener bmi = new Button.OnClickListener(){
    	
    	
    	public void onClick(View V){
    		
    		//切換activity，跳頁 switch to report page
    		//建立一個新的intent 實體
    		Intent intent = new Intent();
    		
    		//替這個intent指定來源的Activity所在calss,以及所前往的Activity 所在的class
    		intent.setClass(Main.this, Report.class);
    		
    		//將附加在intent上的資訊儲存在bundle實體上 
    		Bundle bundle = new Bundle();
    		
    		//從field_height欄位取得的字串，傳到bundle的KEY_HEIGHT
    		bundle.putString("KEY_HEIGHT", field_height.getText().toString());
    		
    		//從field_weight欄位取得的字串，傳到bundle的KEY_WEIGHT
    		bundle.putString("KEY_WEIGHT", field_weight.getText().toString());
    		
    		//透過putExtras()函式將bundle內容傳入intent這個實體
    		intent.putExtras(bundle);
    		
    		//導入intent到startActivity()函式中
    		startActivity(intent);
    		
    		
    	}
     	
    };
    	


    //建立選單
    
    //MENU_ABOUT為第一個item;MENU_QUIT為第二個item
    protected static final int MENU_ABOUT = Menu.FIRST;
    protected static final int MENU_QUIT = Menu.FIRST+1;
    
    
    //@Override複寫掉定義在Activity類別的函式 (public class Main extends Activity)
    @Override
    
    // onCreateOptionsMenu()為選單列主體，傳入一個Menu型別的menu參數。
    public boolean onCreateOptionsMenu(Menu menu){
    	
    	//參數1:群組id, 參數2:itemID, 參數3:順序, 參數4:title
    	menu.add(0, MENU_ABOUT, Menu.NONE, "關於");
    	menu.add(0, MENU_QUIT, Menu.NONE, "結束");
    	return super.onCreateOptionsMenu(menu);
    	
    }
    
    //處理選項動作
    //onOptionsItemSelected()為處理所有選項主體，傳入一個MenuItem型別的item參數。
    public boolean onOptionsItemSelected(MenuItem item){
    	
    	//透過getItemId()函式取得識別符號代碼
    	switch(item.getItemId()){
    	
    	//點選MENU_ABOUT，呼叫openAboutDialog()函式
    		case MENU_ABOUT:
    			openAboutDialog();
    			break;
    			
    	//點選MENU_ABOUT，呼叫finish()函式
    		case MENU_QUIT:
    			finish();
    			break;
    	
    	}
    	
    	return super.onOptionsItemSelected(item);
    	
    	
    }
       
    
    
    
}
