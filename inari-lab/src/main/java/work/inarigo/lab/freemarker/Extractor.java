package work.inarigo.lab.freemarker;

import java.util.ArrayList;
import java.util.List;

public class Extractor {
    public static List<String> extract(String content) {
        char[] charArray = content.toCharArray();
        int needle = -1;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (c == '$' && i < charArray.length - 1) {
                if (charArray[i + 1] == '{' && i + 2 < charArray.length - 1) {
                    i += 2;
                    needle = i;
                }
            } else if (c == '}' && needle != -1) {
                String res = content.substring(needle, i);
                result.add(res);
                needle = -1;
            } else if ((c < 64 || c > 90 && c < 97 || c > 122) && c != '.') {
                needle = -1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String content = "hello, ${p.name}, i'm your ${p.job}";
        List<String> extract = extract(content);
        System.out.println(extract);
    }
}
