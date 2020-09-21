package com.example.window10edu.aroneincement;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import goldzweigapps.tabs.Builder.EasyTabsBuilder;
import goldzweigapps.tabs.Colors.EasyTabsColors;
import goldzweigapps.tabs.Interface.TabsListener;
import goldzweigapps.tabs.Items.TabItem;
import goldzweigapps.tabs.View.EasyTabs;

public class tutorial extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

       /* EasyTabs tabs = (EasyTabs)findViewById(R.id.EasyTabs);
        EasyTabsBuilder.init(tabs)
                .addTabs(
                        new TabItem(new Fragment1(),"tab1"),
                        new TabItem(new Fragment2(),"tab2"),
                        new TabItem(new Fragment3(),"tab3"))

                .addIcons(
                        R.drawable.ic_book_black_24dp,
                        R.drawable.ic_book_black_24dp,
                        R.drawable.ic_book_black_24dp
                )
                .setBackgroundColor(EasyTabsColors.Black)
                .setIndicatorColor(EasyTabsColors.Red)
                .setTextColors(EasyTabsColors.RoyalBlue , EasyTabsColors.DarkGray)
                .setTabLayoutScrollable(true)

                .withListener(new TabsListener() {
                    @Override
                    public void onScreenPosition(int position) {


                    }
                })
                .HideTitle(false)
                .Build();*/

  }
}
