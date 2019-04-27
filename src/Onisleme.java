import zemberek.core.logging.Log;
import zemberek.morphology.TurkishMorphology;
import zemberek.morphology.analysis.SingleAnalysis;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.normalization.TurkishSpellChecker;
import zemberek.tokenization.TurkishTokenizer;
import zemberek.tokenization.antlr.TurkishLexer;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.antlr.v4.runtime.Token;

public class Onisleme {
	public static ArrayList<String> stop_words = new ArrayList<>();
	public static Vector toplam = new Vector();
	public static int frekans_Sayac = 0;
	public static Vector<String> doc_info = new Vector<>();
	public static Vector<String> top50_ozellik = new Vector<>();
	public static Vector<Double> top50_ozellik_bayesEko = new Vector<>();
	public static Vector<Double> top50_ozellik_bayesMag = new Vector<>();
	public static Vector<Double> top50_ozellik_bayesSag = new Vector<>();
	public static Vector<Double> top50_ozellik_bayesSys = new Vector<>();
	public static Vector<Double> top50_ozellik_bayesSpr = new Vector<>();
	public static Vector<Integer> top50_ozellik_adedi = new Vector<>();
	public static double Peko, Pmag, Psys, Pspr, Psag;
	public static double sycEko = 0.0, sycMag = 0.0, sycSys = 0.0, sycSpr = 0.0, sycSag = 0.0;

	public static String Test(int a) {
		String sonuc = "";
		double eko_olasiligi=Peko,mag_olasiligi=Pmag,sag_olasiligi=Psag,sys_olasiligi=Psys,spr_olasiligi=Pspr;
		Double[] snc = new Double[5];
		System.out.println("Seçilen dökümanın n-gram içeriği aşağıdaki gibidir:");
		System.out.println(doc_info.get(a));

		
		for (int i = 0; i < top50_ozellik.size(); i++) {
			
			if(doc_info.get(a).indexOf(top50_ozellik.get(i))>0) {
				eko_olasiligi *= top50_ozellik_bayesEko.get(i);
				snc[0]=eko_olasiligi;
				mag_olasiligi *= top50_ozellik_bayesMag.get(i);
				snc[1]=mag_olasiligi;
				sag_olasiligi *= top50_ozellik_bayesSag.get(i);
				snc[2]=sag_olasiligi;
				sys_olasiligi *= top50_ozellik_bayesSys.get(i);
				snc[3]=sys_olasiligi;
				spr_olasiligi *= top50_ozellik_bayesSpr.get(i);			
				snc[4]=spr_olasiligi;
			}
			
			
		}
		double enbuyuk= snc[1];
		
		System.out.println("Ekonomi:" + eko_olasiligi);
		System.out.println("Magazin:" + mag_olasiligi);
		System.out.println("Saglik :" + sag_olasiligi);
		System.out.println("Siyasi :" + sys_olasiligi);
		System.out.println("Spor   :" + spr_olasiligi);
	
		for(int i=0; i<snc.length; i++)
        {
			if(enbuyuk < snc[i])
            {
                enbuyuk = snc[i];
            }
             
           
        }
		
		if(enbuyuk == eko_olasiligi) {
			return "Ekonomi";
		}
		if(enbuyuk == mag_olasiligi) {
			return "Magazin";
		}
		if(enbuyuk == sag_olasiligi) {
			return "Saglik";
		}
		if(enbuyuk == sys_olasiligi) {
			return "Siyaset";
		}
		if(enbuyuk == spr_olasiligi) {
			return "Spor";
		}
		
		return "";
		
	}
	
	public static void egitim() {
		for (int i = 1; i <= 1150; i++) {
			if (i % 4 == 0) {
				continue;
			}
			if (i <= 150 || (830 >= i && i > 750)) {
				sycEko++;
			}
			if ((i <= 300 && i > 150) || (910 >= i && i > 830)) {
				sycMag++;
			}
			if ((i <= 450 && i > 300) || (990 >= i && i > 910)) {
				sycSag++;
			}
			if ((i <= 600 && i > 450) || (1070 >= i && i > 990)) {
				sycSys++;
			}
			if ((i <= 750 && i > 600) || (1150 >= i && i > 1070)) {
				sycSpr++;
			}

		}
		System.out.println(sycEko);
		Peko=0.2;
		System.out.println(Peko);
		Pmag=0.2;
		System.out.println(Pmag);
		Psag=0.2;
		System.out.println(Psag);
		Psys=0.2;
		System.out.println(Psys);
		Pspr=0.2;
		System.out.println(Pspr);
		
		
		for (int i = 0; i < top50_ozellik.size(); i++) {
			String ozl;
			double a=0.0,b=0.0,c=0.0,d=0.0,e=0.0;
			ozl=top50_ozellik.get(i);
			//top50_ozellik_bayesEko.add()
			
			for (int j = 1; j < doc_info.size(); j++) {
				
				String str = top50_ozellik.get(i);
				if(doc_info.get(j).indexOf(str)>=0) {
					if (j <= 150 || (830 >= i && i > 750)) {
						a++;
					}
					if ((j <= 300 && j > 150) || (910 >= j && j > 830)) {
						b++;
					}
					if ((j <= 450 && j > 300) || (990 >= j && j > 910)) {
						c++;
					}
					if ((j <= 600 && j > 450) || (1070 >= j && j > 990)) {
						d++;
					}
					if ((j <= 750 && j > 600) || (1150 >= j && j > 1070)) {
						e++;
					}
				}else {
					continue;
				}
				
				
			}
			
			System.out.println(a);
			System.out.println(b);
			System.out.println(c);
			System.out.println(d);
			System.out.println(e);
			
			Double x=(a/sycEko);
			System.out.println(x);
		
			top50_ozellik_bayesEko.add((sycEko));
			top50_ozellik_bayesMag.add((b/sycMag));
			top50_ozellik_bayesSag.add((c/sycSag));
			top50_ozellik_bayesSys.add((d/sycSys));
			top50_ozellik_bayesSpr.add((e/sycSpr));
		}


	}

	public static void _3Gram(String a) {
		doc_info.add("");
		Vector tplm = new Vector<>();
		String d[] = a.split("txtson\n");
		System.out.println(d.length);
		System.out.println("1");
		System.out.println("2");
		int n = 5;
		for (int j = 1; j <= d.length; j++) {
			Vector tmp = new Vector<>();
			// System.out.println("3");
			for (int i = 0; i < d[j - 1].length() - n + 1; i++) {
				String x = d[j - 1].substring(i, i + n);
				boolean buldu = false;
				boolean buldu2 = false;

				for (int k = 0; k < tmp.size(); k++) {
					sayac s = (sayac) tmp.elementAt(k);
					if (x.equals(s.ngram)) {
						buldu2 = true;
						s.num++;
					}
				}
				for (int k = 0; k < tplm.size(); k++) {
					sayac s = (sayac) tplm.elementAt(k);
					if (x.equals(s.ngram)) {
						buldu = true;
						s.num++;
						if (s.num == 50) {
							frekans_Sayac++;
						}
					}
				}
				if (!buldu) {
					tplm.add(new sayac(1, x));

				}
				if (!buldu2) {
					tmp.add(new sayac(1, x));

				}

			}
			String atama = new String();
			System.out.println(j);
			if (j <= 150 || (830 >= j && j > 750)) {
				atama = "eko:,";
			}
			if ((j <= 300 && j > 150) || (910 >= j && j > 830)) {
				atama = "mag:,";
			}
			if ((j <= 450 && j > 300) || (990 >= j && j > 910)) {
				atama = "sag:,";
			}
			if ((j <= 600 && j > 450) || (1070 >= j && j > 990)) {
				atama = "sys:,";
			}
			if ((j <= 750 && j > 600) || (1150 >= j && j > 1070)) {
				atama = "spr:,";
			}
			for (int k = 0; k < tmp.size(); k++) {
				sayac s = (sayac) tmp.elementAt(k);
				// System.out.println(s.ngram + ";" + s.num);
				atama += s.ngram + "->" + s.num + ",";

			}
			toplam = tplm;
			doc_info.add(atama);
		}
		System.out.println("hey");
		Log.info();
		//System.out.println(doc_info.get(1));
		//for (String i : doc_info) {
			//System.out.println(i);
		//}

	}

	public static boolean word_kontrol(String kelime) {
		for (int i = 0; i < stop_words.size(); i++) {
			if (stop_words.get(i).equals(kelime)) {
				return false;
			}
		}

		return true;
	}

	public static String VeriCek() {
		String str;
		String metin = new String();

		try {

			FileInputStream fstream = new FileInputStream(
					"C:\\Users\\alick\\eclipse-workspace\\zemberekdeneme\\stop-words.txt");
			DataInputStream dstream = new DataInputStream(fstream);
			BufferedReader breader = new BufferedReader(new InputStreamReader(dstream));
			while ((str = breader.readLine()) != null) {
				stop_words.add(str);
			}

			dstream.close();

		} catch (Exception e) {
			System.err.println("Hata: " + e.getMessage());
		}

		for (int i = 1; i <= 300; i++) {
			int n = i;
			if (n > 750) {

				n = n - 750;
				try {

					FileInputStream fstream = new FileInputStream(
							"C:\\Users\\alick\\eclipse-workspace\\YazLab2_3\\hepsi\\Kopyas " + n + ".txt");
					DataInputStream dstream = new DataInputStream(fstream);
					BufferedReader breader = new BufferedReader(new InputStreamReader(dstream));
					int satir_sayisi = 0;
					while ((str = breader.readLine()) != null) {

						metin = metin + str + "\n";
						satir_sayisi++;
					}

					dstream.close();

				} catch (Exception e) {
					System.err.println("Hata: " + e.getMessage());
				}

			} else {
				try {

					FileInputStream fstream = new FileInputStream(
							"C:\\Users\\alick\\eclipse-workspace\\YazLab2_3\\hepsi\\" + n + ".txt");
					DataInputStream dstream = new DataInputStream(fstream);
					BufferedReader breader = new BufferedReader(new InputStreamReader(dstream));
					int satir_sayisi = 0;
					while ((str = breader.readLine()) != null) {

						metin = metin + str + "\n";
						satir_sayisi++;
					}

					dstream.close();

				} catch (Exception e) {
					System.err.println("Hata: " + e.getMessage());
				}
			}

			metin = metin + "\n" + "txtson\n";
		}
		return metin;

	}

	public static void Calistir() throws IOException {
		TurkishTokenizer tokenizer = TurkishTokenizer.ALL;
		TurkishMorphology morphology = TurkishMorphology.createWithDefaults();
		TurkishSpellChecker spellChecker = new TurkishSpellChecker(morphology);

		String input = VeriCek();
		StringBuilder duzeltilmis = new StringBuilder();
		StringBuilder output = new StringBuilder();

		for (Token token : tokenizer.tokenize(input)) {
			String text = token.getText();
			if (text.equals("txtson")) {
				output.append(text);
				duzeltilmis.append(text + "\n");
				continue;
			}
			if (analyzeToken(token) && !spellChecker.check(text)) {

				List<String> strings = spellChecker.suggestForWord(token.getText());
				if (!strings.isEmpty()) {
					String suggestion = strings.get(0);
					output.append(suggestion);
					String word = suggestion;
					if (!word_kontrol(suggestion)) {
						continue;
					}
					String govde = "";
					WordAnalysis results = morphology.analyze(word);
					for (SingleAnalysis result : results) {
						if (result.getLemmas().size() >= 2) {
							govde = result.getLemmas().get(1) + "_";
						} else {
							govde = result.getLemmas().get(0) + "_";

						}
					}
					duzeltilmis.append(govde);
				} else {
					output.append(text);

					String word = text;
					if (!word_kontrol(text)) {
						continue;
					}
					String govde = "";
					WordAnalysis results = morphology.analyze(word);
					for (SingleAnalysis result : results) {
						if (result.getLemmas().size() >= 2) {
							govde = result.getLemmas().get(1) + "_";
						} else {
							govde = result.getLemmas().get(0) + "_";

						}
					}
					duzeltilmis.append(govde);

				}
			} else {
				output.append(text);
				if (!word_kontrol(text)) {
					continue;
				}
				String word = text;
				String govde = new String();
				WordAnalysis results = morphology.analyze(word);
				for (SingleAnalysis result : results) {
					if (result.getLemmas().size() >= 2) {
						govde = result.getLemmas().get(1) + "_";
					} else {
						govde = result.getLemmas().get(0) + "_";

					}
				}
				duzeltilmis.append(govde);

			}
		}
		// Log.info(output);
		// Log.info(duzeltilmis);
		System.out.println("Zemberek ile düzeltildi...");
		_3Gram(duzeltilmis.toString());
		System.out.println("N-gramlarına ayrılıp dokuman dokuman ayrıldı.");
		Log.info();
		System.out.println(frekans_Sayac + "tane özellik bulundu.");
		int m = 0;
		for (int j = 0; j < toplam.size(); j++) {
			sayac s = (sayac) toplam.elementAt(j);
			if (s.num >= 50) { // 50den buyukleri burdan al.

				top50_ozellik.add(s.ngram);
				top50_ozellik_adedi.add(s.num);
				// System.out.println(s.ngram + ";" + s.num);
				m++;
			}
		}

		for (int j = 0; j < frekans_Sayac; j++) {
			System.out.println(top50_ozellik.get(j) + "," + top50_ozellik_adedi.get(j));

		}
		System.out.println(doc_info.size());
	}

	static boolean analyzeToken(Token token) {
		return token.getType() != TurkishLexer.NewLine && token.getType() != TurkishLexer.SpaceTab
				&& token.getType() != TurkishLexer.UnknownWord && token.getType() != TurkishLexer.RomanNumeral
				&& token.getType() != TurkishLexer.Unknown;
	}

}