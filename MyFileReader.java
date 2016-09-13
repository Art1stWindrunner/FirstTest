import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class MyFileReader {
	/**
	 * 打开文件
	 */
	private File openFile(String path) {
		File file = new File(path);
		return file;
	}
	/**
	 *取出文件里的各个单词 
	 */
	private void getWords(File file, Map map) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(
								new InputStreamReader(
									new FileInputStream(file)));
		String line = null;
		
		try {
			while((line = br.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(line, ", ，！!？?：:.。;；");//按逗号等字符分隔单词
				while(st.hasMoreElements()) {
					compareWords(st.nextElement(), map);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 比较单词
	 */
	private void compareWords(Object object, Map map) {
		String word = (String)object;
		if (!(map.containsKey(word))) {
				map.put(word, 1);
		} else {
			int i = (int) map.get(word);
			map.put(word, ++i);
		}
	}
	
	private void print(Map<String, Integer> map) {
		for(Map.Entry<String, Integer>entry : map.entrySet()) {
			System.out.println("文档中出现的单词：" + entry.getKey() + "	出现的次数：" + entry.getValue());
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		String path = "/Users/Art1st/Documents/TestFile/test.txt";
		Map map = new HashMap();
		
		MyFileReader myFileReader = new MyFileReader();
		File file = myFileReader.openFile(path);
		myFileReader.getWords(file, map);
		myFileReader.print(map);
	}
	
}
