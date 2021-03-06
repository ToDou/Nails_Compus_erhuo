package com.erhuo.forsale;


import com.erhuo.app.util.Tools;
import com.erhuo.main.MainActivity;
import com.erhuo.main.R;
import com.erhuo.message.ChatMain;
import com.erhuo.message.message;
import com.erhuo.salegoods.GoodsActivity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class contact extends Activity {
	//private MyDialog dialog;
	private LinearLayout layout;
//	private TextView phoneView,goodsName;
	private Button button_phone,button_send,exitButton,contactButton,
	copyqqButton,copyemialButton=null;
	public String userid2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contacting_goods);
		notifym();
		
		button_phone=(Button)findViewById(R.id.call_Btn);
		button_send=(Button)findViewById(R.id.sendmessage_Btn);
		exitButton=(Button)findViewById(R.id.exitBtn);
		contactButton=(Button)findViewById(R.id.contact_to);
		copyqqButton=(Button)findViewById(R.id.copyqq);
		copyemialButton=(Button)findViewById(R.id.copyemail);
		button_phone.setOnClickListener(new button_phoneListener());
		button_send.setOnClickListener(new button_sendListener());
		exitButton.setOnClickListener(new button_exitListener());
		contactButton.setOnClickListener(new contaclListener());
		copyqqButton.setOnClickListener(new qqListener());
		copyemialButton.setOnClickListener(new emialListener());
		//dialog=new MyDialog(this);
		layout=(LinearLayout)findViewById(R.id.exit_layout2);
		 Intent intent=getIntent();
	      	userid2=intent.getStringExtra("userid2");     
//		phoneView=(TextView)findViewById(R.id.phoneView);
//		goodsName=(TextView)findViewById(R.id.goodsName);
		layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！", 
						Toast.LENGTH_SHORT).show();	
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event){
		finish();
		return true;
	}
	class   contaclListener  implements  OnClickListener{
		public void onClick(View v){
//		 Intent intent=getIntent();
//	        String phoneNumber=intent.getStringExtra("phoneNumber");      
//			Uri uri = Uri.parse("tel:"+phoneNumber);
//			Intent intent_2= new Intent(Intent.ACTION_DIAL, uri);
//			startActivity(intent_2);
			
	
			Intent intent =new Intent(contact.this,ChatMain.class);
			intent.putExtra("userid2", userid2);
			contact.this.startActivityForResult(intent,1);
			overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
//			   finish();
			 }
	}
	class   qqListener  implements  OnClickListener{
		public void onClick(View v){
		 Intent intent=getIntent();
	        String qq=intent.getStringExtra("qq");      
//			Uri uri = Uri.parse("tel:"+phoneNumber);
//			Intent intent_2= new Intent(Intent.ACTION_DIAL, uri);
//			startActivity(intent_2);
	        
	        final ClipboardManager clipBoard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
			//在Menu事件呼叫的时候运行这一行代码 文字就复制到粘贴板了
			clipBoard.setText(qq);
			   Toast.makeText(getApplicationContext(), "复制成功！",Toast.LENGTH_SHORT).show();

			   finish();
			 }
	}
	class   emialListener  implements  OnClickListener{
		public void onClick(View v){
			 Intent intent=getIntent();

		     String email=intent.getStringExtra("email");      
//				Uri uri = Uri.parse("tel:"+phoneNumber);
//				Intent intent_2= new Intent(Intent.ACTION_DIAL, uri);
//				startActivity(intent_2);
		        
		        final ClipboardManager clipBoard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
				//在Menu事件呼叫的时候运行这一行代码 文字就复制到粘贴板了
				clipBoard.setText(email);
				   Toast.makeText(getApplicationContext(), "复制成功！",Toast.LENGTH_SHORT).show();

				   finish();
				
			 }
	}
	
	
	
	class   button_phoneListener  implements  OnClickListener{
		public void onClick(View v){
		 Intent intent=getIntent();
	        String phoneNumber=intent.getStringExtra("phoneNumber");      
			Uri uri = Uri.parse("tel:"+phoneNumber);
			Intent intent_2= new Intent(Intent.ACTION_DIAL, uri);
			startActivity(intent_2);
			 }
	}
	class   button_sendListener  implements  OnClickListener{
		public void onClick(View v){
	         Intent intent=getIntent();
	         String phoneNumber=intent.getStringExtra("phoneNumber");           
             Intent intent_1 = new Intent(Intent.ACTION_VIEW);  
              intent_1.setType("vnd.android-dir/mms-sms");
              intent_1.putExtra("sms_body","您好，我是在二货交易软件中看到你的关于"+intent.getStringExtra("name")+"商品的求购信息的，我这有个，还要吗，价格---");
              intent_1.setData(Uri.parse("smsto:"+phoneNumber));//此为号码
              startActivity(intent_1);	

			 }
	}
	class   button_exitListener  implements  OnClickListener{
		public void onClick(View v){
	   finish();

			 }
	}
	
	public void notifym() {
		//获得通知管理器
		 NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); 

		//构建一个通知对象
		 Notification notification = new Notification(R.drawable.icon, "新消息", System.currentTimeMillis()); 

		 PendingIntent pendingIntent = PendingIntent.getActivity( 
				 contact.this, 0, new Intent(contact.this,message.class),0);
		 notification.setLatestEventInfo(getApplicationContext(),"新信息", "通知显示的内容", pendingIntent);
		
			notification.flags|=Notification.FLAG_AUTO_CANCEL; //自动终止
			notification.defaults |= Notification.DEFAULT_SOUND; //默认声音	
		Tools.context=getApplicationContext();
			Tools.manager=manager;
		Tools.notification=notification;
		Tools.pendingIntent=pendingIntent;
	}

}
