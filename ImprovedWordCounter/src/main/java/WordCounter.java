import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class WordCounter {

	public static void main(String[] args) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] splitLine = null;
		BufferedReader buff = null;

		try {
			FileReader file = new FileReader("words.txt");
			buff = new BufferedReader(file);

			boolean eof = false;
			while (!eof) {
				String line = buff.readLine();

				if (line == null) {
					eof = true;
				} else {
					System.out.println(line);
					splitLine = line.split(" ");

					for (int i = 0; i < splitLine.length; i++) {
						String key = splitLine[i].toLowerCase();
						if (!map.containsKey(key))
							map.put(key, 1);
						else
							map.put(key, map.get(key) + 1);
					}
				}
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				buff.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println(" ");
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
}
