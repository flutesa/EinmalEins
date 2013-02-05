/***
 * Created by Burkova Alexandra
 * m-tel.: +7 (916) 448 99 65
 * e-mail: flutesa@ya.ru
 * 2012, Moscow
 ***/

package com.flutesa.ee;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class EinmaleinsActivity extends Activity implements OnClickListener {

	private int result, answer, fE, sE;	//result - ответ точный, answer - ответ пользователя
	private int difficulty = 10; //сложность выражений по умолчанию - низкая (ещё средняя и высокая может быть)
	private int i = 0; 			//по умолчанию - умножение
	private int j = 0;
	private String mStr = "";	//строка для записи пользовательского ввода
	private final Random myRandom = new Random();
	private final Handler handler = new Handler();
    private final Runnable newGen = new Runnable() {
        //@Override
        public void run() {
			resultElement.setText("");
			answerElement.setText("");
			answerElement.setTextColor(Color.WHITE); 
			answerElement.setTypeface(null, Typeface.NORMAL); 
        	generator(i);
        } //public void run()
    };
	
	private TextView firstElement, secondElement, resultElement, answerElement, operation, diffLevel;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
        setContentView(R.layout.main);

    		final Button Button0 = (Button) findViewById(R.id.Button0);
    			Button0.setOnClickListener(this);
    		final Button Button1 = (Button) findViewById(R.id.Button1);
    			Button1.setOnClickListener(this);			
    		final Button Button2 = (Button) findViewById(R.id.Button2);
    			Button2.setOnClickListener(this);
    		final Button Button3 = (Button) findViewById(R.id.Button3);
    			Button3.setOnClickListener(this);
    		final Button Button4 = (Button) findViewById(R.id.Button4);
    			Button4.setOnClickListener(this);
   			final Button Button5 = (Button) findViewById(R.id.Button5);
    			Button5.setOnClickListener(this);
   			final Button Button6 = (Button) findViewById(R.id.Button6);
    			Button6.setOnClickListener(this);
   			final Button Button7 = (Button) findViewById(R.id.Button7);
    			Button7.setOnClickListener(this);
   			final Button Button8 = (Button) findViewById(R.id.Button8);
    			Button8.setOnClickListener(this);
    		final Button Button9 = (Button) findViewById(R.id.Button9);
    			Button9.setOnClickListener(this);
    		final Button ButtonEnter = (Button) findViewById(R.id.ButtonEnter);
    			ButtonEnter.setOnClickListener(this);
    		final Button ButtonCancel = (Button) findViewById(R.id.ButtonCancel);
    			ButtonCancel.setOnClickListener(this);
    		firstElement = (TextView) findViewById(R.id.firstElement);
    		secondElement = (TextView)findViewById(R.id.secondElement);
    		resultElement = (TextView)findViewById(R.id.resultElement);
    		answerElement = (TextView) findViewById(R.id.answerElement);
    			answerElement.setOnClickListener(this);
    		operation = (TextView) findViewById(R.id.operation);
    			operation.setOnClickListener(this);
    		diffLevel = (TextView) findViewById(R.id.diffLevel);
    			diffLevel.setOnClickListener(this);

    		generator(i); //при запуске - вызов генератора примера со стандартными параметрами сложности и операции

    		ButtonCancel.setText("\u2718"); //крестик
    		ButtonEnter.setText("\u2714");	//галочка		
    	} //public void onCreate(Bundle savedInstanceState)
    	
    //@Override
    public void onClick(View v) {
    	switch (v.getId()) {
    		case R.id.Button0:
    			mStr = mStr+"0";
    			answerElement.setText(mStr);
    			break;
    		case R.id.Button1:
    			mStr = mStr+"1";
    			answerElement.setText(mStr);
    			break;
    		case R.id.Button2:
    			mStr = mStr+"2";
    			answerElement.setText(mStr);
    			break;
    		case R.id.Button3:
    			mStr = mStr+"3";
    			answerElement.setText(mStr);
    			break;
    		case R.id.Button4:
    			mStr = mStr+"4";
    			answerElement.setText(mStr);
    			break;
    		case R.id.Button5:
    			mStr = mStr+"5";
    			answerElement.setText(mStr);
    			break;	
    		case R.id.Button6:
    			mStr = mStr+"6";
    			answerElement.setText(mStr);
    			break;
    		case R.id.Button7:
    			mStr = mStr+"7";
    			answerElement.setText(mStr);
    			break;
    		case R.id.Button8:
    			mStr = mStr+"8";
    			answerElement.setText(mStr);
    			break;
    		case R.id.Button9:
    			mStr = mStr+"9";
    			answerElement.setText(mStr);
    			break;
    		case R.id.ButtonCancel:
    			mStr = ""; //обнулять переменную строки
    			answerElement.setText(""); //обнулять поле ввывода
    			break;
    		case R.id.ButtonEnter: //завершение ввода ответа пользователем, отображение результата и проверка
    			try{
    			answer = Integer.valueOf(mStr); //преобразование строки в число
    			checkEnteredAnswer(answer); //отправка ответа на проверку
    			mStr = ""; //обнулять переменную строки
    			handler.postDelayed(newGen, 300); //с задержкой в 300 милисекунд запускаем следующий пример
    			} catch(Exception e){  } finally{  }
    			break;
    		case R.id.answerElement: //переход в статистику приложения
    			statistics();
    			break;
    		case R.id.operation: //смена знака операции по нажатию на знак
    			answerElement.setText(""); //для нового сгенерированного примера очищаем ответ
    			i++;
    			if (i==4) { i=0; generator(i); }
    			if (i<4) generator(i);
    			break;
    		case R.id.diffLevel: //смена уровня сложности выражений
    			j++;
    			if (j==1) { difficulty = 101;  answerElement.setText(""); generator(i); Toast.makeText(getApplicationContext(), "medium", 0).show(); }
    			if (j==2) { difficulty = 1001; answerElement.setText(""); generator(i); Toast.makeText(getApplicationContext(), "hard", 0).show(); }
    			if (j==3) { j=0; difficulty = 10; answerElement.setText(""); generator(i); Toast.makeText(getApplicationContext(), "light", 0).show(); }
    			break;
    	} //switch (v.getId())
    } //public void onClick(View v)
    
	private void checkEnteredAnswer(int answer) { //проверка ответа и косметические операции в связи с этим
		if (answer==result) {
			answerElement.setTextColor(Color.GREEN); 
			answerElement.setTypeface(null, Typeface.BOLD);  
			answerElement.setText(String.valueOf(answer));  
			}
		else { 
			answerElement.setTextColor(Color.RED); 
			answerElement.setTypeface(null, Typeface.BOLD);
			answerElement.setText(String.valueOf(answer)); 
			resultElement.setTextColor(Color.GREEN);
			resultElement.setText(String.valueOf(result)); 
			}
	} //private void checkEnteredAnswer(int answer)

	public int generator(int i) { //в зависимости от знака операции вызывает нужный метод
		if (i==0) { operation.setText(" * "); multiplication(); result = multiplication(); }
		if (i==1) { operation.setText(" : "); division(); result = division(); }
		if (i==2) { operation.setText(" + "); addition(); result = addition(); }
		if (i==3) { operation.setText(" - "); subtraction(); result = subtraction(); }
	return result;
	} //public int generator(int i)
    
    public int multiplication() { //умножение чисел от 2 до 9    	    	
    	do {
    		fE = myRandom.nextInt(difficulty);
    		sE = myRandom.nextInt(difficulty);
    	} while (fE<=1 || sE<=1);
    	
    	result = fE * sE;
    	
    	firstElement.setText(String.valueOf(fE));
    	secondElement.setText(String.valueOf(sE));
    	
		return result;    	
    } //public int multiplication()
    
    public int division() { //деление чисел
    	do {
    		fE = myRandom.nextInt(difficulty);
    		sE = myRandom.nextInt(difficulty);
    	} while (fE<=1 || sE<=1 || fE<sE || fE%sE != 0 || fE==sE);
    	
    	result = fE / sE;
    	
    	firstElement.setText(String.valueOf(fE));
    	secondElement.setText(String.valueOf(sE));
    	//answerElement.setText(String.valueOf(result));
    	
		return result;    	
    } //public int division()
    
    public int addition() { //сложение чисел, от 2 до 100
    	do {
    		fE = myRandom.nextInt(difficulty);
    		sE = myRandom.nextInt(difficulty);
    	} while (fE<=1 || sE<=1);
    	
    	result = fE + sE;
    	
    	firstElement.setText(String.valueOf(fE));
    	secondElement.setText(String.valueOf(sE));
    	//answerElement.setText(String.valueOf(result));
    	
		return result;    	
    } //public int addition()
    
    public int subtraction() { //вычитание чисел
    	do {
    		fE = myRandom.nextInt(difficulty);
    		sE = myRandom.nextInt(difficulty);
    	} while (fE<=1 || sE<=1 || fE<sE || fE==sE);
    	
    	result = fE - sE;
    	
    	firstElement.setText(String.valueOf(fE));
    	secondElement.setText(String.valueOf(sE));
    	//answerElement.setText(String.valueOf(result));
    	
		return result;    	
    } //public int subtraction()
    
    public void statistics() { //выводит экран статистики
    	//operation.setText(mStr);
    } //public void statistics()
} //public class EinmaleinsActivity