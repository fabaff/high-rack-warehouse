
package test;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import item.*;
import location.Bin;


public class TestSCHAM36
{

	public static void main(String[] args)
	{
		ItemAllocation iA = ItemAllocation.getInstance();
		Item item;
		Bin bin;
		
		// 1
		item = Item.getInstance("Item1");  // Artikel wird neu erzeugt
		bin = new Bin("Bin1");
		iA.addItem(item, bin);
		
		// 2
		item = Item.getInstance("Item2");  // Artikel wird neu erzeugt
		bin = new Bin("Bin2");
		iA.addItem(item, bin);
		
		// 3
		item = Item.getInstance("Item3");  // Artikel wird neu erzeugt
		bin = new Bin("Bin3");
		iA.addItem(item, bin);
		
		// 4
		item = Item.getInstance("Item3");  // Derselbe Artikel wird zurückgegeben
		bin = new Bin("Bin4");
		iA.addItem(item, bin);
		
		// 5
		item = Item.getInstance("Item3");  // Derselbe Artikel wird zurückgegeben
		bin = new Bin("Bin5");
		iA.addItem(item, bin);
		
		// 6
		item = Item.getInstance("Item2");  // Derselbe Artikel wird zurückgegeben
		bin = new Bin("Bin6");
		iA.addItem(item, bin);
		
		// wieder entfernen
		iA.removeItem(bin);
		
		// Kontrolle
		String itemString;
		ArrayList<Bin> binList;
		
		itemString = "Item1";
		binList = iA.getBinListCopy(itemString);
		System.out.println(itemString + " ist in " + binList.size() + " Bin(s) gespeichert:");
		for(Bin currentBin : binList)
		{
			System.out.println("BinID: " + currentBin.getBinID());
		}
		System.out.println();
		
		itemString = "Item2";
		binList = iA.getBinListCopy(itemString);
		System.out.println(itemString + " ist in " + binList.size() + " Bin(s) gespeichert:");
		for(Bin currentBin : binList)
		{
			System.out.println("BinID: " + currentBin.getBinID());
		}
		System.out.println();
		
		itemString = "Item3";
		binList = iA.getBinListCopy(itemString);
		System.out.println(itemString + " ist in " + binList.size() + " Bin(s) gespeichert:");
		for(Bin currentBin : binList)
		{
			System.out.println("BinID: " + currentBin.getBinID());
		}
		System.out.println();
		
		//System.out.println((WallClockTime.getTime()));
		//System.out.println((WallClockTime.getTime() / (1000 * 60 * 60 * 24 * 365)));
		
		String now = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS").format(new Date());
		System.out.println(now);
		
		System.out.println(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
		System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()));
		System.out.println(new SimpleDateFormat("HH").format(new Date()));
		System.out.println(new SimpleDateFormat("mm").format(new Date()));
		System.out.println(new SimpleDateFormat("SSS").format(new Date()));
		int m = Integer.parseInt(new SimpleDateFormat("mm").format(new Date()));
		System.out.println(m);
	}
}