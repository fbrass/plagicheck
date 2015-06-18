package framework;

import alignerPackage.AlignmentControl;

import java.io.File;
import java.io.FileNotFoundException;

public class Framework {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File text2= new File("src/files/original.txt");
		File text1= new File("src/files/suspect.txt");
		
		
		AlignmentControl ac= new AlignmentControl();
		ac.run(text1, text2);
	}
}
	
//	
//	public static void main(String[] args) {
//		
//		
//
//		IMapFactory mf = new TreeMapFactory();
//		IActionAtInsert action = new StringCoding(0);
//		
////		String s = "Heute ist der Samstag, 99.99.0000.";
//		String s = "99.99.0000.";
//		ArrayList<IToken> tokenlist = new ArrayList<IToken>();
//	
//		try {
//			InputStream stream = new ByteArrayInputStream(s.getBytes("UTF-8"));
//			PushbackReader pbr = new PushbackReader(new InputStreamReader(stream));
//			ILexer lexer = new BaseLexer(pbr);
//			
//			while (pbr.ready()) {
//				IToken next= lexer.getNextToken();
//				if (next != null){
//				int rc =next.getRelativeCode();
//				int cc =next.getClassCode().ordinal();
//				System.out.println(" rc:"+rc+"  cc: "+cc);
//			}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		for (IToken i:tokenlist){
//		System.out.println("rc:" +  i.getRelativeCode() + " ; cc: "+i.getClassCode());}
//		
//	}
//}





















		

//		String s = "aab, aac.";
//		
//		try {
//			InputStream stream = new ByteArrayInputStream(s.getBytes("UTF-8"));
//			PushbackReader pbr = new PushbackReader(new InputStreamReader(stream));
//			ILexer lexer = new BaseLexer(pbr);
//			while (pbr.ready()) {
//
//				ArrayList<IToken> tokenlist = new ArrayList<IToken>();
//				tokenlist.add(lexer.getNextToken());
//			}
//		} catch (Exception e) {
//		}
		
//		 IMapFactory mapFactory = new TreeMapFactory();
//				 ITrie trie = new Trie(mapFactory);
//				
//				 // mit folgendem Wert als Schluessel beginnen:
//				 IActionAtInsert action = new StringCoding(4711);
//				
//				 trie.insert("das", action);
//				 trie.insert("dass", action);
//				 trie.insert("dante", action);
//				 trie.insert("dame", action);
//				 trie.insert("dennis", action);
//				 trie.insert("dirne", action);
//				 trie.insert("dumm", action);
//				 trie.insert("ding", action);
//				 trie.insert("dung", action);
//				 trie.insert("dassault", action);
//				 trie.insert("dark", action);
//				 trie.insert("dark", action);
//
//				 System.out.printf("Result Trie: \n" + trie.toString());
//		
		
		
		

