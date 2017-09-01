package org.ucb.c5.composition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.ucb.c5.composition.model.RBSOption;
import org.ucb.c5.sequtils.Translate;
import org.ucb.c5.utils.FileUtils;

/**
 * Second generation RBSChooser algorithm
 *
 * Employs a list of genes and their associated ribosome binding sites for
 * highly-expressed proteins in E. coli.
 *
 * @author J. Christopher Anderson
 */
public class RBSChooser2 {

    private List<RBSOption> rbss;
    //TODO:  Fill in

    public void initiate() throws Exception {
        //Read the data file
        String data = FileUtils.readResourceFile("composition/data/coli_genes.txt");

        //TODO:  Fill in
    }

    /**
     * Provided a protein of sequence 'peptide', this computes the best ribosome
     * binding site to use from a list of options.  It chooses the best option
     * based on the edit distance between the native gene's first few amino acids
     * and those of the protein we wish to exppress.
     * 
     * It also permits a list of options to exclude.
     *
     * @param peptide The protein sequence, ie MSKGEE...
     * @param ignores The list of RBS's to exclude
     * @return
     * @throws Exception
     */
    public RBSOption run(String peptide, Set<RBSOption> ignores) throws Exception {
        //TODO:  Fill in
        
        return null;
    }

    /**
     * Compute edit distance between two Strings using the Smith-Waterman
     * Algorithm
     *
     * @param s1
     * @param s2
     * @return
     */
    private int calcEditDistance(String s1, String s2) {
        int s1len = s1.length();
        int s2len = s2.length();

        int[][] dist = new int[s1len + 1][s2len + 1];

        for (int a = 0; a <= s1len; a++) {
            for (int b = 0; b <= s2len; b++) {
                if (a == 0) {
                    dist[a][b] = b;
                } else if (b == 0) {
                    dist[a][b] = a;
                } else if (s1.charAt(a - 1) == s2.charAt(b - 1)) {
                    dist[a][b] = dist[a - 1][b - 1];
                } else {
                    dist[a][b] = 1 + Math.min(Math.min(dist[a][b - 1], dist[a - 1][b]), dist[a - 1][b - 1]);
                }
            }
        }

        return dist[s1len][s2len];
    }

    public static void main(String[] args) throws Exception {
        //Create an example
//        String peptide = "MLSDTIDTKQQQQQLHVLFIDSYDSFTYNVVRLIEQQTDISPGVNAVHVTTVHSDTFQSMDQLLPLLPLFDAIVVGPGPGNPNNGAQDMGIISELFENANGKLDEVPILGICLGFQAMCLAQGADVSELNTIKHGQVYEMHLNDAARACGLFSGYPDTFKSTRYHSLHVNAEGIDTLLPLCTTEDENGILLMSAQTKNKPWFGVQYHPESCCSELGGLLVSNFLKLSFINNVKTGRWEKKKLNGEFSDILSRLDRTIDRDPIYKVKEKYPKGEDTTYVKQFEVSEDPKLTFEICNIIREEKFVMSSSVISENTGEWSIIALPNSASQVFTHYGAMKKTTVHYWQDSEISYTLLKKCLDGQDSDLPGSLEVIHEDKSQFWITLGKFMENKIIDNHREIPFIGGLVGILGYEIGQYIACGRCNDDENSLVPDAKLVFINNSIVINHKQGKLYCISLDNTFPVALEQSLRDSFVRKKNIKQSLSWPKYLPEEIDFIITMPDKLDYAKAFKKCQDYMHKGDSYEMCLTTQTKVVPSAVIEPWRIFQTLVQRNPAPFSSFFEFKDIIPRQDETPPVLCFLSTSPERFLKWDADTCELRPIKGTVKKGPQMNLAKATRILKTPKEFGENLMILDLIRNDLYELVPDVRVEEFMSVQEYATVYQLVSVVKAHGLTSASKKTRYSGIDVLKHSLPPGSMTGAPKKITVQLLQDKIESKLNKHVNGGARGVYSGVTGYWSVNSNGDWSVNIRCMYSYNGGTSWQLGAGGAITVLSTLDGELEEMYNKLESNLQIFM";
        String peptide = "MSQTVHFQGNPVTVANSIPQ";

        //Initiate the chooser
        RBSChooser2 chooser = new RBSChooser2();
        chooser.initiate();

        //Make the first choice with an empty Set of ignores
        Set<RBSOption> ignores = new HashSet<>();
        RBSOption selected1 = chooser.run(peptide, ignores);

        //Add the first selection to the list of things to ignore
        ignores.add(selected1);

        //Choose again with an ignore added
        RBSOption selected2 = chooser.run(peptide, ignores);

        //Print out the two options, which should be different
        System.out.println("Protein starts with:");
        System.out.println(peptide.substring(0, 6));
        System.out.println();
        System.out.println("Selected1:\n");
        System.out.println(selected1.toString());
        System.out.println();
        System.out.println("Selected2:\n");
        System.out.println(selected2.toString());
    }
}
