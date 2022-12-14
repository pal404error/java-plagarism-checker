import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        File inp1 = new File(args[0]);
        File inp2 = new File(args[1]);
        File[] files = {
            inp1,
            inp2
        };
        HashMap < String, HashMap > fileHm = new HashMap < > ();
        HashMap < String, ArrayList > Twords = new HashMap < > ();
        for (File file: files) {

            String TypeOfFile = file.getName().substring(file.getName().indexOf('.'));
            if (TypeOfFile.equals(".txt")) {
                ArrayList < String > a = new ArrayList < > ();
                int wordCount = 0;

                try {
                    String line;
                    InputStream fis = new FileInputStream(file.getAbsolutePath());
                    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
                    BufferedReader br = new BufferedReader(isr);
                    while ((line = (br.readLine())) != null) {
                        line = line.trim();
                        String[] words = line.split("[\"|,| |?|.|@|/|'|;|(|)]+");
                        for (String s: words) {
                            String ns = " ";
                            if (!s.equals(ns) && !s.equals("")) {
                                wordCount += 1;
                                a.add(s);

                            }
                        }
                    }

                    HashMap < String, Integer > wordCountHm = new HashMap < > ();
                    for (int i = 0; i < a.size() - 1; i++) {
                        String vs = a.get(i);

                        if (vs != null) {

                            if (wordCountHm.containsKey(vs)) {

                                wordCountHm.put(vs, wordCountHm.get(vs) + 1);
                            } else {
                                wordCountHm.put(vs, 1);
                            }
                        }

                    }
                    wordCountHm.put("wordCount", wordCount);

                    fileHm.put(file.getName(), wordCountHm);

                    Twords.put(file.getName(), a);

                } catch (IOException e) {
                    System.out.println("Not Found");
                }

            }

        }

        for (int i = 0; i < files.length - 1; i++) {
            String TypeOfFile1 = files[i].getName().substring(files[i].getName().indexOf('.'));

            if (TypeOfFile1.equals(".txt")) {
                String filename1 = files[i].getName();

                ArrayList nfile1 = Twords.get(filename1);
                Set file1 = new HashSet < > ();
                file1.addAll(nfile1);
                nfile1.clear();
                nfile1.addAll(file1);

                for (int j = i + 1; j < files.length; j++) {

                    String TypeOfFile2 = files[j].getName().substring(files[j].getName().indexOf('.'));
                    if (TypeOfFile2.equals(".txt")) {
                        double commonwords = 0;
                        String filename2 = files[j].getName();
                        ArrayList nfile2 = Twords.get(filename2);
                        Set file2 = new HashSet < > ();
                        file2.addAll(nfile2);
                        nfile2.clear();
                        nfile2.addAll(file2);
                        int totalWords = nfile1.size() + nfile2.size();
                        Set trytest = new HashSet < > ();
                        try {
                            if (nfile1.size() > nfile2.size()) {
                                for (int z = 0; z < nfile1.size(); z++) {

                                    if (nfile1.contains(nfile2.get(z))) {

                                        trytest.add(nfile2.get(z));

                                    }
                                    if (nfile2.contains(nfile1.get(z))) {

                                        trytest.add(nfile1.get(z));
                                    }

                                }
                            } else {
                                for (int z = 0; z < nfile2.size(); z++) {

                                    if (nfile1.contains(nfile2.get(z))) {
                                        trytest.add(nfile2.get(z));

                                    }
                                    if (nfile2.contains(nfile1.get(z))) {
                                        trytest.add(nfile1.get(z));

                                    }

                                    ;
                                }
                            }
                        } catch (Exception e) {

                        }

                        commonwords = trytest.size();
                        float Tmatch = (float) commonwords / totalWords;
                        int match = (int)(Tmatch * 100);
                        Double result = ((commonwords / nfile1.size()) / (commonwords / nfile2.size()));
                        System.out.println(Tmatch);
                        System.out.println(match);
                        System.out.println(result);
                        if (result > 1) {
                            if (match > 27) {
                                System.out.println("1\n");
                            } else {
                                System.out.println("0\n");
                            }
                        } else {
                            if (match > 27) {
                                System.out.println("1\n");
                            } else {
                                System.out.println("0\n");

                            }
                        }

                    }
                }

            }

        }

    }
}
