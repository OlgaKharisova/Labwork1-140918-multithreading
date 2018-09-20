package ru.innopolis;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        String packageName = "file://" + System.getenv("PWD") + "/sources/";
        String[] sources = {
                packageName + "1.Vnutrennee_ustrojstvo_Windows_(gl._1-4).txt",
                packageName + "Akcionernye_obwestva._OAO_i_ZAO._Ot_sozdanija_do_likvidacii.txt",
                packageName + "Bandity.txt",
                packageName + "Blesk_i_niweta_informacionnyh_tehnologij._Pochemu_IT_ne_javljajutsja_konkurentnym_preimuwestvom.txt",
                packageName + "Catching Fire.txt",
        };
        String[] words = {"любовь", "программирование", "работа", "fire", "командной строки"};
        String res = "result.txt";

        Main main = new Main();
        main.getOccurencies(sources, words, res);

/*        URL url = new URL(sources[2]);
        InputStream inputStream = url.openStream();
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }*/
    }

    void getOccurencies(String[] sources, String[] words, String res) {
        File file = new File(res);
        if (file.exists()) {
            file.delete();
        }
        List<String> strings = Arrays.asList(words);
        HashSet<String> hashSet = new HashSet<>(strings);

        Arrays.stream(sources).forEach(url -> {
            new SequenceParser(url, res, hashSet).start();
        });
    }
}
