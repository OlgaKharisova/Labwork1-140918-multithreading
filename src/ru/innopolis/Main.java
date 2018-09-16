package ru.innopolis;

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

        String[] words = {"любовь", "программирование", "работа", "fire"};
        String res = "result.txt";

        WordFinder wordFinder = new WordFinder();

        wordFinder.getOccurencies(sources, words, res);

/*        URL url = new URL(sources[2]);
        InputStream inputStream = url.openStream();
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }*/
    }
}
