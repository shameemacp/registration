package jaya.speechapp.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText ed1,ed2,ed3,ed4;
    TextView t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.button);
        t=findViewById(R.id.textView);
        ed1=findViewById(R.id.editText);
        ed2=findViewById(R.id.editText2);
        ed3=findViewById(R.id.editText3);
        ed4=findViewById(R.id.editText4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(ed1.getText().toString().isEmpty()||ed2.getText().toString().isEmpty()||ed3.getText().toString().isEmpty()||ed4.getText().toString().isEmpty()) && ed3.getText().toString().equals(ed4.getText().toString()))
                {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://appealable-merchant.000webhostapp.com/regs.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server

                                    Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                                    if(response.equals("success"))
                                    {
                                        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                                        startActivity(intent);
                                    }


                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                                }

                            }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<>();
//Adding parameters to request

                            params.put("name",ed1.getText().toString());
                            params.put("email",ed2.getText().toString());
                            params.put("password",ed3.getText().toString());
//returning parameter
                            return params;
                        }
                    };

//Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    requestQueue.add(stringRequest);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"not matched ",Toast.LENGTH_LONG).show();

                }

            }
        });

    }
}