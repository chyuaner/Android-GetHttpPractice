package org.twbbs.yuan817.androidgetjson;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	/*private JSONObject[] number_json;
	private JSONObject[] string_json;*/
	private TextView outputText;
	private final String HTTP_URL = "http://192.168.1.14/test.json";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		outputText = (TextView)findViewById(R.id.to_text);
		setNetwork();
		getConfiguration();
	}

	private void setNetwork() {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork() // or
																		// .detectAll()
																		// for
																		// all
																		// detectable
																		// problems
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
				.build());
	}
	
	private void getConfiguration() {
		HttpGet request = new HttpGet();
		try {
			request.setURI(new URI(HTTP_URL));
			// 取得HttpClient物件
			HttpClient httpclient = new DefaultHttpClient();
			
			// 與伺服器溝通
			HttpResponse response = httpclient.execute(request);
			// 伺服器回報成功
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				// 伺服器回傳值
				String result = EntityUtils.toString(response.getEntity());
				
				outputText.setText(result);
			}
		} catch (Exception e) {
			// 發生錯誤
			e.printStackTrace();
			outputText.setText("Fail");
			
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
