package com.example.yahyagungorur.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by YAHYA on 7/29/2019.
 */


public class WebScraping extends AsyncTask<Void,Void,Void>
{
    String UserAgent ="Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0";
    //ArrayAdapter<String > adapter;
    CustomAdapter customAdapter;
    ArrayList<String> listTitle = new ArrayList<>();
    ArrayList<String> listPrice = new ArrayList<>();
    ArrayList<String> listImg = new ArrayList<>();
    ArrayList<String> listLink = new ArrayList<>();
    ListView lv;
    ProgressDialog dialog;
    Context context;
    String search;
    int counter = 0;

    public WebScraping(ListView lv, Context context,String search) {
        this.lv = lv;
        this.context = context;
        this.search = search;
    }


    @Override
    protected  void onPreExecute()
    {
        super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setTitle("Parsing");
        dialog.setMessage("Data Exracting...");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();

    }
    @Override
    protected Void doInBackground(Void... params) {

        try {
            // Here we create a document object and use JSoup to fetch the website
            String url="https://www.cimri.com/arama?q="+search;
            //"https://www.codetriage.com/?language=Java"
            Document doc = Jsoup.connect(url)
                    .userAgent(UserAgent)
                    .referrer("http://www.google.com")
                    .get();

            org.jsoup.select.Elements items = doc.getElementsByClass("z7ntrt-0");

            for(org.jsoup.nodes.Element item : items){
                String title = item.getElementsByClass("product-title").text();

                String price = item.getElementsByClass("s14oa9nh-0").text();
                String[] prices = price.split(" ");
                if (prices[0].indexOf("tr")>prices[0].indexOf("com")){
                    int startind = prices[0].indexOf("tr");
                    price = prices[0].substring(startind+2)+" "+prices[1];
                }else {
                    int startind = prices[0].indexOf("com");
                    price = prices[0].substring(startind+3)+" "+prices[1];
                }

                String img = "https:"+item.getElementsByTag("img").attr("data-src");

                String link = item.getElementsByClass("s14oa9nh-0 gwkxYt").attr("href");

                Document docs = Jsoup.connect(link)
                        .userAgent(UserAgent)
                        .referrer("http://www.google.com")
                        .get();
                String linkss = docs.getElementsByTag("a").attr("href");
                if(!listLink.contains(linkss))
                    listLink.add(linkss);
                    listTitle.add(title);
                    listPrice.add(price);
                    listImg.add(img);

                TimeUnit.MILLISECONDS.sleep(1750);
                counter++;
                if (counter>31)
                    break;

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    protected void onPostExecute(Void avoid)//Arka plan işlemleri bittikten sonrası
    {
        customAdapter = new CustomAdapter(listTitle,listPrice,listImg,context);
        lv.setAdapter(customAdapter);
        dialog.dismiss();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Uri link = Uri.parse(listLink.get(i));
                Intent openbrowser = new Intent(Intent.ACTION_VIEW,link);
                context.startActivity(openbrowser);
            }
        });
    }
}
