package test;

import java.util.ArrayList;

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
	}
}