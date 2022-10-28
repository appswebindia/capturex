package dev.trendster.capturex;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.webicaso.matkaapp.Models.GameRequest;
import com.webicaso.matkaapp.R;
import com.webicaso.matkaapp.Utils.Utils;
import com.webicaso.matkaapp.activities.DataViewModals.AddGameViewModal;
import com.webicaso.matkaapp.activities.DataViewModals.RegisterViewModal;
import com.webicaso.matkaapp.databinding.ActivityAddgameBinding;
import com.webicaso.matkaapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.internal.Util;

public class AddGameActivity extends AppCompatActivity {
    private AddGameViewModal viewModal;
    private ActivityAddgameBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddgameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModal = new ViewModelProvider(this)
                .get(AddGameViewModal.class);

        binding.starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCaledner((TextInputEditText) view);
            }
        });

        binding.endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCaledner((TextInputEditText) view);
            }
        });

        binding.drawtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCaledner((TextInputEditText) view);
            }
        });

        binding.addgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameRequest request = new GameRequest();
                request.setUserid(Utils.getUser(getBaseContext()).getId());
                request.setName(binding.name.getText().toString().trim());
                request.setStarttime(binding.starttime.getText().toString().trim());
                request.setEndtime(binding.endtime.getText().toString().trim());
                request.setDrawtime(binding.drawtime.getText().toString().trim());

                List<Integer> list = binding.catGroup.getCheckedChipIds();
                ArrayList<String> catList = new ArrayList<>();
                for (int i=0; i<list.size(); i++) {
                    catList.add(getCat(list.get(i)));
                }
                request.setCats(catList);

                List<Integer> listshow = binding.showCatGroup.getCheckedChipIds();
                ArrayList<String> showCatList = new ArrayList<>();
                for (int i=0; i<listshow.size(); i++) {
                    showCatList.add(getCat(listshow.get(i)));
                }
                request.setShowcats(showCatList);

                List<Integer> listdays = binding.dayGroup.getCheckedChipIds();
                ArrayList<String> dayList = new ArrayList<>();
                for (int i=0; i<listdays.size(); i++) {
                    dayList.add(getCat(listdays.get(i)));
                }
                request.setDays(dayList);

                viewModal.addgame(request, AddGameActivity.this , binding.addgame , binding.loading).observe(AddGameActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if(aBoolean){
                            finish();
                        }
                    }
                });

            }
        });


    }
    public void openCaledner(TextInputEditText v){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(AddGameActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                v.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }


    public String getCat(Integer item ){
        switch (item){
            case R.id.catTeenPatti:
            case R.id.showCatTeenPatti:
                return "GAME_TYPE_TEENPATTI";
            case R.id.catDouble:
            case R.id.showCatDouble:
                return "GAME_TYPE_DOUBLE";
            case R.id.catDhai:
            case R.id.showCatDhai:
                return "GAME_TYPE_DHAI";
            case R.id.catHaru:
            case R.id.showCatHaru:
                return "GAME_TYPE_HARU";
            case R.id.sunday:
                return "DAY_SUNDAY";
            case R.id.monday:
                return "DAY_MONDAY";
            case R.id.tuesday:
                return "DAY_TUESDAY";
            case R.id.wednesday:
                return "DAY_WEDNESDAY";
            case R.id.thursday:
                return "DAY_THURSDAY";
            case R.id.friday:
                return "DAY_FRIDAYDAY";
            case R.id.saturday:
                return "DAY_SATURDAY";
        }

        return "NA";
    }

}
