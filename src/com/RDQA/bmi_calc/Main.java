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
	
	
	//�ŧiDebug����Debug_calc
	private static final String TAG_Calc = "Debug_calc";
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//�I�s��X��������禡  
		findViews();
		
		//���ݴ�����s�Q�W�禡
		CalcSetListeners();
		
		//����BMI�p����s�Q�W�禡
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
		
	
	//���ݴ�����s�Q�W�禡
	private void CalcSetListeners(){
		
		//�z�L�e���ŧi��TAG_Calc���ҡA��K�bCalcSetListeners()�[����Debug
		//d=debug ������T
		Log.d(TAG_Calc, "Calc Set Listeners");
		
		button_calculate.setOnClickListener(calc);
		button_about.setOnClickListener(about);
				
	}
	
	
	//����BMI���s�Q�W�禡
	private void BMISetListeners(){

		button_bmi.setOnClickListener(bmi);
		
	}
	
	
	
	
	
	
	
	
	//��촫��{��(����>�S�A����>�^�`)
	private Button.OnClickListener calc = new Button.OnClickListener(){
		
		public void onClick(View V){
			
			
			//Try error
			try {
				
				DecimalFormat nFormat = new DecimalFormat("0.00");
				
				//������^�`
				double feet = Double.parseDouble(field_height.getText().toString())/30.48;
				
				//������^�S
				double pound = Double.parseDouble(field_weight.getText().toString())*2.2;
				
				//�N���⵲�G�s��view_result_feet�Pview_result_pound�o���TextView���C
				view_result_feet.setText(getText(R.string.result_height)+ nFormat.format(feet));
				view_result_pound.setText(getText(R.string.result_weight)+ nFormat.format(pound));
						
				
			} catch (Exception error) {
				
				//
				Log.e(TAG_Calc, "Error"+ error.toString());
				
				
				//���~�B�z�AToast�禡�C
				Toast.makeText(Main.this, R.string.error_msg, Toast.LENGTH_LONG).show();
			}
								
		}
		
	};
	
	
	
	//About���s�禡
    private Button.OnClickListener about = new Button.OnClickListener()
    {
    	public void onClick(View V)
    	{
    		//�I�sDialog��ܨ禡
    		openAboutDialog();
    	}  	
    	
    };
	
    
    //Dialog��ܮب禡
    private void openAboutDialog()
    {
    	//�ŧi�@�ӷs��AlertDialog����
    	AlertDialog.Builder dialog = new AlertDialog.Builder(Main.this);
    	
    	//��ܮؼ��D
    	dialog.setTitle(R.string.about_title);
    	
    	//��ܮؤ���
    	dialog.setMessage(R.string.about_msg);
    	
    	
    	//OK���s (PositiveButton)
    	dialog.setPositiveButton(R.string.ok_label,
    			
    			new DialogInterface.OnClickListener(){
    		
    				public void onClick(DialogInterface dialog, int which) {
    					
    				}
    					
									
				});
    	
    	//�Y�������s (NeutralButton)
    	dialog.setNeutralButton(R.string.google_label,
    			
    			new DialogInterface.OnClickListener(){
    		
    				public void onClick(DialogInterface dialog, int which) {
    					
    					//�إߤ@��uri(Universal Resource Identifier)����A�Ngoogle���}�s�J.
    					Uri uri = Uri.parse(getString(R.string.google_url));
    					
    					//�إߤ@��intent����,�öǤJ�o�ӷN�Ϫ�"�ʧ@"�P"���e"
    					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
    					
    					//�ɤJintent��startActivity()�禡��
    					startActivity(intent);
    					
    				}
    					
									
				});
    	
    	
    	//�p���@�̫��s (NegativeButton)
    	dialog.setNegativeButton(R.string.contact_label,
        		new DialogInterface.OnClickListener() {
    				
    				public void onClick(DialogInterface dialog, int which) {
    					
    					//Intent
    					Uri uri = Uri.parse("tel:0926913951");
    					Intent intent = new Intent(Intent.ACTION_DIAL, uri);
    					startActivity(intent);
    				}
    			});
    	
    	//�z�Lshow()�禡�A�N����ܮ���ܥX�ӡC
    	dialog.show();
    }
    
    
    
    
    
    //BMI�p��禡���s
    private Button.OnClickListener bmi = new Button.OnClickListener(){
    	
    	
    	public void onClick(View V){
    		
    		//����activity�A���� switch to report page
    		//�إߤ@�ӷs��intent ����
    		Intent intent = new Intent();
    		
    		//���o��intent���w�ӷ���Activity�Ҧbcalss,�H�Ωҫe����Activity �Ҧb��class
    		intent.setClass(Main.this, Report.class);
    		
    		//�N���[�bintent�W����T�x�s�bbundle����W 
    		Bundle bundle = new Bundle();
    		
    		//�qfield_height�����o���r��A�Ǩ�bundle��KEY_HEIGHT
    		bundle.putString("KEY_HEIGHT", field_height.getText().toString());
    		
    		//�qfield_weight�����o���r��A�Ǩ�bundle��KEY_WEIGHT
    		bundle.putString("KEY_WEIGHT", field_weight.getText().toString());
    		
    		//�z�LputExtras()�禡�Nbundle���e�ǤJintent�o�ӹ���
    		intent.putExtras(bundle);
    		
    		//�ɤJintent��startActivity()�禡��
    		startActivity(intent);
    		
    		
    	}
     	
    };
    	


    //�إ߿��
    
    //MENU_ABOUT���Ĥ@��item;MENU_QUIT���ĤG��item
    protected static final int MENU_ABOUT = Menu.FIRST;
    protected static final int MENU_QUIT = Menu.FIRST+1;
    
    
    //@Override�Ƽg���w�q�bActivity���O���禡 (public class Main extends Activity)
    @Override
    
    // onCreateOptionsMenu()�����C�D��A�ǤJ�@��Menu���O��menu�ѼơC
    public boolean onCreateOptionsMenu(Menu menu){
    	
    	//�Ѽ�1:�s��id, �Ѽ�2:itemID, �Ѽ�3:����, �Ѽ�4:title
    	menu.add(0, MENU_ABOUT, Menu.NONE, "����");
    	menu.add(0, MENU_QUIT, Menu.NONE, "����");
    	return super.onCreateOptionsMenu(menu);
    	
    }
    
    //�B�z�ﶵ�ʧ@
    //onOptionsItemSelected()���B�z�Ҧ��ﶵ�D��A�ǤJ�@��MenuItem���O��item�ѼơC
    public boolean onOptionsItemSelected(MenuItem item){
    	
    	//�z�LgetItemId()�禡���o�ѧO�Ÿ��N�X
    	switch(item.getItemId()){
    	
    	//�I��MENU_ABOUT�A�I�sopenAboutDialog()�禡
    		case MENU_ABOUT:
    			openAboutDialog();
    			break;
    			
    	//�I��MENU_ABOUT�A�I�sfinish()�禡
    		case MENU_QUIT:
    			finish();
    			break;
    	
    	}
    	
    	return super.onOptionsItemSelected(item);
    	
    	
    }
       
    
    
    
}
