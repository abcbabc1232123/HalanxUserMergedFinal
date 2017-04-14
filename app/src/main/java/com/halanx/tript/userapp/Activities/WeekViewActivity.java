package com.halanx.tript.userapp.Activities;

import android.graphics.Color;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.halanx.tript.userapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class WeekViewActivity extends AppCompatActivity {
    WeekView mWeekView;

    protected String getEventTitle(Calendar time) {

        return "Event of "+time.get(Calendar.HOUR_OF_DAY)+": " + time.get(Calendar.MINUTE)+": "+ (time.get(Calendar.MONTH)+1) +": "+ time.get(Calendar.DAY_OF_MONTH);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);
        mWeekView = (WeekView) findViewById(R.id.weekView);

        mWeekView.setNumberOfVisibleDays(3);
        mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
        mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
        mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));

        mWeekView.setOnEventClickListener(new WeekView.EventClickListener() {
            @Override
            public void onEventClick(WeekViewEvent event, RectF eventRect) {
                Toast.makeText(WeekViewActivity.this, "Date Selected", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        mWeekView.setMonthChangeListener(new MonthLoader.MonthChangeListener() {
            @Override
            public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
                List<WeekViewEvent> events = new ArrayList<>();
                Calendar startTime = Calendar.getInstance();

                startTime.set(Calendar.HOUR_OF_DAY, 3);

                startTime.set(Calendar.MINUTE, 0);

                startTime.set(Calendar.MONTH, newMonth - 1);

                startTime.set(Calendar.YEAR, newYear);

                Calendar endTime = (Calendar) startTime.clone();

                endTime.add(Calendar.HOUR, 1);

                endTime.set(Calendar.MONTH, newMonth - 1);

                WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);

                event.setColor(Color.GREEN);

                events.add(event);


                startTime = Calendar.getInstance();

                startTime.set(Calendar.HOUR_OF_DAY, 3);

                startTime.set(Calendar.MINUTE, 30);

                startTime.set(Calendar.MONTH, newMonth - 1);

                startTime.set(Calendar.YEAR, newYear);

                endTime = (Calendar) startTime.clone();

                endTime.set(Calendar.HOUR_OF_DAY, 4);

                endTime.set(Calendar.MINUTE, 30);

                endTime.set(Calendar.MONTH, newMonth - 1);

                event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);

                event.setColor(Color.RED);

                events.add(event);

                return events;
            }
        });

        setupDateTimeInterpreter(true);

    }

        private void setupDateTimeInterpreter(final boolean shortDate) {

            mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {

                @Override

                public String interpretDate(Calendar date) {

                    SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());

                    String weekday = weekdayNameFormat.format(date.getTime());

                    SimpleDateFormat format = new SimpleDateFormat(" M/d", Locale.getDefault());



                    // All android api level do not have a standard way of getting the first letter of

                    // the week day name. Hence we get the first char programmatically.

                    // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657

                    if (shortDate)

                        weekday = String.valueOf(weekday.charAt(0));

                    return weekday.toUpperCase() + format.format(date.getTime());

                }



                @Override

                public String interpretTime(int hour) {

                    return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM");

                }

            });

    }
}

