package com.ledou.mhw.view.widget;



import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.ledou.mhw.R;

public class Myeditext extends FrameLayout {  
	    private EditText editText;  
	    private ImageButton imgButton;  
	      
	    public Myeditext(Context context) {  
	        super(context);  
	        // TODO Auto-generated constructor stub  
	    }  
	  
	    public Myeditext(Context context, AttributeSet attrs) {  
	        super(context, attrs);  
	        // TODO Auto-generated constructor stub  
	        LayoutInflater.from(context).inflate(R.layout.myeditext, this, true);
	        this.editText = (EditText)findViewById(R.id.edittext);  
	        this.imgButton = (ImageButton)findViewById(R.id.imageview);  
	        
	        
	        
	        this.editText.addTextChangedListener(new TextWatcher() {  
	            @Override  
	            public void onTextChanged(CharSequence s, int start, int before, int count) {  
	                // TODO Auto-generated method stub  
	                if(!TextUtils.isEmpty(s)){  
	                    imgButton.setVisibility(View.VISIBLE);  
	                }else{  
	                    imgButton.setVisibility(View.GONE);  
	                }  
	            }  
	            @Override  
	            public void beforeTextChanged(CharSequence s, int start, int count,  
	                    int after) {  
	                // TODO Auto-generated method stub  
	            }  
	              
	            @Override  
	            public void afterTextChanged(Editable s) {  
	                // TODO Auto-generated method stub  
	            }  
	        });  
	          
	        //��ť�ĵ���¼� �����������������  
	        this.imgButton.setOnClickListener(new OnClickListener() {  
	            @Override  
	            public void onClick(View v) {  
	                // TODO Auto-generated method stub  
	                editText.setText(null);  
	                imgButton.setVisibility(View.GONE);  
	            }  
	        });  
	    }  
	      
	    //��ֵ
	    
	    public void settext(String string){
	    	
	    	editText.setText(string);
	    	
	    }
	    
	    //��ȡ���������  
	    public String getContent(){  
	        return editText.getText().toString();  
	    }  
	    
	      //���ü��̻س�Ϊ������
	    //Ϊʲô����д����acitivity��OnEditorActionListener�¼��ύ���Զ���view�е�editText��������activity
	    //���ܼ������Զ���view�е�OnEditorActionListener�¼�
	    public  void once(OnEditorActionListener oea){
	    	editText.setOnEditorActionListener(oea);
//	    	editText.setOnEditorActionListener(new OnEditorActionListener() {
//				@Override
//				public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//					// TODO Auto-generated method stub
//					
////				    if (actionId==EditorInfo.IME_ACTION_SEND ||(event!=null&&event.getKeyCode()== KeyEvent.KEYCODE_ENTER)) 
////					{                
//					//do something;    
//		             Intent intent = new Intent(IndexSearchActivity.mactivity,IndexSearchinfoActivity.class);
//		             IndexSearchActivity.mactivity.startActivity(intent);
//
////					return true;             
////					}
//					
//					return false;
//				}
//			});
	    	
	    }
	    
	    
	      
	  
	}